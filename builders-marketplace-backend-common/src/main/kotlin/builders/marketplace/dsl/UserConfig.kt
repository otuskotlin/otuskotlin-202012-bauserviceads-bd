package builders.marketplace.dsl

import builders.marketplace.models.Address
import builders.marketplace.models.Contacts
import builders.marketplace.models.Name
import builders.marketplace.models.Role
import builders.marketplace.models.UserId
import builders.marketplace.models.UserModel
import java.time.LocalDate

class UserConfig {
    private var id: UserId = UserId.NONE
    private lateinit var name: Name
    private lateinit var dob: LocalDate
    private var contacts: Contacts = Contacts()
    private var address: Address = Address()
    private var role: MutableSet<Role> = mutableSetOf()

    fun name(block: UserNameConfig.() -> Unit) =
        UserNameConfig()
            .apply(block)
            .run {
                name = Name(
                    firstName = this.firstName,
                    secondName = this.secondName,
                    lastName = this.lastName
                )
            }

    fun dateOfBirth(block: DateOfBirthConfig.() -> Unit) =
        DateOfBirthConfig()
            .apply(block)
            .run {
                dob = this.dateOfBirth
            }

    fun address(block: AddressConfig.() -> Unit) =
        AddressConfig()
            .apply(block)
            .run {
                address = Address(
                    postCode = this.postCode,
                    firstLine = this.firstLine,
                    secondLine = this.secondLine,
                    city = this.city,
                    country = this.country
                )
            }

    fun contacts(block: ContactsConfig.() -> Unit) =
        ContactsConfig()
            .apply(block)
            .run {
                contacts = Contacts(
                    email = email,
                    phoneNumber = phoneNumber
                )
            }

    fun role(block: RoleConfig.() -> Unit) =
        RoleConfig()
            .apply(block)
            .run {
                role = this.roles.toMutableSet()
            }

    fun build() = UserModel(
        id = this.id,
        name = this.name,
        dateOfBirth = this.dob,
        contacts = this.contacts,
        address = this.address,
        role = this.role
    )
}

fun user(block: UserConfig.() -> Unit) = UserConfig().apply(block).build()
