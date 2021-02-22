package builders.marketplace.transport.multiplatform.models.common

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = KSerializer::class)
abstract class MultiplatformMessage {
}