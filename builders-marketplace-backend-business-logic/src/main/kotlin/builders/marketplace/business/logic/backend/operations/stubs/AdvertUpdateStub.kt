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
import java.time.Instant

object AdvertUpdateStub : IOperation<AdvertBackendContext> by pipeline({
    startIf { stubCase == AdvertStubCase.ADVERT_UPDATE_SUCCESS }

    execute {
        advertResponse = AdvertModel(
            id = requestAdvertId,
            name = "Updated Stub Advert",
            description = "Updated Stub description",
            price = Money(amount = BigDecimal.ONE),
            advertType = AdvertType.SELL,
            lastTimeModifiedAt = Instant.now().epochSecond
        )
        status = AdvertBackendContextStatus.FINISHING
    }
})
