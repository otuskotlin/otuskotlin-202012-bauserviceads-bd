package builders.marketplace.models.message

import builders.marketplace.models.advert.S3ImagePath
import builders.marketplace.models.chat.ChatId
import builders.marketplace.models.user.UserId
import java.time.LocalDate

data class MessageModel(
        val id: MessageId = MessageId.NONE,
        val text: String = "",
        val sentAt: LocalDate = LocalDate.now(),
        val readAt: LocalDate = LocalDate.now(),
        val author: UserId = UserId.NONE,
        val recipient: UserId = UserId.NONE,
        val chatId: ChatId = ChatId.NONE,
        val attachments: Set<S3ImagePath> = mutableSetOf()
) {
}