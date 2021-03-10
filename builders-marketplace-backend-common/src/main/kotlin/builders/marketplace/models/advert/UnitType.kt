package builders.marketplace.models.advert

data class UnitType(
    val id: UnitTypeId = UnitTypeId.NONE,
    val name: String = "",
    val description: String = "",
    val symbol: String = "",
    val symbols: Set<String> = mutableSetOf(),
    val isBase: Boolean = false
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
