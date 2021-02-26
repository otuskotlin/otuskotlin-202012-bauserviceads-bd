package builders.marketplace.models.advert

inline class AdvertLocationId(val locationId: String) {
    companion object {
        val NONE = AdvertLocationId("")
    }
}