package builders.marketplace.transport.multiplatform.models.common

interface IMultiplatformAdvertUpdateDto: IMultiplatformAdvertCreateDto {
    val id: String?
    val lastTimeModifiedAt: Long?
}