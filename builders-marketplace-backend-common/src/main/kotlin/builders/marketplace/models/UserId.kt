package builders.marketplace.models

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
inline class UserId(val id: String) {
    companion object {
        val NONE = UserId("")
    }
}
