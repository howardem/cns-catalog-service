package com.polarbookshop.catalog.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polarbookshop.catalog.config.PolarProperties;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HomeController {

	private final PolarProperties polarProperties;

	@GetMapping("/")
	public String getGreeting() {
		return this.polarProperties.getGreeting();
	}

}
