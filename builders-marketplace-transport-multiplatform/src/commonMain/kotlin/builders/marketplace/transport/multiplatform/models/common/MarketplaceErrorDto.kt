package builders.marketplace.transport.multiplatform.models.common

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable
data class MultiplatformErrorDto(
        val code: String? = null,
        val message: String? = null,
        val field: String? = null,
        val level: ErrorLevelDto? = null
)

@Serializable
enum class ErrorLevelDto {
    INFO,
    WARNING,
    ERROR
}

