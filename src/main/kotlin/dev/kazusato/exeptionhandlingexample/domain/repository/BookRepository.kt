package dev.kazusato.exeptionhandlingexample.domain.repository

import dev.kazusato.exeptionhandlingexample.domain.exception.InvalidCategorySpecifiedException
import dev.kazusato.exeptionhandlingexample.domain.model.Book
import dev.kazusato.exeptionhandlingexample.domain.model.BookCategory
import dev.kazusato.exeptionhandlingexample.domain.model.SearchCriteria
import dev.kazusato.exeptionhandlingexample.persistence.entity.BookEntity
import dev.kazusato.exeptionhandlingexample.persistence.repository.BookEntityRepository
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException

@Repository
class BookRepository(val repo: BookEntityRepository) {

	fun searchBooks(criteria: SearchCriteria): List<Book> {
		val queryResult = if (criteria.category == BookCategory.ALL) {
			repo.findAll()
		} else {
			repo.findByCategory(criteria.category.toString())
		}
		return queryResult.map { entity -> entity.toBook() }
	}

	fun registerBook(book: Book) {
		if (book.category == BookCategory.ALL) {
			// ビジネスルール「カテゴリーALLは登録時は指定できない」に対する実装。
			// このルールに違反した場合、例外をスローし、登録処理を中断する。
			// 事象に即した例外クラスを作成してスローしている。
			throw InvalidCategorySpecifiedException("Category ALL is not permitted for registration.")
		}
		val bookEntity = BookEntity(book)
		repo.save(bookEntity)
	}

}