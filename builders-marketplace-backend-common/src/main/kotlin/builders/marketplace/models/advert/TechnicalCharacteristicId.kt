package builders.marketplace.models.advert

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
inline class TechnicalCharacteristicId(val id: String) {
    companion object {
        val NONE = TechnicalCharacteristicId("")
    }
}