package com.polarbookshop.catalog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "polar")
@Data
public class PolarProperties {

	/*
	 * A message to welcome users.
	 */
	private String greeting;

}
