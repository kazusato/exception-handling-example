package dev.kazusato.exeptionhandlingexample.extsys.exception

import org.springframework.http.HttpStatus

class ExampleExtsysException : AbstractExtsysException {

	companion object {
		private const val TARGET_SYSTEM = "ExampleSystem"
	}

	constructor() : super(TARGET_SYSTEM)
	constructor(message: String?) : super(TARGET_SYSTEM, message)
	constructor(message: String?, cause: Throwable?) : super(TARGET_SYSTEM, message, cause)
	constructor(cause: Throwable?) : super(TARGET_SYSTEM, cause)
}