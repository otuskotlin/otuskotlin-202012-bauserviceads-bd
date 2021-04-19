package builders.marketplace.business.logic.backend

import builders.marketplace.context.AdvertBackendContext
import builders.marketplace.context.AdvertBackendContextStatus
import builders.marketplace.context.AdvertStubCase
import builders.marketplace.models.advert.AdvertModel
import builders.marketplace.models.categories.CategoryId
import builders.marketplace.models.user.UserId
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class AdvertCrudSpec : BehaviorSpec() {
    init {
        Given("Create advert request") {
            val advertCrud = AdvertCrud()
            val context = AdvertBackendContext(
                stubCase = AdvertStubCase.ADVERT_CREATE_SUCCESS,
                requestAdvert = AdvertModel(
                    name = "Advert",
                    categories = setOf(CategoryId("1"), CategoryId("2")),
                    description = "Description",
                    ownerId = UserId("owner")
                )
            )
            When("Request is processed") {
                advertCrud.create(context)

                Then("Request is successful") {
                    context.status shouldBe AdvertBackendContextStatus.FINISHING
                    context.advertResponse.name shouldBe "Advert"
                    context.advertResponse.description shouldBe "Description"
                }
            }
        }
    }
}