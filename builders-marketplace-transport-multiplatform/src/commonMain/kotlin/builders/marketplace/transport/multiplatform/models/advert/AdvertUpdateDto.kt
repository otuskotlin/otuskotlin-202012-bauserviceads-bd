package builders.marketplace.transport.multiplatform.models.advert

import builders.marketplace.transport.multiplatform.models.advert.options.AdvertType
import builders.marketplace.transport.multiplatform.models.advert.options.LocationDto
import builders.marketplace.transport.multiplatform.models.advert.options.MarkeplaceAdvertPermission
import builders.marketplace.transport.multiplatform.models.advert.options.TechParamDto
import builders.marketplace.transport.multiplatform.models.common.IMarketplaceUpdateDto
import kotlinx.serialization.Serializable

@Serializable
data class AdvertUpdateDto(
    override val name: String? = null,
    override val categories: Set<String>? = null,
    override val technicalCharacteristics: Set<TechParamDto>? = null,
    override val description: String? = null,
    override val ownerId: String? = null,
    override val createdAt: Long? = null,
    override val imagesS3Paths: Set<String>? = null,
    override val tags: Set<String>? = null,
    override val id: String? = null,
    override val lastTimeModifiedAt: Long? = null,
    override val price: Double? = null,
    override val quantity: Int? = null,
    override val manufacturer: String? = null,
    override val countryOfOrigin: String? = null,
    override val location: LocationDto? = null,
    override val type: AdvertType? = null,
    override val permissions: Set<MarkeplaceAdvertPermission>? = null
) : IMarketplaceUpdateDto, IMarketplaceAdvertDto, IMarketplaceAdvertCreateDto