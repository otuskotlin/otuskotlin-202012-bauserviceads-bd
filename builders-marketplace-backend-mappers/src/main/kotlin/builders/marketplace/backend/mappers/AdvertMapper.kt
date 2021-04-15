package builders.marketplace.backend.mappers

import builders.marketplace.context.AdvertBackendContext
import builders.marketplace.models.advert.AdditionalDetail
import builders.marketplace.models.advert.AdvertId
import builders.marketplace.models.advert.AdvertLocationId
import builders.marketplace.models.advert.AdvertModel
import builders.marketplace.models.advert.AdvertPermission
import builders.marketplace.models.advert.AdvertType
import builders.marketplace.models.advert.Money
import builders.marketplace.models.advert.S3ImagePath
import builders.marketplace.models.advert.Tag
import builders.marketplace.models.categories.CategoryId
import builders.marketplace.models.user.UserId
import builders.marketplace.transport.multiplatform.models.advert.AdvertDto
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
import java.math.BigDecimal
import java.time.Instant
import java.util.*

fun AdvertBackendContext.setQuery(requestAdvertRead: RequestAdvertRead) {
    copy(
        requestAdvertId = requestAdvertRead.advertId?.let { id -> AdvertId(id) } ?: AdvertId.NONE
    )
}

fun AdvertBackendContext.setQuery(requestAdvertCreate: RequestAdvertCreate) {
    requestAdvertCreate.advert?.let { advertCreateDto ->
        copy(
            requestAdvert = advertCreateDto.toInternalAdvertModel()
        )
    }
}

fun AdvertBackendContext.setQuery(requestAdvertUpdate: RequestAdvertUpdate) {
    requestAdvertUpdate.advert?.let { advertUpdateDto ->
        copy(
            requestAdvert = advertUpdateDto.toInternalAdvertModel()
        )
    }
}

fun AdvertBackendContext.setQuery(requestAdvertDelete: RequestAdvertDelete) {
    copy(
        requestAdvertId = requestAdvertDelete.advertId?.let { id -> AdvertId(id) } ?: AdvertId.NONE
    )
}

fun AdvertBackendContext.respondCreateAdvert() = ResponseAdvertCreate(
    responseId = UUID.randomUUID().toString(),
    onRequest = this.requestAdvertId.id,
    endTime = Instant.now().toString(),
    advert = this.advertResponse.takeIf { advert -> advert != AdvertModel.NONE }?.toTransportModel()
)

fun AdvertBackendContext.respondReadAdvert() = ResponseAdvertRead(
    responseId = UUID.randomUUID().toString(),
    onRequest = this.requestAdvertId.id,
    endTime = Instant.now().toString(),
    advert = this.advertResponse.takeIf { advert -> advert != AdvertModel.NONE }?.toTransportModel()
)

fun AdvertBackendContext.respondUpdateAdvert() = ResponseAdvertUpdate(
    responseId = UUID.randomUUID().toString(),
    onRequest = this.requestAdvertId.id,
    endTime = Instant.now().toString(),
    advert = this.advertResponse.takeIf { advert -> advert != AdvertModel.NONE }?.toTransportModel()
)

fun AdvertBackendContext.respondDeleteAdvert() = ResponseAdvertDelete(
    responseId = UUID.randomUUID().toString(),
    onRequest = this.requestAdvertId.id,
    endTime = Instant.now().toString(),
    advert = this.advertResponse.takeIf { advert -> advert != AdvertModel.NONE }?.toTransportModel()
)

internal fun AdvertModel.toTransportModel() = AdvertDto(
    id = id.id.takeIf { id -> id.isNotBlank() },
    name = name.takeIf { name -> name.isNotBlank() },
    description = description.takeIf { description -> description.isNotBlank() },
    categories = categories.handle(),
    additionalDetails = additionalDetails.handle(),
    ownerId = ownerId.takeIf { ownerId -> ownerId != UserId.NONE }?.id,
    createdAt = createdAt.takeIf { createdAt != Long.MIN_VALUE },
    imagesS3Paths = imagesS3Paths.handle(),
    tags = tags.handle(),
    permissions = advertPermission.handle(),
    lastTimeModifiedAt = lastTimeModifiedAt.takeIf { lastTimeModifiedAt -> lastTimeModifiedAt != Long.MIN_VALUE },
    typeDto = advertType.takeIf { advertType -> advertType != AdvertType.NA && advertType.name.isNotBlank() }
        .run { this?.let { AdvertTypeDto.valueOf(name) } },
    price = price.takeIf { money -> money.amount != BigDecimal.ZERO }?.amount?.toDouble(),
    location = location.takeIf { advertLocationId -> advertLocationId != AdvertLocationId.NONE }.run {
        this?.let {
            LocationDto(
                id = locationId
            )
        }
    },
    deleted = deleted
)

fun AdvertDto.toInternalAdvertModel() = AdvertModel(
    id = id?.let { id -> AdvertId(id) } ?: AdvertId.NONE,
    name = name ?: "",
    description = description ?: "",
    categories = categories?.map { category -> CategoryId(category) }?.toSet() ?: setOf(
        CategoryId.NONE
    ),
    additionalDetails = additionalDetails?.map { additionalDetail ->
        AdditionalDetail(
            name = additionalDetail.name ?: "",
            description = additionalDetail.description ?: ""
        )
    }?.toSet() ?: setOf(),
    ownerId = ownerId?.let { id -> UserId(id) } ?: UserId.NONE,
    createdAt = createdAt ?: Instant.now().epochSecond,
    imagesS3Paths = imagesS3Paths?.map { s3imagePath -> S3ImagePath(s3imagePath) }?.toSet()
        ?: setOf(),
    tags = tags?.map { tag -> Tag(tag) }?.toSet() ?: setOf(),
    advertPermission = permissions?.map { permission ->
        when (permission.name) {
            "READ" -> AdvertPermission.READ
            "UPDATE" -> AdvertPermission.UPDATE
            "DELETE" -> AdvertPermission.DELETE
            else -> AdvertPermission.NA
        }
    }?.toSet() ?: setOf(),
    lastTimeModifiedAt = lastTimeModifiedAt ?: Instant.now().epochSecond,
    advertType = typeDto?.let { typeDto -> AdvertType.valueOf(typeDto.name) } ?: AdvertType.NA,
    price = price?.let { price -> Money(amount = BigDecimal.valueOf(price)) } ?: Money.NONE,
    location = location?.id?.let { id -> AdvertLocationId(locationId = id) }
        ?: AdvertLocationId.NONE
)
