package builders.marketplace.models.advert

data class TechnicalParameter(
    val id: TechnicalParameterId,
    val name: String,
    val description: String,
    val priority: Double,
    val units: Set<UnitType>
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
