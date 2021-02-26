package builders.marketplace.models.advert

data class TechnicalCharacteristic(
    val id: TechnicalCharacteristicId,
    val technicalParameter: TechnicalParameter,
    val value: String,
    val unit: UnitType,
    val comparableValue: Double
)
