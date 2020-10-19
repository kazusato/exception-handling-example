package dev.kazusato.exeptionhandlingexample.extsys.exception

abstract class AbstractExtsysException : RuntimeException {

	val targetSystem: String

	constructor(targetSystem: String) : super() {
		this.targetSystem = targetSystem
	}

	constructor(targetSystem: String, message: String?) : super(message) {
		this.targetSystem = targetSystem
	}

	constructor(targetSystem: String, message: String?, cause: Throwable?) : super(message, cause) {
		this.targetSystem = targetSystem
	}

	constructor(targetSystem: String, cause: Throwable?) : super(cause) {
		this.targetSystem = targetSystem
	}

	constructor(targetSystem: String, message: String?, cause: Throwable?, enableSuppression: Boolean, writableStackTrace: Boolean)
			: super(message, cause, enableSuppression, writableStackTrace) {
		this.targetSystem = targetSystem
	}

}