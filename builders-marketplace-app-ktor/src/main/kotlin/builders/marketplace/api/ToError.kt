package builders.marketplace.api

import builders.marketplace.models.advert.AdvertError
import kotlinx.serialization.SerializationException
import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger("Throwable.toError")
fun Throwable.toError(): AdvertError = when (this) {
    is SerializationException -> AdvertError(message = "Request JSON syntax error: ${this.message}")
    is ClassCastException -> AdvertError(message = "Wrong data sent to the endpoint: ${this.message}")
    else -> {
        logger.error("Unknown exception", this)
        AdvertError(message = "Some exception is thrown: ${this.message}")
    }
}