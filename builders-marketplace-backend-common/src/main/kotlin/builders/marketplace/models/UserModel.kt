package builders.marketplace.models

import java.time.LocalDate

val customerStorage = mutableListOf<UserModel>()

data class UserModel(
    val id: UserId = UserId.NONE,
    val name: Name = Name(),
    val dateOfBirth: LocalDate = LocalDate.MIN,
    val contacts: Contacts = Contacts(),
    val address: Address = Address(),
    val role: MutableSet<Role> = mutableSetOf()
)
