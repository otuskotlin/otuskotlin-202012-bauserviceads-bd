package builders.marketplace.transport.multiplatform.models.advert

import builders.marketplace.transport.multiplatform.models.common.IMarketplaceFilter
import kotlinx.serialization.Serializable

@Serializable
data class AdvertListFilterDto(
    override val text: String? = null,
    override val categories: Set<String>? = null
) : IMarketplaceFilter