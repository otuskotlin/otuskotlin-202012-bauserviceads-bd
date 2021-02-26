package builders.marketplace.transport.multiplatform.models.advert

import builders.marketplace.transport.multiplatform.models.advert.options.AdvertType
import builders.marketplace.transport.multiplatform.models.advert.options.LocationDto
import builders.marketplace.transport.multiplatform.models.advert.options.TechParamDto
import builders.marketplace.transport.multiplatform.models.advert.options.TechnicalDetailDto

interface IMarketplaceAdvertCreateDto {
    val name: String?
    val categories: Set<String>?
    val technicalDetails: Set<TechnicalDetailDto>?
    val description: String?
    val ownerId: String?
    val createdAt: Long?
    val imagesS3Paths: Set<String>?
    val tags: Set<String>?
    val price: Double?
    val quantity: Int?
    val manufacturer: String?
    val countryOfOrigin: String?
    val location: LocationDto?
    val type: AdvertType?
}