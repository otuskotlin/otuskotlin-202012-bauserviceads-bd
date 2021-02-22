package builders.marketplace.transport.multiplatform.models.common

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = KSerializer::class)
data class TechnicalDetailsDto(
        val id: String? = null,
        val param: TechParamDto? = null,
        val value: String? = null,
        val unit: UnitTypeDto? = null,
        val comparableValue: Double? = null,
)