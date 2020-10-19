package dev.kazusato.exeptionhandlingexample.domain.exception

class InvalidRequestTypeException : AbstractBusinessRuleException {
	constructor(message: String, cause: Throwable?) : super(message, cause)
}