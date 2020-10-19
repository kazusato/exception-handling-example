package dev.kazusato.exeptionhandlingexample.domain.model

import java.util.*

data class Book(
		val id: UUID,
		val title: String,
		val author: String,
		val publisher: String,
		val category: BookCategory,
		val description: String? = null
) {
	constructor(title: String, author: String, publisher: String, category: BookCategory, description: String?)
			: this(UUID.randomUUID(), title, author, publisher, category, description)
}
