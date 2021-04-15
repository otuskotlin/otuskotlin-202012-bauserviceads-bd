package builders.marketplace.business.logic.backend.operations.stubs

import builders.marketplace.context.AdvertBackendContext
import builders.marketplace.context.AdvertBackendContextStatus
import builders.marketplace.context.AdvertStubCase
import builders.marketplace.models.advert.AdvertId
import builders.marketplace.pipelines.IOperation
import builders.marketplace.pipelines.pipeline
import java.time.Instant
import java.util.*

object AdvertCreateStub : IOperation<AdvertBackendContext> by pipeline({
    startIf { stubCase == AdvertStubCase.ADVERT_CREATE_SUCCESS }
    execute {
        advertResponse = requestAdvert.copy(
            id = AdvertId(UUID.randomUUID().toString()),
            createdAt = Instant.now().epochSecond,
            lastTimeModifiedAt = Instant.now().epochSecond,
        )
        status = AdvertBackendContextStatus.FINISHING
    }
})
