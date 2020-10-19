package dev.kazusato.exeptionhandlingexample.presentation.dto

data class BaseResponseDto(
		val processStatus: ProcessStatus,
		val message: String? = null
)