package builders.marketplace.backend.mappers

import builders.marketplace.context.AdvertBackendContext
import builders.marketplace.models.advert.AdditionalDetail
import builders.marketplace.models.advert.AdvertLocationId
import builders.marketplace.models.advert.AdvertType
import builders.marketplace.models.advert.S3ImagePath
import builders.marketplace.models.advert.Tag
import builders.marketplace.models.categories.CategoryId
import builders.marketplace.transport.multiplatform.models.advert.AdvertDto
import builders.marketplace.transport.multiplatform.models.advert.options.AdditionalDetailDto
import builders.marketplace.transport.multiplatform.models.advert.options.AdvertTypeDto
import builders.marketplace.transport.multiplatform.models.advert.options.LocationDto
import builders.marketplace.transport.multiplatform.models.advert.request.RequestAdvertCreate
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.equality.shouldBeEqualToIgnoringFields
import io.kotest.matchers.shouldBe
import java.time.Instant
import java.util.*

class AdvertMapperSpec : BehaviorSpec() {
    init {
        Given("RequestAdvertCreate") {
            val requestAdvertCreate = RequestAdvertCreate(
                requestId = UUID.randomUUID().toString(),
                startTime = Instant.now().toString(),
                advert = AdvertDto(
                    name = "Test advert",
                    categories = setOf("1", "2", "3"),
                    additionalDetails = setOf(
                        AdditionalDetailDto(
                            name = "Property",
                            description = "houses only"
                        )
                    ),
                    description = "Damp proofing required on ground floor",
                    ownerId = UUID.randomUUID().toString(),
                    createdAt = Instant.now().epochSecond,
                    imagesS3Paths = setOf("file://dummypath/image.jpg"),
                    tags = setOf("dampproofing", "roofing"),
                    price = 250.0,
                    location = LocationDto(
                        id = "2"
                    ),
                    typeDto = AdvertTypeDto.SELL
                )
            )
            val advertCreate = requestAdvertCreate.advert
            When("Mapped into AdvertModel") {
                val advertBackendContext = AdvertBackendContext().apply { setQuery(requestAdvertCreate) }
                Then("Data from RequestAdvertCreate is correctly mapped") {
                    val mappedAdvert = advertBackendContext.requestAdvert

                    mappedAdvert.id.id shouldBe ""
                    mappedAdvert.name shouldBe advertCreate?.name
                    mappedAdvert.categories.shouldContainAll(CategoryId("1"), CategoryId("2"), CategoryId("3"))
                    mappedAdvert.additionalDetails.shouldContain(
                        AdditionalDetail(
                            name = "Property",
                            description = "houses only"
                        )
                    )
                    mappedAdvert.description shouldBe advertCreate?.description
                    mappedAdvert.ownerId.id shouldBe advertCreate?.ownerId
                    mappedAdvert.createdAt shouldBe advertCreate?.createdAt
                    mappedAdvert.imagesS3Paths.shouldContain(S3ImagePath("file://dummypath/image.jpg"))
                    mappedAdvert.tags.shouldContainAll(Tag("dampproofing"), Tag("roofing"))
                    mappedAdvert.price.amount.toDouble() shouldBe advertCreate?.price
                    mappedAdvert.location shouldBe AdvertLocationId("2")
                    mappedAdvert.advertType shouldBe AdvertType.SELL
                }
            }
            When("Mapped to transport model") {
                val advertBackendContext = AdvertBackendContext().apply { setQuery(requestAdvertCreate) }
                advertBackendContext.advertResponse = advertBackendContext.requestAdvert
                Then("Data is correctly mapped to transport model") {
                    val response = advertBackendContext.respondCreateAdvert()
                    response.advert!!.shouldBeEqualToIgnoringFields(requestAdvertCreate.advert!!, AdvertDto::lastTimeModifiedAt)
                }
            }
        }
    }
}