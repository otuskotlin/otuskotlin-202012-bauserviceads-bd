package builders.marketplace.transport.multiplatform.models.user

import builders.marketplace.transport.multiplatform.models.common.IMarketplaceFilter
import kotlinx.serialization.Serializable

@Serializable
data class UserListFilterDto(override val text: String? = null) : IMarketplaceFilter {
}