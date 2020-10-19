package dev.kazusato.exeptionhandlingexample.extsys.dto

import dev.kazusato.exeptionhandlingexample.domain.model.ExampleData

data class ResultDataExtsysDto(
		val name: String,
		val detail: String? = null
) {

	fun toExampleData(): ExampleData {
		return ExampleData(name, detail)
	}

}