package builders.marketplace.modelsTests

import builders.marketplace.dsl.user.user
import builders.marketplace.models.user.Role.USER
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class UserSpec : AnnotationSpec() {

    @Test
    fun userDslTest() {
        val user = user {
            name {
                firstName = "John"
                secondName = ""
                lastName = "Doe"
            }
            dateOfBirth {
                dateOfBirth = LocalDate.parse("2002-01-01")
            }
            address {
                postCode = "SE1 2QN"
                firstLine = "Tower Bridge"
                secondLine = ""
                city = "London"
                country = "UK"
            }
            contacts {
                email = "john.doe@builders.market.com"
                phoneNumber = "+441234567890"
            }
            role {
                add(USER)
            }
            nickname {
                nickname = "nick"
            }
        }
        user.name.firstName shouldBe "John"
        user.name.secondName shouldBe ""
        user.name.lastName shouldBe "Doe"

        user.dateOfBirth shouldBe LocalDate.parse("2002-01-01")

        user.address.postCode shouldBe "SE1 2QN"
        user.address.firstLine shouldBe "Tower Bridge"
        user.address.city shouldBe "London"
        user.address.country shouldBe "UK"

        user.contacts.email shouldBe "john.doe@builders.market.com"
        user.contacts.phoneNumber shouldBe "+441234567890"
        user.nickname shouldBe "nick"

        user.role shouldContain USER
    }
}
