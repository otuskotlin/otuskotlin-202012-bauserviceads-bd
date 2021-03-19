package builders.marketplace.transport.multiplatform.models.advert

import builders.marketplace.transport.multiplatform.models.advert.options.AdvertTypeDto
import builders.marketplace.transport.multiplatform.models.advert.options.LocationDto
import builders.marketplace.transport.multiplatform.models.advert.options.AdditionalDetailDto
import com.ionspin.kotlin.bignum.decimal.BigDecimal

interface IMarketplaceAdvertCreateDto {
    val name: String?
    val categories: Set<String>?
    val additionalDetails: Set<AdditionalDetailDto>?
    val description: String?
    val ownerId: String?
    val createdAt: Long?
    val imagesS3Paths: Set<String>?
    val tags: Set<String>?
    val price: Double?
    val location: LocationDto?
    val typeDto: AdvertTypeDto?
}