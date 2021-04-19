package builders.marketplace.pipelines

interface IOperation<T> {
    suspend fun execute(context: T)
}