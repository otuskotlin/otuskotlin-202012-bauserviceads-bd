package builders.marketplace.models.message

import builders.marketplace.models.advert.S3ImagePath
import builders.marketplace.models.chat.ChatId
import builders.marketplace.models.user.UserId
import java.time.LocalDate

data class MessageModel(
        val id: MessageId = MessageId.NONE,
        val text: String,
        val sentAt: LocalDate,
        val readAt: LocalDate,
        val author: UserId,
        val recipient: UserId,
        val chatId: ChatId,
        val attachments: Set<S3ImagePath>
) {
}