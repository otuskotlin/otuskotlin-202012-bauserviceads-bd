package builders.marketplace.transport.multiplatform.models.advert.options

import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    val id: String?,
    val city: String?,
    val county: String?,
    val postCode: String?
)