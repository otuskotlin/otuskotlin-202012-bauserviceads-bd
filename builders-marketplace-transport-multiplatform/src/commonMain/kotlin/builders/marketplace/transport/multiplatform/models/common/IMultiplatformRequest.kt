package builders.marketplace.transport.multiplatform.models.common

interface IMultiplatformRequest {
    val requestId: String?
    val onResponse: String?
    val startTime: String?
    val debug: IMultiplatformDebug?
}