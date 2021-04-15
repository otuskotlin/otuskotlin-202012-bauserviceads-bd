package builders.marketplace.pipelines

interface IOperationBuilder<T> {
    fun build(): IOperation<T>
}