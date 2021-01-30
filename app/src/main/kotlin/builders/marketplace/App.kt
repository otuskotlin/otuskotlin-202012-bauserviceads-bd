/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package builders.marketplace

import builders.marketplace.routes.registerCustomerRoutes
import io.ktor.application.*
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.json
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import java.util.concurrent.TimeUnit.SECONDS

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    install(ContentNegotiation) {
        gson()
    }
    registerCustomerRoutes()
}
