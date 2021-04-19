package builders.marketplace.pipelines.validation

import builders.marketplace.pipelines.IOperation
import builders.marketplace.pipelines.IOperationBuilder
import builders.marketplace.pipelines.PipelineDsl
import builders.marketplace.pipelines.Predicate
import builders.marketplace.validation.ValidationResult

@PipelineDsl
class ValidationBuilder<C>: IOperationBuilder<C> {
    private var checkPrecondition: Predicate<C> = { true }
    private var errorHandler: C.(ValidationResult) -> Unit = {}
    private val validators: MutableList<IValidationOperation<C,*>> = mutableListOf()

    fun startIf(block: Predicate<C>) {
        checkPrecondition = block
    }

    fun errorHandler(block: C.(ValidationResult) -> Unit) {
        errorHandler = block
    }

    fun <T> validate(block: ValidationOperationBuilder<C,T>.() -> Unit) {
        val builder = ValidationOperationBuilder<C,T>(errorHandler).apply(block)
        validators.add(builder.build())
    }

    override fun build(): IOperation<C> = PipelineValidation(validators)
}
