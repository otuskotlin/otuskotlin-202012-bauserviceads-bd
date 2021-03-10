package builders.marketplace.models.advert

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
inline class S3ImagePath(val imagePath: String) {
    companion object {
        val NONE = S3ImagePath("")
    }
}
