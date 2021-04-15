package builders.marketplace.business.logic.backend

import builders.marketplace.business.logic.backend.pipelines.AdvertCreate
import builders.marketplace.business.logic.backend.pipelines.AdvertDelete
import builders.marketplace.business.logic.backend.pipelines.AdvertRead
import builders.marketplace.business.logic.backend.pipelines.AdvertUpdate
import builders.marketplace.context.AdvertBackendContext

class AdvertCrud {
    suspend fun create(context: AdvertBackendContext) {
        AdvertCreate.execute(context)
    }

    suspend fun read(context: AdvertBackendContext)  {
        AdvertRead.execute(context)
    }

    suspend fun update(context: AdvertBackendContext) {
        AdvertUpdate.execute(context)
    }

    suspend fun delete(context: AdvertBackendContext) {
        AdvertDelete.execute(context)
    }
}