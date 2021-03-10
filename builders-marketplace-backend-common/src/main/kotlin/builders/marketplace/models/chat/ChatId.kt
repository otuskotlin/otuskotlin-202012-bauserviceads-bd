package builders.marketplace.models.chat

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
inline class ChatId(val id: String) {
    companion object {
        val NONE = ChatId("")
    }
}