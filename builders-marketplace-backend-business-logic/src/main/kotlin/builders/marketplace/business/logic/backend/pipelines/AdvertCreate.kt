package builders.marketplace.business.logic.backend.pipelines

import builders.marketplace.business.logic.backend.operations.CompletePipeline
import builders.marketplace.business.logic.backend.operations.InitializePipeline
import builders.marketplace.business.logic.backend.operations.stubs.AdvertCreateStub
import builders.marketplace.context.AdvertBackendContext
import builders.marketplace.pipelines.IOperation
import builders.marketplace.pipelines.pipeline
import builders.marketplace.pipelines.validation.validation
import builders.marketplace.validation.validators.ValidatorStringNonEmpty

object AdvertCreate: IOperation<AdvertBackendContext> by pipeline({
  execute(InitializePipeline)

  execute(AdvertCreateStub)

  validation {
    validate<String?> {
      validator(ValidatorStringNonEmpty(field = "name", message = "Advert name can not be empty"))
      on { requestAdvert.name }
    }
    validate<String?> {
      validator(ValidatorStringNonEmpty(field = "locationId", message = "Location Id can not be empty"))
      on { requestAdvert.location.locationId}
    }
  }

  execute(CompletePipeline)
})
