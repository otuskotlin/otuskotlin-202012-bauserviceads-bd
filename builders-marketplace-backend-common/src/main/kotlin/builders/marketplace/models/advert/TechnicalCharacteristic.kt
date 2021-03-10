package builders.marketplace.models.advert

data class TechnicalCharacteristic(
    val id: TechnicalCharacteristicId = TechnicalCharacteristicId.NONE,
    val technicalParameter: TechnicalParameter = TechnicalParameter.NONE,
    val value: String = "",
    val unit: UnitType = UnitType.NONE,
    val comparableValue: Double = Double.MIN_VALUE
)
