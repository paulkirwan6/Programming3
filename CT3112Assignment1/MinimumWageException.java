/*
 * Paul Kirwan
 * 17321313
 */

//Exception is thrown when the employee's earnings is below minimum wage
public class MinimumWageException extends Exception {
	
	public MinimumWageException(String error) {
		super(error);
	}
}
