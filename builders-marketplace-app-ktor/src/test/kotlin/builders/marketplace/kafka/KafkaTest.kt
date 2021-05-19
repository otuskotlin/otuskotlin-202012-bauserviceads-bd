package builders.marketplace.kafka

import builders.marketplace.api.config.jsonMapperConfig
import builders.marketplace.api.service.module
import builders.marketplace.context.AdvertStubCase
import builders.marketplace.transport.multiplatform.models.advert.request.RequestAdvertCreate
import builders.marketplace.transport.multiplatform.models.common.MarketplaceMessage
import io.kotest.core.spec.style.ShouldSpec
import io.ktor.config.*
import io.ktor.server.testing.*
import io.ktor.util.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import ru.datana.smart.common.ktor.kafka.TestConsumer
import ru.datana.smart.common.ktor.kafka.TestProducer
import java.time.Duration
import java.util.*
import kotlin.test.assertTrue

@OptIn(KtorExperimentalAPI::class)
class KafkaTest : ShouldSpec() {
    init {
        should("Publish to Kafka") {
            val consumer: TestConsumer<String, String> = TestConsumer(duration = Duration.ofMillis(20))
            val producer: TestProducer<String, String> = TestProducer()
            withTestApplication({
                (environment.config as MapApplicationConfig).apply {
                    put("builders.kafka.topicIn", TOPIC_IN)
                    put("builders.kafka.topicOut", TOPIC_OT)
                    put("builders.kafka.brokers", BROKERS)
                }
                module(
                    kafkaTestConsumer = consumer,
                    kafkaTestProducer = producer,
                )
            }) {
                runBlocking {
                    delay(60L)
                    val jsonIn = jsonMapperConfig.encodeToString(
                        MarketplaceMessage.serializer(),
                        RequestAdvertCreate(debug = RequestAdvertCreate.Debug(
                            stubCase = RequestAdvertCreate.StubCase.SUCCESS
                        ))
                    )

                    consumer.send(TOPIC_IN, UUID.randomUUID().toString(), jsonIn)

                    delay(200L)
                    val responseObjs = producer.getSent()

                    assertTrue("Must contain two messages") {
                        val feedBack = responseObjs.first().value()
                        feedBack.contains(Regex("\"status\":\\s*\"SUCCESS\""))
                    }
                }
            }
        }
    }

    companion object {
        const val TOPIC_IN = "builders-advert-request-in"
        const val TOPIC_OT = "builders-advert-request-out"
        const val BROKERS = "http://127.0.0.1:9094"
    }
}



