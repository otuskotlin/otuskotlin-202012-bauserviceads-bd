package builders.marketplace.transport.multiplatform.models.common

interface IMarketplaceRequest {
    val requestId: String?
    val onResponse: String?
    val startTime: String?
    val debug: IMarketplaceDebug?
}