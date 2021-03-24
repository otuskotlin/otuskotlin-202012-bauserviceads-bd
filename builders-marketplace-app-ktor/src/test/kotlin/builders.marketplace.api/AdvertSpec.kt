package builders.marketplace.api

import builders.marketplace.api.config.jsonMapperConfig
import builders.marketplace.api.service.module
import builders.marketplace.transport.multiplatform.models.advert.request.RequestAdvertRead
import builders.marketplace.transport.multiplatform.models.advert.response.ResponseAdvertRead
import builders.marketplace.transport.multiplatform.models.common.MarketplaceMessage
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.collections.shouldContainAll
import io.ktor.http.*
import io.ktor.server.testing.*

class AdvertSpec : ShouldSpec() {
    init {
        should("Handle Get Advert request") {
            withTestApplication({ module(testing = true) }) {
                handleRequest(HttpMethod.Post, "/advert/read") {
                    val body = jsonMapperConfig.encodeToString(
                        MarketplaceMessage.serializer(),
                        RequestAdvertRead(
                            advertId = "1"
                        )
                    )
                    setBody(body)
                    addHeader("Content-Type", "application/json")
                }.apply {
                    val responseAdvert: ResponseAdvertRead? =
                        response.content?.let { jsonMapperConfig.decodeFromString(MarketplaceMessage.serializer(), it) as ResponseAdvertRead }
                    responseAdvert?.advert?.tags?.shouldContainAll("dampproofing", "roofing")
                }
            }
        }
    }
}