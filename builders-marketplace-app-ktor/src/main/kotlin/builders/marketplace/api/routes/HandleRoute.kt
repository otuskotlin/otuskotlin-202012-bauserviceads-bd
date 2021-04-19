package builders.marketplace.api.routes

import builders.marketplace.api.config.jsonMapperConfig
import builders.marketplace.api.toError
import builders.marketplace.context.AdvertBackendContext
import builders.marketplace.context.AdvertBackendContextStatus.FAILING
import builders.marketplace.context.AdvertBackendContextStatus.RUNNING
import builders.marketplace.transport.multiplatform.models.common.IMarketplaceRequest
import builders.marketplace.transport.multiplatform.models.common.MarketplaceMessage
import io.ktor.application.*
import io.ktor.http.ContentType.Application.Json
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.util.pipeline.*
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.serializer
import java.time.Instant
import java.util.*

@OptIn(InternalSerializationApi::class)
suspend inline fun <reified T : IMarketplaceRequest, reified U : MarketplaceMessage> PipelineContext<Unit, ApplicationCall>.handleRoute(
    block: AdvertBackendContext.(T?) -> U
) {
    val context = AdvertBackendContext(
        responseId = UUID.randomUUID().toString(),
        startedAt = Instant.now()
    )
    kotlin.runCatching {
        val query = call.receive<MarketplaceMessage>() as T
        val response = context.copy(status = RUNNING).block(query)
        val responseJson = jsonMapperConfig.encodeToString(MarketplaceMessage::class.serializer(), response)
        call.respondText(responseJson, Json)
    }.onFailure {
        val response = context.copy(status = FAILING, errors = context.errors.plus(it.toError())).block(null)
        val responseJson = jsonMapperConfig.encodeToString(MarketplaceMessage::class.serializer(), response)
        call.respondText(responseJson, Json)
    }
}
