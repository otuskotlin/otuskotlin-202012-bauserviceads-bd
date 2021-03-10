package builders.marketplace.transport.multiplatform.models.common

interface IMarketplaceResponse {
    val responseId: String?
    val onRequest: String?
    val endTime: String?
    val errors: List<MultiplatformErrorDto>?
    val status: ResponseStatusDto?
    val debug: IMarketplaceDebug?
}