package builders.marketplace.transport.multiplatform.models.common

interface IMultiplatformAdvertDto: IMultiplatformAdvertUpdateDto {
    val permissions: Set<MultiplatformAdvertPermission>?
}