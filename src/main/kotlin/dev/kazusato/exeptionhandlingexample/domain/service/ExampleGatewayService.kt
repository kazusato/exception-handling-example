package dev.kazusato.exeptionhandlingexample.domain.service

import dev.kazusato.exeptionhandlingexample.domain.model.DataRetrievalCondition
import dev.kazusato.exeptionhandlingexample.domain.model.ExampleData
import dev.kazusato.exeptionhandlingexample.domain.model.ExampleRequestType
import dev.kazusato.exeptionhandlingexample.extsys.dto.ExampleRequestExtsysDto
import dev.kazusato.exeptionhandlingexample.extsys.gateway.ExampleGateway
import org.springframework.stereotype.Service

@Service
class ExampleGatewayService(val gateway: ExampleGateway) {

	fun retrieveData(condition: DataRetrievalCondition): List<ExampleData> {
		val endpoint = if (condition.requestType == ExampleRequestType.LIST) {
			"/list"
		} else {
			"/details"
		}
		val req = ExampleRequestExtsysDto(endpoint)
		val resp = gateway.retrieveData(req)
		return resp.resultList.map { dto -> dto.toExampleData() }
	}

}