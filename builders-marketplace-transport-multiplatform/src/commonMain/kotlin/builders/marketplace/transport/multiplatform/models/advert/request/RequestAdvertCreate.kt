package builders.marketplace.transport.multiplatform.models.advert.request

import builders.marketplace.transport.multiplatform.models.advert.AdvertDto
import builders.marketplace.transport.multiplatform.models.common.IMarketplaceAdvert
import builders.marketplace.transport.multiplatform.models.common.IMarketplaceDebug
import builders.marketplace.transport.multiplatform.models.common.IMarketplaceRequest
import builders.marketplace.transport.multiplatform.models.common.MarketplaceMessage
import builders.marketplace.transport.multiplatform.models.common.MarketplaceOperationModeDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("RequestAdvertCreate")
data class RequestAdvertCreate(
    override val requestId: String? = null,
    override val onResponse: String? = null,
    override val startTime: String? = null,
    override val debug: Debug? = null,
    override val advert: AdvertDto? = null
) : IMarketplaceRequest, IMarketplaceAdvert, MarketplaceMessage() {

    @Serializable
    data class Debug(
        override val operationMode: MarketplaceOperationModeDto? = null,
        val stubCase: StubCase? = null
    ) : IMarketplaceDebug

    @Serializable
    enum class StubCase {
        NONE,
        SUCCESS
    }
}


