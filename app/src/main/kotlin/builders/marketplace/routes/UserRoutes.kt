package builders.marketplace.routes

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.*

fun Application.registerCustomerRoutes() {
    routing {
        customerRouting()
    }
}

fun Route.customerRouting() {
    route("/") {
        get {
            call.respondText("Hello Kotlin")
        }
    }
}