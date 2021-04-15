package builders.marketplace.pipelines.validation

import builders.marketplace.pipelines.IOperation
import builders.marketplace.pipelines.Predicate

class PipelineValidation<C>(
    private val validations: List<IValidationOperation<C,*>>,
    private val checkPrecondition: Predicate<C> = { true },
) : IOperation<C> {
    override suspend fun execute(context: C) {
        if (context.checkPrecondition()) {
            validations.forEach {
                it.execute(context)
            }
        }
    }
}