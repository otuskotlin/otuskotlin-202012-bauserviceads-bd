package builders.marketplace.transport.multiplatform.models.advert.options

import kotlinx.serialization.Serializable

@Serializable
enum class MarketplaceAdvertPermission {
    READ,
    UPDATE,
    DELETE
}
