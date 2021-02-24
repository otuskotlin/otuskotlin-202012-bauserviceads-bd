package builders.marketplace.transport.multiplatform.models.common

interface IMarketplaceAdvertUpdateDto: IMarketplaceAdvertCreateDto {
    val id: String?
    val lastTimeModifiedAt: Long?
}