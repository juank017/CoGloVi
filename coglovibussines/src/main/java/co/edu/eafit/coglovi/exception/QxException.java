package co.edu.eafit.coglovi.exception;


/**Exception Generica empleada para deshacer las transacciones  <br>
 * Creado el 22/05/2012 a las  13:50:13 <br>
 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
 */
public class QxException extends Exception {
	private int codException;

	/**
	 * 
	 */
	public QxException() {
		
	}

	/**
	 * @param message
	 */
	public QxException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public QxException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public QxException(String message, Throwable cause) {
		super(message, cause);
	}
	/**
	 * Constructor sobrecargado para recibir un codigo que indica el tipo de tratamiento que se puede hacer en las capas inferiores
	 * @param message
	 * @param cause
	 * @param codException
	 */
	public QxException(String message, Throwable cause,int codException) {
		super(message, cause);
		this.codException=codException;
	}
	/**
	 * Obtiene el codigo de exception lanzado en las capas superiores
	 * @return
	 */
	public int getCodException() {
		return codException;
	}
}

