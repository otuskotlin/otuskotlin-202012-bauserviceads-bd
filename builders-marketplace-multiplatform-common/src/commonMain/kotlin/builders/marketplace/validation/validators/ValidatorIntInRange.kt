package builders.marketplace.validation.validators

import builders.marketplace.validation.IValidator
import builders.marketplace.validation.ValidationFieldError
import builders.marketplace.validation.ValidationResult

class ValidatorIntInRange<T: Comparable<T>>(
    private val field: String = "",
    private val min: T,
    private val max: T
): IValidator<T> {
    override fun validate(chunk: T): ValidationResult {
        return if (chunk in min..max) {
            ValidationResult.SUCCESS
        } else {
            ValidationResult(
                errors = listOf(
                    ValidationFieldError(
                        field = field,
                        message = "Value $chunk for field $field is out of range [$min, $max]"
                    )
                )
            )
        }
    }
}