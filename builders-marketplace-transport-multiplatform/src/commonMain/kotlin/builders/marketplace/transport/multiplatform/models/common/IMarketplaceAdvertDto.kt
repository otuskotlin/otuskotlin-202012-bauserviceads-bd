package builders.marketplace.transport.multiplatform.models.common

interface IMarketplaceAdvertDto: IMarketplaceAdvertUpdateDto {
    val permissions: Set<MarkeplaceAdvertPermission>?
}