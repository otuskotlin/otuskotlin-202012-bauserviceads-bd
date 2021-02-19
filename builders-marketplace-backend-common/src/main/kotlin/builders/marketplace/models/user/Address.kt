package builders.marketplace.models.user

data class Address(
    val postCode: String = "",
    val firstLine: String = "",
    val secondLine: String = "",
    val city: String = "",
    val country: String = ""
)
