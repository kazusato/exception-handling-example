package dev.kazusato.exeptionhandlingexample.presentation.api

import dev.kazusato.exeptionhandlingexample.domain.model.DataRetrievalCondition
import dev.kazusato.exeptionhandlingexample.domain.model.ExampleRequestType
import dev.kazusato.exeptionhandlingexample.domain.service.ExampleGatewayService
import dev.kazusato.exeptionhandlingexample.presentation.dto.ExampleDataRetrievalResponseDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/example")
class ExampleExtsysGatewayApi(val service: ExampleGatewayService) {

	@GetMapping("/data", produces = ["application/json;charset=UTF-8"])
	fun retrieveData(@RequestParam("condition") conditionString: String): ExampleDataRetrievalResponseDto {
		val condition = DataRetrievalCondition(ExampleRequestType.fromString(conditionString))
		val resultList = service.retrieveData(condition)
		return ExampleDataRetrievalResponseDto(resultList.map { svcData -> svcData.toExampleDataDto() })
	}

}