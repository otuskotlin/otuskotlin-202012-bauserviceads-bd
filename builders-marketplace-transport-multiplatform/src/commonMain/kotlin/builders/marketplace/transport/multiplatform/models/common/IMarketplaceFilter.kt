package builders.marketplace.transport.multiplatform.models.common

interface IMarketplaceFilter {
    val text: String?
    val categories: Set<String>?
}