package builders.marketplace.models.advert

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
inline class TechnicalParameterId(val id: String) {
    companion object {
        val NONE = TechnicalParameterId("")
    }
}