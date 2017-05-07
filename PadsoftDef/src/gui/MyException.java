package gui;

public class MyException extends Exception{
	private static final long serialVersionUID = 1L;
	private String msg;
	
	/**
	 * Constructor
	 * @param message mensaje de la excepcion
	 */
	public MyException (String message) {
		msg = message;
	}
	@Override
	public String toString() {
		return msg;
	}
	
	
}