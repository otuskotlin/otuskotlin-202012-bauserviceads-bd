package builders.marketplace.routes

import builders.marketplace.models.user.UserId
import builders.marketplace.models.user.UserModel
import builders.marketplace.models.user.customerStorage
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import java.util.UUID

fun Application.registerCustomerRoutes() {
    routing {
        customerRouting()
    }
}

fun Route.customerRouting() {
    route("/status") {
        get {
            call.respondText("online")
        }
    }
    route("/customers") {
        post {
            val customer = call.receive<UserModel>().copy(id = UserId(UUID.randomUUID().toString()))
            customerStorage.add(customer)
            call.respond(HttpStatusCode.Created, customer)
        }
    }
}
