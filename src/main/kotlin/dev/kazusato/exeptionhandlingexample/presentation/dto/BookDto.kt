package dev.kazusato.exeptionhandlingexample.presentation.dto

import com.fasterxml.jackson.annotation.JsonInclude
import dev.kazusato.exeptionhandlingexample.domain.model.Book
import dev.kazusato.exeptionhandlingexample.domain.model.BookCategory
import dev.kazusato.exeptionhandlingexample.presentation.validation.RegistrationGroup
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Null

@JsonInclude(JsonInclude.Include.NON_NULL)
data class BookDto(
		@field:Null(groups = [RegistrationGroup::class])
		val id: UUID?,
		@field:NotBlank
		val title: String,
		@field:NotBlank
		val author: String,
		@field:NotBlank
		val publisher: String,
		val category: String? = null,
		val description: String? = null
) {
	constructor(book: Book) : this(book.id, book.title, book.author, book.publisher, book.category.toString(), book.description)

	fun toBook(): Book {
		return Book(title, author, publisher, BookCategory.fromString(category), description)
	}
}