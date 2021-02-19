package builders.marketplace.modelsTests

import builders.marketplace.dsl.message.message
import builders.marketplace.models.advert.S3ImagePath
import builders.marketplace.models.message.MessageModel
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldMatch
import java.time.LocalDate

class MessageSpec : BehaviorSpec() {
    init {
        Given("Message created with 'message dsl'") {
            val messagesStorage: MutableSet<MessageModel> = mutableSetOf()
            val message = message {
                conversation {
                    chatId = "1"
                }
                text {
                    text = "Hi, I would like to get a quote"
                }
                createdBy {
                    author = "John Doe with enquiry"
                }
                sentAt {
                    sentAtLocalDate = LocalDate.parse("2021-02-17")
                }
                receivedBy {
                    recipient = "Bob the Builder"
                }
                readAt {
                    readAtLocalDate = LocalDate.parse("2021-02-18")
                }
                attachToMessage {
                    files = setOf(S3ImagePath("s3path1"), S3ImagePath("s3path2"))
                }
            }
            When("Message is sent") {
                messagesStorage.add(message)
                Then("Message received matches message sent") {
                    val receivedMessage = messagesStorage.first()
                    receivedMessage.chatId.id shouldBe "1"
                    receivedMessage.text shouldMatch "Hi, I would like to get a quote"
                    receivedMessage.author.id shouldBe "John Doe with enquiry"
                    receivedMessage.sentAt.toString() shouldBe "2021-02-17"
                    receivedMessage.recipient.id shouldBe "Bob the Builder"
                    receivedMessage.readAt.toString() shouldBe "2021-02-18"
                    receivedMessage.attachments shouldContainAll setOf(S3ImagePath("s3path1"), S3ImagePath("s3path2"))
                }
            }
        }
    }
}