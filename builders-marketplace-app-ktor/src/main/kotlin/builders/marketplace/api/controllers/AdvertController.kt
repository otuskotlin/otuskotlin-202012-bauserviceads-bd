package builders.marketplace.api.controllers

import builders.marketplace.api.service.AdvertService
import builders.marketplace.context.AdvertBackendContext
import builders.marketplace.context.AdvertBackendContextStatus
import builders.marketplace.transport.multiplatform.models.advert.request.RequestAdvertCreate
import builders.marketplace.transport.multiplatform.models.advert.request.RequestAdvertDelete
import builders.marketplace.transport.multiplatform.models.advert.request.RequestAdvertRead
import builders.marketplace.transport.multiplatform.models.advert.request.RequestAdvertUpdate
import builders.marketplace.transport.multiplatform.models.common.MarketplaceMessage

suspend fun service(advertService: AdvertService, query: MarketplaceMessage?, context: AdvertBackendContext): MarketplaceMessage? = when (query) {
    is RequestAdvertCreate -> advertService.create(context, query)
    is RequestAdvertRead -> advertService.read(context, query)
    is RequestAdvertUpdate -> advertService.update(context, query)
    is RequestAdvertDelete -> advertService.delete(context, query)
    else -> when (context.status) {
        AdvertBackendContextStatus.FAILING -> advertService.read(context, null)
        else -> null
    }
}
