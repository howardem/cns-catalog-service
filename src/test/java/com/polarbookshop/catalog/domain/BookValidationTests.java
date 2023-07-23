package com.polarbookshop.catalog.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

class BookValidationTests {

	private static Validator validator;

	@BeforeAll
	static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void whenAllFieldsCorrectThenValidationSucceeds() {
		Book book = Book.of("1234567890", "Title", "Author", 9.90, "Polarsophia");
		Set<ConstraintViolation<Book>> violations = validator.validate(book);
		assertThat(violations).isEmpty();
	}
	
	@Test
	void whenIsbnNotDefinedThenValidationFails() {
		Book book = Book.of("", "Title", "Author", 9.90, "Polarsophia");
		Set<ConstraintViolation<Book>> violations = validator.validate(book);
		assertThat(violations).hasSize(2);

		List<String> violationMessages = violations.stream()
				.map(ConstraintViolation::getMessage)
				.toList();
		assertThat(violationMessages)
			.contains("The book ISBN must be defined.")
			.contains("The ISBN format must be valid.");
	}

	@Test
	void whenIsbnDefinedButIncorrectThenValidationFails() {
		Book book = Book.of("a234567890", "Title", "Author", 9.90, "Polarsophia");
		Set<ConstraintViolation<Book>> violations = validator.validate(book);

		assertThat(violations).hasSize(1);
		assertThat(violations.iterator().next().getMessage())
			.isEqualTo("The ISBN format must be valid.");
	}

	@Test
	void whenTitleNotDefinedThenValidationFails() {
		Book book = Book.of("1234567890", "", "Author", 9.90, "Polarsophia");
		Set<ConstraintViolation<Book>> violations = validator.validate(book);

		assertThat(violations).hasSize(1);
		assertThat(violations.iterator().next().getMessage())
			.isEqualTo("The book title must be defined.");
	}

	@Test
	void whenAuthorNotDefinedThenValidationFails() {
		Book book = Book.of("1234567890", "Title", "", 9.90, "Polarsophia");
		Set<ConstraintViolation<Book>> violations = validator.validate(book);
		
		assertThat(violations).hasSize(1);
		assertThat(violations.iterator().next().getMessage())
			.isEqualTo("The book author must be defined.");
	}
	
	@Test
	void whenPriceNotDefinedThenValidationFails() {
		Book book = Book.of("1234567890", "Title", "Author", null, "Polarsophia");
		Set<ConstraintViolation<Book>> violations = validator.validate(book);
		
		assertThat(violations).hasSize(1);
		assertThat(violations.iterator().next().getMessage())
			.isEqualTo("The book price must be defined.");
	}
	
	@Test
	void whenPriceDefinedButZeroThenValidationFails() {
		Book book = Book.of("1234567890", "Title", "Author", 0.0, "Polarsophia");
		Set<ConstraintViolation<Book>> violations = validator.validate(book);
		
		assertThat(violations).hasSize(1);
		assertThat(violations.iterator().next().getMessage())
			.isEqualTo("The book price must be greater than zero.");
	}
	
	@Test
	void whenPriceDefinedButNegativeThenValidationFails() {
		Book book = Book.of("1234567890", "Title", "Author", -9.90, "Polarsophia");
		Set<ConstraintViolation<Book>> violations = validator.validate(book);
		
		assertThat(violations).hasSize(1);
		assertThat(violations.iterator().next().getMessage())
			.isEqualTo("The book price must be greater than zero.");
	}

    @Test
    void whenPublisherIsNotDefinedThenValidationSucceeds() {
        Book book = Book.of("1234567890", "Title", "Author", 9.90,null);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).isEmpty();
    }

}