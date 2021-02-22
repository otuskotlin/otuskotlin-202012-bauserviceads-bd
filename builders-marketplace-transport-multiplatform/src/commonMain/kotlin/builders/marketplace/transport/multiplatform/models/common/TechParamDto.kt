package builders.marketplace.transport.multiplatform.models.common

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = KSerializer::class)
class TechParamDto(
        val id: String? = null,
        val name: String? = null,
        val description: String? = null,
        val priority: Double? = null,
        val units: Set<UnitTypeDto>? = null,
)
