package com.musicstreamingapi.exception;

public class EmailAlreadyExistsException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2657250036420509832L;

	public EmailAlreadyExistsException(String message) {
		super(message);
	}
}
