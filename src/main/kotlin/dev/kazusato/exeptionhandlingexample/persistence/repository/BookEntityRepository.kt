package dev.kazusato.exeptionhandlingexample.persistence.repository

import dev.kazusato.exeptionhandlingexample.persistence.entity.BookEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface BookEntityRepository : JpaRepository<BookEntity, UUID> {

	fun findByCategory(category: String): List<BookEntity>

}