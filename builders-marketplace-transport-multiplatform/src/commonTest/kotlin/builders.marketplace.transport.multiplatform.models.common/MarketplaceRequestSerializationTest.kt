package builders.marketplace.transport.multiplatform.models.common

import builders.marketplace.transport.multiplatform.models.advert.MarketplaceRequestBuyAdvertRead
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import kotlinx.serialization.serializer
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


internal class MarketplaceRequestSerializationTest {

    private val json = Json {
        prettyPrint = true
        serializersModule = SerializersModule {
            polymorphic(MarketplaceMessage::class) {
                subclass(MarketplaceRequestBuyAdvertRead::class)
            }
        }
        classDiscriminator = "type"
    }

    private val request: MarketplaceMessage = MarketplaceRequestBuyAdvertRead(
            requestId = "advert-1"
    )

    @Test
    fun marketplaceRequestSerializationTest() {

        val serializedRequestTemplate = json.encodeToString(MarketplaceMessage.serializer(), request)
        assertTrue(serializedRequestTemplate.contains("${(request as MarketplaceRequestBuyAdvertRead).requestId}"))
    }

    @Test
    fun marketplaceRequestDeserializationTest() {
        val serializedRequestTemplate = json.encodeToString(MarketplaceMessage.serializer(), request)
        val deserializedRequest = json.decodeFromString(MarketplaceMessage.serializer(), serializedRequestTemplate)
        assertEquals("advert-1", (deserializedRequest as MarketplaceRequestBuyAdvertRead).requestId)
    }
}