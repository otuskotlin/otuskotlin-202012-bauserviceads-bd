package builders.marketplace.validation.validators

import builders.marketplace.validation.IValidator
import builders.marketplace.validation.ValidationFieldError
import builders.marketplace.validation.ValidationResult

class ValidatorPostcode(
    private val field: String = ""
) : IValidator<String?> {
    private val postcodeRegex =
        Regex("^(([A-Z][A-HJ-Y]?\\d[A-Z\\d]?|ASCN|STHL|TDCU|BBND|[BFS]IQQ|PCRN|TKCA) ?\\d[A-Z]{2}|BFPO ?\\d{1,4}|(KY\\d|MSR|VG|AI)[ -]?\\d{4}|[A-Z]{2} ?\\d{2}|GE ?CX|GIR ?0A{2}|SAN ?TA1)\$")

    override fun validate(chunk: String?): ValidationResult {
        return if (chunk?.let { postcodeRegex.matches(it) } == true) {
            ValidationResult.SUCCESS
        } else {
            ValidationResult(
                errors = listOf(
                    ValidationFieldError(
                        field = field,
                        message = "Postcode $chunk is not valid"
                    )
                )
            )
        }
    }
}