package builders.marketplace.api.routes

import builders.marketplace.api.controllers.advertRoutes
import builders.marketplace.api.service.AdvertService
import builders.marketplace.business.logic.backend.AdvertCrud
import io.ktor.application.*
import io.ktor.routing.*

fun Application.registerAdvertRoutes(advertService: AdvertService) {
    routing {
        advertRoutes(advertService)
    }
}
