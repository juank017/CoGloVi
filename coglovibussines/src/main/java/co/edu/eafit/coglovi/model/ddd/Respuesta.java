package co.edu.eafit.coglovi.model.ddd;

import java.util.Date;

import co.edu.eafit.coglovi.model.usuario.UsuarioAPP;

/**
 * @version 1.0
 * @created 14-sep.-2014 5:26:58 p. m.
 */
public class Respuesta {

	private Date fecha;
	private String respuesta;
	public UsuarioAPP usuario;

	public Respuesta(){

	}

	public void finalize() throws Throwable {

	}
}//end Respuesta