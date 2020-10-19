package dev.kazusato.exeptionhandlingexample.extsys.gateway

import dev.kazusato.exeptionhandlingexample.extsys.dto.ExampleRequestExtsysDto
import dev.kazusato.exeptionhandlingexample.extsys.dto.ExampleResponseExtsysDto
import dev.kazusato.exeptionhandlingexample.extsys.exception.ExampleExtsysException
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException

@Component
class ExampleGateway(val restTemplateBuilder: RestTemplateBuilder) {

	companion object {
		private const val BASE_URL = "http://localhost:8888"
	}

	fun retrieveData(req: ExampleRequestExtsysDto): ExampleResponseExtsysDto {
		try {
			val restTemplate = restTemplateBuilder.build()
			return restTemplate.getForObject(BASE_URL + req.endpoint, ExampleResponseExtsysDto::class.java)
					?: throw ExampleExtsysException("Response body is empty.")
		} catch (e: HttpClientErrorException) {
			// 4xxエラー
			throw ExampleExtsysException(e)
		} catch (e: HttpServerErrorException) {
			// 5xxエラー
			throw ExampleExtsysException(e)
		}
	}

}