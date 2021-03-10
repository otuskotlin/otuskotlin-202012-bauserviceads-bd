package builders.marketplace.models.categories

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
inline class SubCategoryId(val id: String) {
    companion object {
        val NONE = SubCategoryId("")
    }
}