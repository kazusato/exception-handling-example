package dev.kazusato.exeptionhandlingexample.domain.model

data class SearchCriteria(
		val category: BookCategory
) {
	constructor(categoryString: String?) : this(BookCategory.fromString(categoryString))
}