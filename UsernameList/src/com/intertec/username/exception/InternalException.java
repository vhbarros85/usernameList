/**
 * 
 */
package com.intertec.username.exception;

/**
 * Exception class when there is an internal error.
 * @author vitor barros
 *
 */
public class InternalException extends Exception {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 6269857503689943800L;

	/**
	 * Uses superclass constructor. 
	 */
	public InternalException() {
		super();
	}

	/**
	 * It uses superclass constructor. 
	 * @param message
	 */
	public InternalException(String message) {
		super(message);
	}

	/**
	 * It uses superclass constructor. 
	 * @param cause
	 */
	public InternalException(Throwable cause) {
		super(cause);
	}

	/**
	 * It uses superclass constructor. 
	 * @param message
	 * @param cause
	 */
	public InternalException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * It uses superclass constructor.  
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public InternalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
