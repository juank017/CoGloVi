package co.edu.eafit.coglovi.manager.autenticarseguridad;

import co.edu.eafit.coglovi.model.autenticausuario.CambioClave;
import co.edu.eafit.coglovi.model.autenticausuario.RestResponse;
import co.edu.eafit.coglovi.model.autenticausuario.User;
import co.edu.eafit.coglovi.model.usuariovehiculo.UsuarioAPP;
import co.edu.eafit.coglovi.model.usuariovehiculo.UsuarioVehiculoResponse;

public interface AutenticaUsuarioManager {

	/**
	 * Verifica la consistencia de datos del objeto User y lo envia a la capa de persistencia para su correspondiente consulta.
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	UsuarioVehiculoResponse login(User user) throws Exception;
	
	/**
	 * Actualiza la clave del usuario logueado
	 * @param user
	 * @return
	 * @throws Exception
	 */
	RestResponse cambiarClave(CambioClave cambioClave) throws Exception;

	/**
	 * Se encarga de enviar una nueva clave al correo del usuario  <br>
	 * Creado el 3/07/2014 a las  11:31:16 <br>
	 * @param user
	 * @throws Exception
	 */
	void recuperarClave(UsuarioAPP user) throws Exception;
}
