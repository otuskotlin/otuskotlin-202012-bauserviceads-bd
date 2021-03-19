package builders.marketplace.api.routes

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing

fun Application.registerCustomerRoutes() {
    routing {
        statusCheck()
    }
}

fun Route.statusCheck() {
    route("/status") {
        get {
            call.respondText("online")
        }
    }
}
