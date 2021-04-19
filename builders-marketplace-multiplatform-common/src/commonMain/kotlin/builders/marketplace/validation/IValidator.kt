package builders.marketplace.validation

interface IValidator<T> {
    infix fun validate(chunk: T): ValidationResult
}