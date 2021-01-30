package builders.marketplace.routes

import builders.marketplace.models.UserId
import builders.marketplace.models.UserModel
import builders.marketplace.models.customerStorage
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.*
import java.util.*

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