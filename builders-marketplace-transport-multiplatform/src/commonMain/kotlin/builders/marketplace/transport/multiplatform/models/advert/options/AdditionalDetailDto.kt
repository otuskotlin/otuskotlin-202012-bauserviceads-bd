package builders.marketplace.transport.multiplatform.models.advert.options

import builders.marketplace.transport.multiplatform.models.common.MarketplaceMessage
import kotlinx.serialization.Serializable

@Serializable
data class AdditionalDetailDto(
    val name: String? = null,
    val description: String? = null
) : MarketplaceMessage()