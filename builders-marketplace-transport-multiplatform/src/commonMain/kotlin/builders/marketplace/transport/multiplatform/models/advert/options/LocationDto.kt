package builders.marketplace.transport.multiplatform.models.advert.options

import builders.marketplace.transport.multiplatform.models.common.MarketplaceMessage
import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    val id: String?,
) : MarketplaceMessage()