package builders.marketplace.pipelines

class Operation<T>
private constructor(
    private val checkPrecondition: Predicate<T>,
    private val runOperation: Runnable<T>,
    private val handleError: ErrorHandler<T>
) : IOperation<T> {
    override suspend fun execute(context: T) {
        kotlin.runCatching {
            if (checkPrecondition(context))
                runOperation(context)
        }.onFailure { handleError(context, it) }
    }

    @PipelineDsl
    class Builder<T> : IOperationBuilder<T> {
        private var checkPrecondition: Predicate<T> = { true }
        private var runOperation: Runnable<T> = { }
        private var handleError: ErrorHandler<T> = { throw it }

        fun startIf(block: Predicate<T>) {
            checkPrecondition = block
        }

        fun executeOperation(block: Runnable<T>) {
            runOperation = block
        }

        fun onError(block: ErrorHandler<T>) {
            handleError = block
        }

        override fun build(): Operation<T> =
            Operation(checkPrecondition, runOperation, handleError)
    }
}
