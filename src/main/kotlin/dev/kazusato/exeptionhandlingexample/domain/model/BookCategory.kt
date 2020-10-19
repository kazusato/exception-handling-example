package dev.kazusato.exeptionhandlingexample.domain.model

import java.lang.IllegalArgumentException

enum class BookCategory {
	COMPUTER,
	TRAVEL,
	SPORTS,
	ALL;

	companion object {
		fun fromString(categoryString: String?): BookCategory {
			return if (categoryString == null) {
				BookCategory.ALL
			} else {
				BookCategory.valueOf(categoryString)
			}
		}
	}
}