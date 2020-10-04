package edu.saby.msec.infra.exceptions;

/**
 * @author Soumya Banerjee
 *
 */
public class DataAccessException extends Exception {

	/**
	 * Serial Version Unique Id
	 */
	private static final long serialVersionUID = 1667533185555200773L;

	public DataAccessException() {
        super();
    }
	
	public DataAccessException(Exception e) {
        super(e);
    }
	
	public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
