package builders.marketplace.transport.multiplatform.models.common

import builders.marketplace.transport.multiplatform.models.advert.request.RequestAdvertRead
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


internal class MarketplaceRequestSerializationTest {

    private val json = Json {
        prettyPrint = true
        serializersModule = SerializersModule {
            polymorphic(MarketplaceMessage::class) {
                subclass(RequestAdvertRead::class)
            }
        }
        classDiscriminator = "type"
    }

    private val request: MarketplaceMessage = RequestAdvertRead(
        requestId = "advert-1"
    )

    @Test
    fun marketplaceRequestSerializationTest() {

        val serializedRequest = json.encodeToString(MarketplaceMessage.serializer(), request)
        println(serializedRequest)
        assertTrue(serializedRequest.contains("${(request as RequestAdvertRead).requestId}"))
    }

    @Test
    fun marketplaceRequestDeserializationTest() {
        val serializedRequest = json.encodeToString(MarketplaceMessage.serializer(), request)
        val deserializedRequest = json.decodeFromString(MarketplaceMessage.serializer(), serializedRequest)
        assertEquals("advert-1", (deserializedRequest as RequestAdvertRead).requestId)
    }

    @Test
    fun marketplaceRequestShouldBeSerializedInCamelCase() {
        val serializedRequestTemplate = json.encodeToString(MarketplaceMessage.serializer(), request)
        val typeString =
            """|"type": "RequestAdvertRead""""
                .trimMargin()
        val valueString =
            """|"requestId": "advert-1""""
                .trimMargin()
        assertTrue { serializedRequestTemplate.contains(typeString) }
        assertTrue { serializedRequestTemplate.contains(valueString) }
    }
}