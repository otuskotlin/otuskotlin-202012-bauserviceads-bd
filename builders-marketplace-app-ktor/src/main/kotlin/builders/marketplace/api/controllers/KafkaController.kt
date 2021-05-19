package builders.marketplace.api.controllers

import builders.marketplace.api.config.jsonMapperConfig
import builders.marketplace.api.service.AdvertService
import builders.marketplace.api.toError
import builders.marketplace.context.AdvertBackendContext
import builders.marketplace.context.AdvertBackendContextStatus
import builders.marketplace.transport.multiplatform.models.common.IMarketplaceResponse
import builders.marketplace.transport.multiplatform.models.common.MarketplaceMessage
import io.ktor.application.*
import io.ktor.routing.*
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import ru.datana.smart.common.ktor.kafka.KtorKafkaConsumer
import ru.datana.smart.common.ktor.kafka.kafka
import java.time.Instant
import java.util.*

fun Application.kafkaEndpoints(
    brokers: String,
    kafkaConsumer: Consumer<String, String>?,
    kafkaProducer: Producer<String, String>?,
    advertService: AdvertService,
) {
    val topicIn by lazy { environment.config.property("builders.kafka.topicIn").getString() }
    val topicOut by lazy { environment.config.property("builders.kafka.topicOut").getString() }
    val producer: Producer<String,String> = kafkaProducer ?: run {
        KafkaProducer(mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to brokers,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
        ))
    }

    install(KtorKafkaConsumer)

    routing {
        kafkaController(
            topicIn,
            topicOut,
            kafkaConsumer = kafkaConsumer,
            kafkaProducer = producer,
            advertService = advertService,
        )
    }
}

fun Routing.kafkaController(
    topicIn: String,
    topicOut: String,
    kafkaConsumer: Consumer<String, String>?,
    kafkaProducer: Producer<String, String>,
    advertService: AdvertService,
) {
    kafka<String, String> {
        keyDeserializer = StringDeserializer::class.java
        valDeserializer = StringDeserializer::class.java
        consumer = kafkaConsumer

        topic(topicIn) {
            for (item in items.items) {
                var context = AdvertBackendContext(
                    startedAt = Instant.now(),
                    responseId = UUID.randomUUID().toString(),
                )
                try {
                    context.status = AdvertBackendContextStatus.RUNNING
                    val query = jsonMapperConfig.decodeFromString(MarketplaceMessage.serializer(), item.value)
                    service(
                        context = context,
                        query = query,
                        advertService = advertService
                    )?.also {
                        println("SEND SUCCESS $it")
                        kafkaProducer.send(
                            ProducerRecord(
                                topicOut,
                                (it as IMarketplaceResponse).responseId,
                                jsonMapperConfig.encodeToString(MarketplaceMessage.serializer(), it)
                            )
                        )
                    }
                } catch (e: Throwable) {
                    e.printStackTrace()
                    context.status = AdvertBackendContextStatus.FAILING
                    context.errors.plus(e.toError())
                    service(
                        context = context,
                        query = null,
                        advertService = advertService
                    )?.also {
                        println("SEND ERROR $it")
                        kafkaProducer.send(
                            ProducerRecord(
                                topicOut,
                                (it as IMarketplaceResponse).responseId,
                                jsonMapperConfig.encodeToString(MarketplaceMessage.serializer(), it)
                            )
                        )
                    }
                }
            }
        }
    }
}
