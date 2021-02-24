package builders.marketplace.transport.multiplatform.models.common

interface IMarketplaceAdvertCreateDto {
    val name: String?
    val categories: Set<String>?
    val technicalCharacteristics: Set<Map<String,String>>?
    val description: String?
    val ownerId: String?
    val createdAt: Long?
    val imagesS3Paths: Set<String>?
    val tags: Set<String>?
}