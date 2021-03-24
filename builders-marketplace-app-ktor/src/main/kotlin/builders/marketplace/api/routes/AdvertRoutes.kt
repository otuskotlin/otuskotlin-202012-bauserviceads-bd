package builders.marketplace.api.routes

import builders.marketplace.api.controllers.AdvertController
import io.ktor.application.*
import io.ktor.routing.*

fun Application.registerAdvertRoutes(advertController: AdvertController) {
    routing {
        advertRoutes(advertController)
    }
}

fun Route.advertRoutes(advertController: AdvertController) {
    route("/advert") {
        post("/create") {
            advertController.create(this)
        }
        post("/read") {
            advertController.read(this)
        }
        post("/update") {
            advertController.update(this)
        }
        post("/delete") {
            advertController.delete(this)
        }
    }
}
