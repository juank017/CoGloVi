package co.edu.eafit.coglovi.manager.correo;

import co.edu.eafit.coglovi.model.usuariovehiculo.UsuarioAPP;

public interface EnvioCorreoManager {
	
	/**
	 * Envia un correo electronico en el proceso de recuperacion de la clave, a peticion del usuario logueado.
	 * @param usuarioAPP
	 * @throws Exception
	 */
	void enviarCorreoRecuperarClave(UsuarioAPP usuarioAPP) throws Exception;
	
	/**
	 * Genera un correo electronico con la bienvenida del registro del usuario en la aplicacion.
	 * @param usuarioAPP
	 * @throws Exception
	 */
	void enviarCorreoRegistrarse(UsuarioAPP usuarioAPP) throws Exception;

}
