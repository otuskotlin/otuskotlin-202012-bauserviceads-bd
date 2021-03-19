package builders.marketplace.api.routes

import builders.marketplace.api.service.AdvertService
import builders.marketplace.transport.multiplatform.models.advert.request.RequestAdvertCreate
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.registerAdvertRoutes() {
    routing {
        advertRoutes()
    }
}

fun Route.advertRoutes() {
    route("/advert") {
        post {
            val requestAdvertCreate = call.receive(RequestAdvertCreate::class)
            call.respond(AdvertService)
        }
    }
}
