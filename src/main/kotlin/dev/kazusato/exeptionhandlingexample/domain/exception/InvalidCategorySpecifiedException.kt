package dev.kazusato.exeptionhandlingexample.domain.exception

class InvalidCategorySpecifiedException : AbstractBusinessRuleException {
	constructor(message: String) : super(message)
	constructor(message: String, cause: Throwable) : super(message, cause)
	constructor(cause: Throwable) : super(cause)
}