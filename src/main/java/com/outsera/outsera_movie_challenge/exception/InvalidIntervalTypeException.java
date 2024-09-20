package com.outsera.outsera_movie_challenge.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class InvalidIntervalTypeException extends RuntimeException {

	private String resourceName;
	private String type;

	public String getMessage() {
		if (resourceName == null || type == null)
			return null;
		return String.format("Resource '%s' with type '%s' is invalid!", resourceName, type);
	}

}