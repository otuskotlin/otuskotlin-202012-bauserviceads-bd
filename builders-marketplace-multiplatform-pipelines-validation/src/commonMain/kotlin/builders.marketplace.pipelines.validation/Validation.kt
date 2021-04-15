package builders.marketplace.pipelines.validation

import builders.marketplace.pipelines.Pipeline

fun <C> Pipeline.Builder<C>.validation(block: ValidationBuilder<C>.() -> Unit){
    execute(ValidationBuilder<C>().apply(block).build())
}