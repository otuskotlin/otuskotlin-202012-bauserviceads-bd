package builders.marketplace.models.advert

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
inline class Tag(val name: String) {
    companion object {
        val NONE = Tag("")
    }
}