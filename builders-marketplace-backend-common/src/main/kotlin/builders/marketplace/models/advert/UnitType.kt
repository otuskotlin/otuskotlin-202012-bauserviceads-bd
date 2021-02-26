package builders.marketplace.models.advert

data class UnitType(
    val id: UnitTypeId,
    val name: String,
    val description: String,
    val symbol: String,
    val symbols: Set<String>,
    val isBase: Boolean
) {
    companion object {
        val NONE = UnitType(
            id = UnitTypeId.NONE,
            name = "",
            description = "",
            symbol = "",
            symbols = mutableSetOf(),
            isBase = false
        )
    }
}
