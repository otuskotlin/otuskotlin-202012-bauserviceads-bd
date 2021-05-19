package builders.marketplace.transport.multiplatform.models.common

import kotlinx.serialization.Serializable

@Serializable
enum class ResponseStatusDto {
    SUCCESS,
    BAD_REQUEST,
    NOT_FOUND,
    FAILED,
    NONE
}
