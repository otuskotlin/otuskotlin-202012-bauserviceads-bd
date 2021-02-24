package builders.marketplace.transport.multiplatform.models.advert.response

import builders.marketplace.transport.multiplatform.models.advert.AdvertDto
import builders.marketplace.transport.multiplatform.models.common.IMarketplaceDebug
import builders.marketplace.transport.multiplatform.models.common.IMarketplaceResponse
import builders.marketplace.transport.multiplatform.models.common.MarketplaceMessage
import builders.marketplace.transport.multiplatform.models.common.MarketplaceOperationModeDto
import builders.marketplace.transport.multiplatform.models.common.MultiplatformErrorDto
import builders.marketplace.transport.multiplatform.models.common.ResponseStatusDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("ResponseAdvertCreate")
data class ResponseAdvertCreate(
    override val responseId: String? = null,
    override val onRequest: String? = null,
    override val endTime: String? = null,
    override val errors: List<MultiplatformErrorDto>? = null,
    override val status: ResponseStatusDto? = null,
    override val debug: IMarketplaceDebug? = null,
    val advert: AdvertDto? = null
) : IMarketplaceResponse, MarketplaceMessage() {

    @Serializable
    data class Debug(
        override val operationMode: MarketplaceOperationModeDto? = null
    ) : IMarketplaceDebug
}