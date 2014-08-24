package co.edu.eafit.coglovi.dao.usuarioapp;

import co.edu.eafit.coglovi.model.usuariovehiculo.UsuarioAPP;

public interface UsuarioAPPDao {

	
	/**
	 * Realiza el cambio de clave en la base de datos para el usuario y activa la opcion que lo obliga a cambiar de clave cuando ingrese<br>
	 * Creado el 3/07/2014 a las  9:57:26 <br>
	 * @param user
	 * @throws Exception
	 */
	void registrarRecuperacionClave(UsuarioAPP user)throws Exception;

	/**
	 * Obtiene la informacion del usuario<br>
	 * Creado el 3/07/2014 a las  10:02:46 <br>
	 * @param idUsuario
	 * @return
	 * @throws Exception
	 */
	UsuarioAPP findUsuarioAPPByIdUser(Long idUsuario)throws Exception;

	/**
	 * Obtiene la informacion del usuario <br>
	 * Creado el 4/07/2014 a las  12:40:08 <br>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	UsuarioAPP findUsuarioAPPByUserName(String user) throws Exception;

}
