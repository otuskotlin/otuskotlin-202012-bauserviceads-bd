package builders.marketplace.models.categories

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
inline class CategoryId(val id: String) {
    companion object {
        val NONE = CategoryId("")
    }
}