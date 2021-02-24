package builders.marketplace.transport.multiplatform.models.user.request

import builders.marketplace.transport.multiplatform.models.common.IMarketplaceDebug
import builders.marketplace.transport.multiplatform.models.common.IMarketplaceRequest
import builders.marketplace.transport.multiplatform.models.common.IMarketplaceUpdateDto
import builders.marketplace.transport.multiplatform.models.common.MarketplaceMessage
import builders.marketplace.transport.multiplatform.models.common.MarketplaceOperationModeDto
import builders.marketplace.transport.multiplatform.models.user.UserDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("RequestUserUpdate")
data class RequestUserUpdate(
    override val requestId: String? = null,
    override val onResponse: String? = null,
    override val startTime: String? = null,
    override val debug: IMarketplaceDebug? = null,
    override val id: String? = null,
    override val lastTimeModifiedAt: Long? = null,
    val user: UserDto? = null
): IMarketplaceRequest, IMarketplaceUpdateDto, MarketplaceMessage() {

    @Serializable
    data class Debug(override val operationMode: MarketplaceOperationModeDto? = null): IMarketplaceDebug
}