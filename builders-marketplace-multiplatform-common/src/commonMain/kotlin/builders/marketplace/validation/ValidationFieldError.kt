package builders.marketplace.validation

data class ValidationFieldError(
    override val field: String,
    override val message: String
) : IValidationError, IValidationFieldError