package builders.marketplace.business.logic.backend.operations.stubs

import builders.marketplace.context.AdvertBackendContext
import builders.marketplace.context.AdvertBackendContextStatus
import builders.marketplace.context.AdvertStubCase
import builders.marketplace.models.advert.AdvertModel
import builders.marketplace.models.advert.AdvertType
import builders.marketplace.models.advert.Money
import builders.marketplace.pipelines.IOperation
import builders.marketplace.pipelines.pipeline
import java.math.BigDecimal

object AdvertReadStub : IOperation<AdvertBackendContext> by pipeline({
    startIf { stubCase == AdvertStubCase.ADVERT_READ_SUCCESS }

    execute {
        status = AdvertBackendContextStatus.FINISHING
        requestAdvert = AdvertModel(
            id = requestAdvertId,
            name = "Stub Advert",
            description = "Stub description",
            price = Money(amount = BigDecimal.ONE),
            advertType = AdvertType.SELL
        )
    }
})
