package builders.marketplace.api.controllers

import builders.marketplace.api.routes.handleRoute
import builders.marketplace.api.service.AdvertService
import builders.marketplace.business.logic.backend.AdvertCrud
import builders.marketplace.transport.multiplatform.models.advert.AdvertDto
import builders.marketplace.transport.multiplatform.models.advert.options.AdditionalDetailDto
import builders.marketplace.transport.multiplatform.models.advert.options.AdvertTypeDto
import builders.marketplace.transport.multiplatform.models.advert.options.LocationDto
import builders.marketplace.transport.multiplatform.models.advert.request.RequestAdvertCreate
import builders.marketplace.transport.multiplatform.models.advert.request.RequestAdvertDelete
import builders.marketplace.transport.multiplatform.models.advert.request.RequestAdvertRead
import builders.marketplace.transport.multiplatform.models.advert.request.RequestAdvertUpdate
import builders.marketplace.transport.multiplatform.models.advert.response.ResponseAdvertCreate
import builders.marketplace.transport.multiplatform.models.advert.response.ResponseAdvertDelete
import builders.marketplace.transport.multiplatform.models.advert.response.ResponseAdvertRead
import builders.marketplace.transport.multiplatform.models.advert.response.ResponseAdvertUpdate
import builders.marketplace.transport.multiplatform.models.common.MarketplaceMessage
import builders.marketplace.transport.multiplatform.models.common.ResponseStatusDto
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.pipeline.*
import org.slf4j.LoggerFactory
import java.time.Instant
import java.util.*

fun Route.advertRoutes(advertService: AdvertService) {
    route("/advert") {
        post("/create") {
            handleRoute<RequestAdvertCreate, ResponseAdvertCreate> { query ->
                advertService.create(this, query)
            }
        }
        post("/read") {
            handleRoute<RequestAdvertRead, ResponseAdvertRead> { query ->
                advertService.read(this, query)
            }
        }
        post("/update") {
            handleRoute<RequestAdvertUpdate, ResponseAdvertUpdate> { query ->
                advertService.update(this, query)
            }
        }
        post("/delete") {
            handleRoute<RequestAdvertDelete, ResponseAdvertDelete> { query ->
                advertService.delete(this, query)
            }
        }
    }
}

