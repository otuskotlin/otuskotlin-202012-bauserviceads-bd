package builders.marketplace.models.advert

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
inline class UnitTypeId(val id: String) {
    companion object {
        val NONE = UnitTypeId("")
    }
}