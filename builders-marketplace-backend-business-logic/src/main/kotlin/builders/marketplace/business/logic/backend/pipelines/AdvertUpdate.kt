package builders.marketplace.business.logic.backend.pipelines

import builders.marketplace.business.logic.backend.operations.CompletePipeline
import builders.marketplace.business.logic.backend.operations.InitializePipeline
import builders.marketplace.business.logic.backend.operations.stubs.AdvertUpdateStub
import builders.marketplace.context.AdvertBackendContext
import builders.marketplace.pipelines.IOperation
import builders.marketplace.pipelines.pipeline
import builders.marketplace.pipelines.validation.validation
import builders.marketplace.validation.validators.ValidatorStringNonEmpty

object AdvertUpdate: IOperation<AdvertBackendContext> by pipeline ({
    execute(InitializePipeline)

    execute(AdvertUpdateStub)

    validation {
        validate<String?> {
            validator(ValidatorStringNonEmpty(field = "id", message = "Advert Id name can not be empty"))
            on { requestAdvert.id.id }
        }
        validate<String?> {
            validator(ValidatorStringNonEmpty(field = "ownerId", message = "Owner Id can not be empty"))
            on { requestAdvert.ownerId.id}
        }
    }

    execute(CompletePipeline)
})