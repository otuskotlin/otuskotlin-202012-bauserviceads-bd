package builders.marketplace.models.advert

data class TechnicalParameter(
    val id: TechnicalParameterId = TechnicalParameterId.NONE,
    val name: String = "",
    val description: String = "",
    val priority: Double = Double.MIN_VALUE,
    val units: Set<UnitType> = mutableSetOf()
) {
    companion object {
        val NONE = TechnicalParameter(
            id = TechnicalParameterId.NONE,
            name = "",
            description = "",
            priority = Double.MIN_VALUE,
            units = mutableSetOf()
        )
    }
}
