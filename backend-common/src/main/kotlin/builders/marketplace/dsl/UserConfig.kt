package builders.marketplace.dsl

import builders.marketplace.models.*
import java.time.LocalDate

class UserConfig {
    private var id: UserId = UserId.NONE
    private var name: Name = Name()
    private var dob: LocalDate = LocalDate.MIN
    private var contacts: Contacts = Contacts()
    private var address: Address = Address()
    private var role: MutableSet<Role> = mutableSetOf()

    fun name(block: UserNameConfig.() -> Unit) {
        UserNameConfig()
                .apply(block)
                .run {
                    name = Name(firstName = this.firstName,
                            secondName = this.secondName,
                            lastName = this.lastName)
                }
    }

    fun dateOfBirth(block: DateOfBirthConfig.() -> Unit) {
        DateOfBirthConfig()
                .apply(block)
                .run {
                    dob = this.dateOfBirth
                }
    }

    fun address(block: AddressConfig.() -> Unit) {
        AddressConfig()
                .apply(block)
                .run {
                    address = Address(
                            postCode = postCode,
                            firstLine = firstLine,
                            secondLine = secondLine,
                            city = city,
                            country = country)
                }
    }

    fun contacts(block: ContactsConfig.() -> Unit) {
        ContactsConfig()
                .apply(block)
                .run {
                    contacts = Contacts(
                            email = email,
                            phoneNumber = phoneNumber)
                }
    }

    fun role(block: RoleConfig.() -> Unit) {
        RoleConfig()
                .apply(block)
                .run {
                    role = this.roles.toMutableSet()
                }
    }

    fun build() = UserModel(
            id = id,
            name = name,
            dateOfBirth = dob,
            contacts = contacts,
            address = address,
            role = role
    )
}

fun user(block: UserConfig.() -> Unit) = UserConfig().apply(block).build()