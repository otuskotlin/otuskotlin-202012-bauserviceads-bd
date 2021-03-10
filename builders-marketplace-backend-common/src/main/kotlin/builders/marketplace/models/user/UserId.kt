package builders.marketplace.models.user

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
inline class UserId(val id: String) {
    companion object {
        val NONE = UserId("")
    }
}
