package builders.marketplace.models

import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class UserModel(
        val id: UserId = UserId.NONE,
        val name: Name = Name(),
        val dateOfBirth: LocalDate = LocalDate.MIN,
        val contacts: Contacts = Contacts(),
        val address: Address = Address(),
        val role: MutableSet<Role> = mutableSetOf()
)

