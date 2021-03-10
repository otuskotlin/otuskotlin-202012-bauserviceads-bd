package builders.marketplace.models.chat

import builders.marketplace.models.message.MessageId
import builders.marketplace.models.user.UserId
import java.time.LocalDate

data class ChatModel(val id: ChatId = ChatId.NONE,
                     val messagesIds: Set<MessageId> = mutableSetOf(),
                     val initiator: UserId = UserId.NONE,
                     val recipient: UserId = UserId.NONE,
                     val startedAt: LocalDate = LocalDate.now()) {
}