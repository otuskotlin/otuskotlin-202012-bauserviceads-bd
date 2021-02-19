package builders.marketplace.models.advert

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
inline class AdvertId(val id: String) {
    companion object {
        val NONE = AdvertId("")
    }
}