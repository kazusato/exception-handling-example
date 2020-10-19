package dev.kazusato.exeptionhandlingexample.domain.model

import dev.kazusato.exeptionhandlingexample.domain.exception.InvalidRequestTypeException

enum class ExampleRequestType {
	LIST,
	SHOW_DETAILS;

	companion object {
		fun fromString(typeString: String): ExampleRequestType {
			try {
				return ExampleRequestType.valueOf(typeString)
			} catch (e: IllegalArgumentException) {
				throw InvalidRequestTypeException("Invalid value for request type: ${typeString}", e)
			}
		}
	}
}