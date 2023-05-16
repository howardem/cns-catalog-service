package com.polarbookshop.catalog.service;

import org.springframework.stereotype.Service;

import com.polarbookshop.catalog.domain.Book;
import com.polarbookshop.catalog.exception.BookAlreadyExistsException;
import com.polarbookshop.catalog.exception.BookNotFoundException;
import com.polarbookshop.catalog.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

	private final BookRepository bookRepository;

	public Iterable<Book> getBooks() {
		return this.bookRepository.findAll();
	}
	
	public Book getBook(String isbn) {
		return this.bookRepository.findByIsbn(isbn)
				.orElseThrow(() -> new BookNotFoundException(isbn));
	}

	public Book createBook(Book book) {
		if (this.bookRepository.existsByIsbn(book.isbn())) {
			throw new BookAlreadyExistsException(book.isbn());
		}

		return this.bookRepository.save(book);
	}
	
	public void deleteBook(String isbn) {
		this.bookRepository.deleteByIsbn(isbn);
	}

	public Book updateBook(String isbn, Book book) {
		return this.bookRepository.findByIsbn(book.isbn())
				.map(existingBook -> {
					var bookToUpdate = new Book(existingBook.id(), existingBook.isbn(), book.title(), book.author(), book.price(), existingBook.createdDate(), existingBook.lastModifiedDate(), existingBook.version());
					
					return this.bookRepository.save(bookToUpdate);
				})
				.orElseGet(() -> createBook(book));
	}

}
