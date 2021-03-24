package builders.marketplace.api.controllers

import builders.marketplace.transport.multiplatform.models.advert.AdvertDto
import builders.marketplace.transport.multiplatform.models.advert.options.AdditionalDetailDto
import builders.marketplace.transport.multiplatform.models.advert.options.AdvertTypeDto
import builders.marketplace.transport.multiplatform.models.advert.options.LocationDto
import builders.marketplace.transport.multiplatform.models.advert.request.RequestAdvertCreate
import builders.marketplace.transport.multiplatform.models.advert.request.RequestAdvertDelete
import builders.marketplace.transport.multiplatform.models.advert.request.RequestAdvertRead
import builders.marketplace.transport.multiplatform.models.advert.request.RequestAdvertUpdate
import builders.marketplace.transport.multiplatform.models.advert.response.ResponseAdvertCreate
import builders.marketplace.transport.multiplatform.models.advert.response.ResponseAdvertDelete
import builders.marketplace.transport.multiplatform.models.advert.response.ResponseAdvertRead
import builders.marketplace.transport.multiplatform.models.advert.response.ResponseAdvertUpdate
import builders.marketplace.transport.multiplatform.models.common.MarketplaceMessage
import builders.marketplace.transport.multiplatform.models.common.ResponseStatusDto
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.util.pipeline.*
import org.slf4j.LoggerFactory
import java.time.Instant
import java.util.*

class AdvertController {
    private val log = LoggerFactory.getLogger(this::class.java)

    suspend fun create(pipelineContext: PipelineContext<Unit, ApplicationCall>) {
        try {
            val request = pipelineContext.call.receive<MarketplaceMessage>() as RequestAdvertCreate
            val response = ResponseStub.respond(request)
            pipelineContext.call.respond(response)
        } catch (e: Throwable) {
            log.error("Failed to read request", e)
        }
    }

    suspend fun read(pipelineContext: PipelineContext<Unit, ApplicationCall>) {
        try {
            val request = pipelineContext.call.receive<MarketplaceMessage>() as RequestAdvertRead
            val response = ResponseStub.respond(request)
            pipelineContext.call.respond(response)
        } catch (e: Throwable) {
            log.error("Failed to read request", e)
        }
    }

    suspend fun update(pipelineContext: PipelineContext<Unit, ApplicationCall>) {
        try {
            val request = pipelineContext.call.receive<MarketplaceMessage>() as RequestAdvertUpdate
            val response = ResponseStub.respond(request)
            pipelineContext.call.respond(response)
        } catch (e: Throwable) {
            log.error("Failed to read request", e)
        }
    }

    suspend fun delete(pipelineContext: PipelineContext<Unit, ApplicationCall>) {
        try {
            val request = pipelineContext.call.receive<MarketplaceMessage>() as RequestAdvertDelete
            val response = ResponseStub.respond(request)
            pipelineContext.call.respond(response)
        } catch (e: Throwable) {
            log.error("Failed to read request", e)
        }
    }

    object ResponseStub {
        val advertStub: AdvertDto = AdvertDto(
            name = "Test advert",
            categories = setOf("1", "2", "3"),
            additionalDetails = setOf(
                AdditionalDetailDto(
                    name = "Property",
                    description = "houses only"
                )
            ),
            description = "Damp proofing required on ground floor",
            ownerId = UUID.randomUUID().toString(),
            createdAt = Instant.now().epochSecond,
            imagesS3Paths = setOf("file://dummypath/image.jpg"),
            tags = setOf("dampproofing", "roofing"),
            price = 250.0,
            location = LocationDto(
                id = "2"
            ),
            typeDto = AdvertTypeDto.SELL
        )

        inline fun <reified T> respond(request: T): MarketplaceMessage {
            return when (request) {
                is RequestAdvertCreate -> ResponseAdvertCreate(
                    responseId = "1",
                    onRequest = request.requestId,
                    endTime = Instant.now().toString(),
                    status = ResponseStatusDto.SUCCESS,
                    advert = advertStub
                )
                is RequestAdvertRead -> ResponseAdvertRead(
                    responseId = "1",
                    onRequest = request.requestId,
                    endTime = Instant.now().toString(),
                    status = ResponseStatusDto.SUCCESS,
                    advert = advertStub
                )
                is RequestAdvertUpdate -> ResponseAdvertUpdate(
                    responseId = "1",
                    onRequest = request.requestId,
                    endTime = Instant.now().toString(),
                    status = ResponseStatusDto.SUCCESS,
                    advert = advertStub.copy(tags = setOf("dampproofing", "roofing", "joinery"))
                )
                is RequestAdvertDelete -> ResponseAdvertDelete(
                    responseId = "1",
                    onRequest = request.requestId,
                    endTime = Instant.now().toString(),
                    status = ResponseStatusDto.SUCCESS,
                    advert = advertStub,
                    deleted = true
                )
                else -> throw IllegalArgumentException("Incorrect request")
            }
        }
    }
}