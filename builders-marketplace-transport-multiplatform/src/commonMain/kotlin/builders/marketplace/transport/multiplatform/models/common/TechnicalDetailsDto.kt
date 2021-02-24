package builders.marketplace.transport.multiplatform.models.common

import kotlinx.serialization.Serializable

@Serializable
data class TechnicalDetailsDto(
        val id: String? = null,
        val param: TechParamDto? = null,
        val value: String? = null,
        val unit: UnitTypeDto? = null,
        val comparableValue: Double? = null,
)