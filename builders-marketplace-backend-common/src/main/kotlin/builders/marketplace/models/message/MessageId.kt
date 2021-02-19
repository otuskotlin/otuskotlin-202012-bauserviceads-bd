package builders.marketplace.models.message

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
inline class MessageId(val id: String) {
    companion object {
        val NONE = MessageId("")
    }
}