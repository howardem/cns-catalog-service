package com.polarbookshop.catalog.exception;

public class BookNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BookNotFoundException(String isbn) {
		super("The book with ISBN " + isbn + " was not found.");
	}

}