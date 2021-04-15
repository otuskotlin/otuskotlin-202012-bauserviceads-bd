package builders.marketplace.validation.validators

import builders.marketplace.validation.IValidator
import builders.marketplace.validation.ValidationFieldError
import builders.marketplace.validation.ValidationResult

class ValidatorStringNonEmpty(
    private val field: String = "",
    private val message: String = "String has to be not empty") : IValidator<String?> {
    override fun validate(chunk: String?): ValidationResult {
        return if (chunk.isNullOrBlank()) {
            ValidationResult(
                errors = listOf(
                    ValidationFieldError(
                        field = field,
                        message = message
                    )
                )
            )
        } else {
            ValidationResult.SUCCESS
        }
    }
}