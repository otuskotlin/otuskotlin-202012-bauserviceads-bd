package builders.marketplace.transport.multiplatform.models.advert.options

import kotlinx.serialization.Serializable

@Serializable
data class TechnicalDetailDto(
    val id: String? = null,
    val param: TechParamDto? = null,
    val value: String? = null,
    val unit: UnitTypeDto? = null,
    val comparableValue: Double? = null,
)