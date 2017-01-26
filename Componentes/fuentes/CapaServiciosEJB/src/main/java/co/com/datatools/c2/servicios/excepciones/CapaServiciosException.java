package co.com.datatools.c2.servicios.excepciones;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class CapaServiciosException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CapaServiciosException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CapaServiciosException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CapaServiciosException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	
	

}
