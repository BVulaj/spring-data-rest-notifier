/**
 * 
 */
package com.bvulaj.data.rest.event.exception;

/**
 * @author Brandon Vulaj
 *
 */
public class UndeliverableEventException extends RuntimeException {

	private static final long serialVersionUID = 2461097993237099328L;

	/**
	 * @param message
	 * @param cause
	 */
	public UndeliverableEventException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public UndeliverableEventException(Throwable cause) {
		super(cause);
	}

}
