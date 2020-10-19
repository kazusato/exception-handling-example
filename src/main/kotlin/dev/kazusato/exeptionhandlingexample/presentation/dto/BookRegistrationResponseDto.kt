package dev.kazusato.exeptionhandlingexample.presentation.dto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class BookRegistrationResponseDto(
		val processStatus: ProcessStatus = ProcessStatus.SUCCESS,
		val message: String? = null
)