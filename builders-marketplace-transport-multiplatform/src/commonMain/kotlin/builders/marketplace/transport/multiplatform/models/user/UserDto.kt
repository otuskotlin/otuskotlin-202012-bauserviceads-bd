package builders.marketplace.transport.multiplatform.models.user

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: String? = null,
    val firstName: String? = null,
    val secondName: String? = null,
    val lastName: String? = null,
    val nickName: String? = null,
    val dateOfBirth: String? = null,
    val email: String? = null,
    val phoneNumber: String? = null,
    val addressFirstLine: String? = null,
    val addressSecondLine: String? = null,
    val postCode: String? = null,
    val city: String? = null,
    val county: String? = null,
    val country: String? = null
)