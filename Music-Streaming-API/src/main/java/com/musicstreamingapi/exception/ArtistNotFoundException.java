package com.musicstreamingapi.exception;

public class ArtistNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8033377134845248934L;

	public ArtistNotFoundException(String message) {
		super(message);
	}
}
