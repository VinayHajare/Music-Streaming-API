package com.musicstreamingapi.exception;

public class SongNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2442366066712072795L;

	public SongNotFoundException(String message) {
		super(message);
	}
	
}
