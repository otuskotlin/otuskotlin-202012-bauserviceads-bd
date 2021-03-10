package builders.marketplace.transport.multiplatform.models.advert.request

import builders.marketplace.transport.multiplatform.models.advert.AdvertUpdateDto
import builders.marketplace.transport.multiplatform.models.common.IMarketplaceDebug
import builders.marketplace.transport.multiplatform.models.common.IMarketplaceRequest
import builders.marketplace.transport.multiplatform.models.common.MarketplaceMessage
import builders.marketplace.transport.multiplatform.models.common.MarketplaceOperationModeDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("RequestAdvertUpdate")
data class RequestAdvertUpdate(
    override val requestId: String? = null,
    override val onResponse: String? = null,
    override val startTime: String? = null,
    override val debug: IMarketplaceDebug? = null,
    val buyAdvertUpdateData: AdvertUpdateDto? = null
) : IMarketplaceRequest, MarketplaceMessage() {

    @Serializable
    data class Debug(
        override val operationMode: MarketplaceOperationModeDto? = null
    ) : IMarketplaceDebug
}