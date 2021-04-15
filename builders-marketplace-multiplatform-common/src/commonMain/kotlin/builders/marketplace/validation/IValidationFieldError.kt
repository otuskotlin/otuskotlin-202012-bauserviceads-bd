package builders.marketplace.validation

interface IValidationFieldError: IValidationError {
    val field: String
}