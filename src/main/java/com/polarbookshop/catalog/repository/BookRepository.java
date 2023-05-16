package com.polarbookshop.catalog.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.polarbookshop.catalog.domain.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

	Optional<Book> findByIsbn(String isbn);
	boolean existsByIsbn(String isbn);

	@Modifying
	@Transactional
	@Query("delete from Book where isbn = :isbn")
	void deleteByIsbn(String isbn);

}
