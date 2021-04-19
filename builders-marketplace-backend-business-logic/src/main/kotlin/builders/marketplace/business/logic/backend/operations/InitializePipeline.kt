package builders.marketplace.business.logic.backend.operations

import builders.marketplace.context.AdvertBackendContext
import builders.marketplace.context.AdvertBackendContextStatus
import builders.marketplace.pipelines.IOperation
import builders.marketplace.pipelines.operation

object InitializePipeline : IOperation<AdvertBackendContext> by operation({
    startIf { status == AdvertBackendContextStatus.NONE }
    executeOperation { copy(status = AdvertBackendContextStatus.RUNNING) }
})
