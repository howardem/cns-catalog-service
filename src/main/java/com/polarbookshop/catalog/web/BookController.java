package com.polarbookshop.catalog.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.polarbookshop.catalog.domain.Book;
import com.polarbookshop.catalog.service.BookService;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {

	private final BookService bookService;

	@GetMapping
	public Iterable<Book> getBooks() {
		return this.bookService.getBooks();
	}

	@GetMapping("{isbn}")
	public Book geBooktByIsbn(@PathVariable String isbn) {
		return this.bookService.getBook(isbn);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Book createBook(@Valid @RequestBody Book book) {
		return this.bookService.createBook(book);
	}

	@DeleteMapping("{isbn}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String isbn) {
		this.bookService.deleteBook(isbn);
	}

	@PutMapping("{isbn}")
	public Book put(@PathVariable String isbn, @Valid @RequestBody Book book) {
		return this.bookService.updateBook(isbn, book);
	}

}
