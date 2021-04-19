package builders.marketplace.business.logic.backend.operations

import builders.marketplace.context.AdvertBackendContext
import builders.marketplace.context.AdvertBackendContextStatus.*
import builders.marketplace.pipelines.IOperation
import builders.marketplace.pipelines.operation
import builders.marketplace.pipelines.pipeline

object CompletePipeline : IOperation<AdvertBackendContext> by pipeline({
    operation {
        startIf { status in setOf(RUNNING, FINISHING) }
        executeOperation { copy(status = SUCCESS) }
    }
    operation {
        startIf { status != SUCCESS }
        executeOperation { copy(status = ERROR) }
    }
})
