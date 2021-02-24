package builders.marketplace.transport.multiplatform.models.advert

import builders.marketplace.transport.multiplatform.models.common.IMarketplaceDebug
import builders.marketplace.transport.multiplatform.models.common.IMarketplaceRequest
import builders.marketplace.transport.multiplatform.models.common.MarketplaceMessage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("MarketplaceRequestBuyAdvertRead")
data class MarketplaceRequestBuyAdvertRead(
        override val requestId: String? = null,
        override val onResponse: String? = null,
        override val startTime: String? = null,
        override val debug: IMarketplaceDebug? = null,
        val advertId: String? = null
) : IMarketplaceRequest, MarketplaceMessage()