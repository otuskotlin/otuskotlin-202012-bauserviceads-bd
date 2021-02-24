package builders.marketplace.transport.multiplatform.models.advert.options

import kotlinx.serialization.Serializable

@Serializable
class TechParamDto(
        val id: String? = null,
        val name: String? = null,
        val description: String? = null,
        val priority: Double? = null,
        val units: Set<UnitTypeDto>? = null,
)