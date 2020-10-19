package dev.kazusato.exeptionhandlingexample.presentation.dto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class SearchBookResponseDto(
		val processStatus: ProcessStatus,
		val message: String? = null,
		val searchResult: List<BookDto>
)
