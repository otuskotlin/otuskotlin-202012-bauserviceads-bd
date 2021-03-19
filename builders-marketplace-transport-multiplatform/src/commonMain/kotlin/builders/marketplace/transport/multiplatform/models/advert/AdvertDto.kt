package builders.marketplace.transport.multiplatform.models.advert

import builders.marketplace.transport.multiplatform.models.advert.options.AdvertTypeDto
import builders.marketplace.transport.multiplatform.models.advert.options.LocationDto
import builders.marketplace.transport.multiplatform.models.advert.options.MarketplaceAdvertPermission
import builders.marketplace.transport.multiplatform.models.advert.options.AdditionalDetailDto
import builders.marketplace.transport.multiplatform.models.common.MarketplaceMessage
import kotlinx.serialization.Serializable

@Serializable
data class AdvertDto(
    override val id: String? = null,
    override val name: String? = null,
    override val categories: Set<String>? = null,
    override val additionalDetails: Set<AdditionalDetailDto>? = null,
    override val description: String? = null,
    override val ownerId: String? = null,
    override val createdAt: Long? = null,
    override val imagesS3Paths: Set<String>? = null,
    override val tags: Set<String>? = null,
    override val permissions: Set<MarketplaceAdvertPermission>? = null,
    override val lastTimeModifiedAt: Long? = null,
    override val price: Double? = null,
    override val location: LocationDto? = null,
    override val typeDto: AdvertTypeDto? = null
) : IMarketplaceAdvertDto, IMarketplaceAdvertCreateDto, MarketplaceMessage()