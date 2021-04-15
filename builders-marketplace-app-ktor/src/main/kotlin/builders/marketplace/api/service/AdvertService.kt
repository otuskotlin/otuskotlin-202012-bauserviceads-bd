package builders.marketplace.api.service

import builders.marketplace.backend.mappers.respondCreateAdvert
import builders.marketplace.backend.mappers.respondDeleteAdvert
import builders.marketplace.backend.mappers.respondReadAdvert
import builders.marketplace.backend.mappers.respondUpdateAdvert
import builders.marketplace.backend.mappers.setQuery
import builders.marketplace.business.logic.backend.AdvertCrud
import builders.marketplace.context.AdvertBackendContext
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
import org.slf4j.LoggerFactory
import java.time.Instant
import java.util.*

class AdvertService(private val crud: AdvertCrud) {
    private val log = LoggerFactory.getLogger(this::class.java)

    suspend fun create(context: AdvertBackendContext, query: RequestAdvertCreate?): ResponseAdvertCreate {
        query?.also { context.setQuery(it) }
        crud.create(context)
        return context.respondCreateAdvert()
    }

    suspend fun read(context: AdvertBackendContext, query: RequestAdvertRead?): ResponseAdvertRead {
        query?.also { context.setQuery(it) }
        crud.read(context)
        return context.respondReadAdvert()
    }

    suspend fun update(context: AdvertBackendContext, query: RequestAdvertUpdate?): ResponseAdvertUpdate {
        query?.also { context.setQuery(it) }
        crud.update(context)
        return context.respondUpdateAdvert()
    }

    suspend fun delete(context: AdvertBackendContext, query: RequestAdvertDelete?): ResponseAdvertDelete {
        query?.also { context.setQuery(it) }
        crud.delete(context)
        return context.respondDeleteAdvert()
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
