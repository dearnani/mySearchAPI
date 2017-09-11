package com.wday.search.api.core;

/**
 * 
 * @author Narasimha
 *
 */
public class SearchAPIException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9005375485949437544L;

	public SearchAPIException() {
		super();
	}

	public SearchAPIException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SearchAPIException(String message, Throwable cause) {
		super(message, cause);
	}

	public SearchAPIException(String message) {
		super(message);
	}

	public SearchAPIException(Throwable cause) {
		super(cause);
	}

	
}
