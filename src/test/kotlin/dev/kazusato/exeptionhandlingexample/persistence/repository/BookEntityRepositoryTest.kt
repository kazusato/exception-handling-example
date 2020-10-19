package dev.kazusato.exeptionhandlingexample.persistence.repository

import dev.kazusato.exeptionhandlingexample.domain.model.BookCategory
import dev.kazusato.exeptionhandlingexample.persistence.entity.BookEntity
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
class BookEntityRepositoryTest {

	@Autowired
	private lateinit var repo: BookEntityRepository

	@Test
	fun testSave() {
		val book = BookEntity(UUID.randomUUID(), "hello world", "Programmer", "Programmer publishing", BookCategory.COMPUTER.toString())
		repo.save(book)
	}

}