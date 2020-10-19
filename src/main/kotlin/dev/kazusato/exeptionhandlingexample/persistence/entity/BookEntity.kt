package dev.kazusato.exeptionhandlingexample.persistence.entity

import dev.kazusato.exeptionhandlingexample.domain.model.Book
import dev.kazusato.exeptionhandlingexample.domain.model.BookCategory
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "BOOK")
data class BookEntity(
		@Id
		@Column(name = "ID")
		var id: UUID,
		@Column(name = "TITLE", nullable = false, length = 200)
		var title: String,
		@Column(name = "AUTHOR", nullable = false, length = 200)
		var author: String,
		@Column(name = "PUBLISHER", nullable = false, length = 100)
		var publisher: String,
		@Column(name = "CATEGORY", nullable = true, length = 30)
		var category: String,
		@Column(name = "DESCRIPTION", nullable = true, columnDefinition = "TEXT")
		var description: String? = null
) {
	constructor(book: Book) : this(book.id, book.title, book.author, book.publisher, book.category.toString(), book.description)

	fun toBook(): Book {
		return Book(id, title, author, publisher, BookCategory.fromString(category), description)
	}
}