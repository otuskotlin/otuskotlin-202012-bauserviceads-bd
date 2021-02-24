package builders.marketplace.transport.multiplatform.models.advert

import builders.marketplace.transport.multiplatform.models.advert.options.MarkeplaceAdvertPermission
import builders.marketplace.transport.multiplatform.models.common.IMarketplaceUpdateDto

interface IMarketplaceAdvertDto: IMarketplaceUpdateDto {
    val permissions: Set<MarkeplaceAdvertPermission>?
}