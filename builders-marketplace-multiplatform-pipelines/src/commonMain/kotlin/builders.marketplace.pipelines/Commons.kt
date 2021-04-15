package builders.marketplace.pipelines

typealias Predicate<T> = suspend T.() -> Boolean

typealias Runnable<T> = suspend T.() -> Unit

typealias ErrorHandler<T> = suspend T.(Throwable) -> Unit