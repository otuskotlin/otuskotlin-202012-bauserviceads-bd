package builders.marketplace.pipelines.validation

import builders.marketplace.pipelines.pipeline
import builders.marketplace.test.runBlockingTest
import builders.marketplace.validation.IValidationError
import builders.marketplace.validation.ValidationResult
import builders.marketplace.validation.validators.ValidatorPostcode
import builders.marketplace.validation.validators.ValidatorStringNonEmpty
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ValidationTest {

    @Test
    fun pipelineValidation() {
        val pl = pipeline<TestContext> {

            validation {
                errorHandler { v: ValidationResult ->
                    if (v.isSuccess) return@errorHandler
                    errors.addAll(v.errors)
                }

                validate<String?> { validator(ValidatorStringNonEmpty()); on { x } }
                validate<String?> { validator(ValidatorPostcode()); on { postCode } }
            }
        }
        runBlockingTest {
            val ctx = TestContext()
            pl.execute(ctx)
            assertEquals(2, ctx.errors.size)
        }
    }
}

data class TestContext(
    val x: String = "",
    val postCode: String = "SE1 2Q_",
    val errors: MutableList<IValidationError> = mutableListOf()
)
