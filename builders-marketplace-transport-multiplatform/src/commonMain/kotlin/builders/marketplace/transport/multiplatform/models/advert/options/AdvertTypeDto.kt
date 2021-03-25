package builders.marketplace.transport.multiplatform.models.advert.options

import kotlinx.serialization.Serializable

@Serializable
enum class AdvertTypeDto {
    BUY,
    SELL,
    PROMOTED_BUY,
    PROMOTED_SELL
}
