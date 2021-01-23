package builders.marketplace.models

data class Address(
        val postCode: String = "",
        val firstLine: String = "",
        val secondLine: String = "",
        val city: String = "",
        val country: String = ""
)
