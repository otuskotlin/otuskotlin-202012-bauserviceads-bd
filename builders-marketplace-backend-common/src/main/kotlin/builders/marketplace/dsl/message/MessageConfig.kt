package builders.marketplace.dsl.message

import builders.marketplace.models.advert.S3ImagePath
import builders.marketplace.models.chat.ChatId
import builders.marketplace.models.message.MessageId
import builders.marketplace.models.message.MessageModel
import builders.marketplace.models.user.UserId
import java.time.LocalDate

class MessageConfig {
    private var id: String = MessageId.NONE.id
    private lateinit var createdBy: String
    private lateinit var receivedBy: String
    private lateinit var conversation: String
    private lateinit var messageText: String
    private lateinit var sentAt: LocalDate
    private lateinit var readAt: LocalDate
    private lateinit var attachments: Set<S3ImagePath>

    fun text(block: MessageTextConfig.() -> Unit) =
            MessageTextConfig()
                    .apply(block)
                    .run {
                        messageText = this.text
                    }

    fun sentAt(block: SentAtConfig.() -> Unit) =
            SentAtConfig()
                    .apply(block)
                    .run {
                        sentAt = this.sentAtLocalDate
                    }

    fun readAt(block: ReadAtConfig.() -> Unit) =
            ReadAtConfig()
                    .apply(block)
                    .run {
                        readAt = this.readAtLocalDate
                    }

    fun createdBy(block: AuthorConfig.() -> Unit) =
            AuthorConfig()
                    .apply(block)
                    .run {
                        createdBy = this.author
                    }

    fun receivedBy(block: RecipientConfig.() -> Unit) =
            RecipientConfig()
                    .apply(block)
                    .run {
                        receivedBy = this.recipient
                    }

    fun conversation(block: ChatConfig.() -> Unit) =
            ChatConfig()
                    .apply(block)
                    .run {
                        conversation = this.chatId
                    }

    fun attachToMessage(block: AttachmentsConfig.() -> Unit) =
            AttachmentsConfig()
                    .apply(block)
                    .run {
                        attachments = this.files
                    }

    fun build() = MessageModel(
            id = MessageId(this.id),
            chatId = ChatId(this.conversation),
            author = UserId(this.createdBy),
            recipient = UserId(this.receivedBy),
            text = this.messageText,
            sentAt = this.sentAt,
            readAt = this.readAt,
            attachments = this.attachments
    )
}

fun message(block: MessageConfig.() -> Unit) = MessageConfig().apply(block).build()