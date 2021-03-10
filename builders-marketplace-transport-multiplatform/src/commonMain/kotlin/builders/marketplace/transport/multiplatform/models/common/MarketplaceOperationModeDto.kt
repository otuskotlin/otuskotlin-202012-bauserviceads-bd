package builders.marketplace.transport.multiplatform.models.common

import kotlinx.serialization.Serializable

@Serializable
enum class MarketplaceOperationModeDto {
    PROD,
    UAT,
    STUB
}
