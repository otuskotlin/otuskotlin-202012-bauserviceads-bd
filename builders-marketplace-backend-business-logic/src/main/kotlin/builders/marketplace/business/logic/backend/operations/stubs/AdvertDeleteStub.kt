package builders.marketplace.business.logic.backend.operations.stubs

import builders.marketplace.context.AdvertBackendContext
import builders.marketplace.context.AdvertBackendContextStatus
import builders.marketplace.context.AdvertStubCase
import builders.marketplace.models.advert.AdvertModel
import builders.marketplace.pipelines.IOperation
import builders.marketplace.pipelines.pipeline
import java.time.Instant

object AdvertDeleteStub : IOperation<AdvertBackendContext> by pipeline({
    startIf { stubCase == AdvertStubCase.ADVERT_DELETE_SUCCESS }

    execute {
        status = AdvertBackendContextStatus.SUCCESS
        advertResponse = AdvertModel(
            id = requestAdvertId,
            lastTimeModifiedAt = Instant.now().epochSecond,
            deleted = true
        )

    }
})
