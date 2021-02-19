package builders.marketplace.models.chat

import builders.marketplace.models.message.MessageId
import builders.marketplace.models.user.UserId

data class ChatModel(val id: ChatId = ChatId.NONE,
                     val messagesIds: Set<MessageId>,
                     val initiator: UserId,
                     val recipient: UserId,
                     val startedAt: Long) {
}