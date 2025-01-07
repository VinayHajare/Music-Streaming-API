package com.musicstreamingapi.exception;

public class PlaylistNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1093271950175089282L;

	public PlaylistNotFoundException(String message) {
		super(message);
	}
}
