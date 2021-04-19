package builders.marketplace.api.service

import builders.marketplace.api.config.jsonMapperConfig
import builders.marketplace.api.routes.registerAdvertRoutes
import builders.marketplace.api.routes.registerCustomerRoutes
import builders.marketplace.business.logic.backend.AdvertCrud
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.serialization.*

fun Application.module(testing: Boolean = false) {
    val advertCrud = AdvertCrud()
    val advertService = AdvertService(advertCrud)

    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        header("MyCustomHeader")
        allowCredentials = true
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }

    install(ContentNegotiation) {
        json(
            contentType = ContentType.Application.Json,
            json = jsonMapperConfig,
        )
    }
    registerAdvertRoutes(advertService)
    registerCustomerRoutes()
}
