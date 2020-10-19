package dev.kazusato.exeptionhandlingexample.presentation.api

import dev.kazusato.exeptionhandlingexample.domain.model.SearchCriteria
import dev.kazusato.exeptionhandlingexample.domain.repository.BookRepository
import dev.kazusato.exeptionhandlingexample.presentation.dto.BookDto
import dev.kazusato.exeptionhandlingexample.presentation.dto.BookRegistrationResponseDto
import dev.kazusato.exeptionhandlingexample.presentation.dto.ProcessStatus
import dev.kazusato.exeptionhandlingexample.presentation.dto.SearchBookResponseDto
import dev.kazusato.exeptionhandlingexample.presentation.validation.RegistrationGroup
import org.slf4j.LoggerFactory
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.transaction.Transactional

@RestController
@RequestMapping("/book")
@Validated
class BookApi(val repo: BookRepository) {

	companion object {
		private val logger = LoggerFactory.getLogger(this::class.java.enclosingClass)
	}

	@GetMapping(path = ["/list"], produces = ["application/json;charset=UTF-8"])
	fun searchBooks(@RequestParam("category") category: String?): SearchBookResponseDto {
		// 「nullableだが空文字は不可」というBean Validationのルールはない。
		// カスタムアノテーションは作れるが、今回はBean Validationでのチェックは行わず、ドメインモデル生成時に
		// チェックする（カテゴリーとして許容される文字列以外はエラー）方針とする。
		logger.debug("CategoryInRequest=${category}")
		val criteria = SearchCriteria(category)
		logger.debug("Criteria=${criteria}")

		val searchResult = repo.searchBooks(criteria)
		return SearchBookResponseDto(ProcessStatus.SUCCESS, null, searchResult.map { book -> BookDto(book) })
	}

	@PostMapping(consumes = ["application/json;charset=UTF-8"], produces = ["application/json;charset=UTF-8"])
	@Transactional
	fun registerBook(@Validated(RegistrationGroup::class) @RequestBody bookDto: BookDto): BookRegistrationResponseDto {
		repo.registerBook(bookDto.toBook())
		return BookRegistrationResponseDto()
	}

}