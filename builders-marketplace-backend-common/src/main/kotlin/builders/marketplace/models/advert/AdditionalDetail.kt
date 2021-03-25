package builders.marketplace.models.advert

data class AdditionalDetail(
    val name: String = "",
    val description: String = ""
) {
    companion object {
        val NONE = AdditionalDetail()
    }
}
