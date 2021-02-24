package builders.marketplace.transport.multiplatform.models.user.request

import builders.marketplace.transport.multiplatform.models.common.IMarketplaceDebug
import builders.marketplace.transport.multiplatform.models.common.IMarketplaceRequest
import builders.marketplace.transport.multiplatform.models.common.MarketplaceMessage
import builders.marketplace.transport.multiplatform.models.common.MarketplaceOperationModeDto
import builders.marketplace.transport.multiplatform.models.user.UserCreateDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("RequestUserCreate")
data class RequestUserCreate(
    override val requestId: String? = null,
    override val onResponse: String? = null,
    override val startTime: String? = null,
    override val debug: IMarketplaceDebug? = null,
    val userCreateData: UserCreateDto? = null
) : IMarketplaceRequest, MarketplaceMessage() {

    @Serializable
    data class Debug(override val operationMode: MarketplaceOperationModeDto? = null): IMarketplaceDebug
}