package dev.kazusato.exeptionhandlingexample.domain.model

import dev.kazusato.exeptionhandlingexample.presentation.dto.ExampleDataDto

data class ExampleData(
		val name: String,
		val detail: String? = null
) {
	fun toExampleDataDto(): ExampleDataDto {
		return ExampleDataDto(name, detail)
	}
}