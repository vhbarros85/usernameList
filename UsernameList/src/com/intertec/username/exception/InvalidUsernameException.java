/**
 * 
 */
package com.intertec.username.exception;

/**
 * Exception class when an username is invalid
 * @author vitor barros
 *
 */
public class InvalidUsernameException extends Exception {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -7377149948486120697L;

	/**
	 * Uses superclass constructor. 
	 */
	public InvalidUsernameException() {
		super();
	}

	/**
	 * It uses superclass constructor. 
	 * @param message
	 */
	public InvalidUsernameException(String message) {
		super(message);
	}

	/**
	 * It uses superclass constructor. 
	 * @param cause
	 */
	public InvalidUsernameException(Throwable cause) {
		super(cause);
	}

	/**
	 * It uses superclass constructor. 
	 * @param message
	 * @param cause
	 */
	public InvalidUsernameException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * It uses superclass constructor.  
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public InvalidUsernameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
