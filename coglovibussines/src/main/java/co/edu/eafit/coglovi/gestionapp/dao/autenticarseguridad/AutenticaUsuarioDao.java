package co.edu.eafit.coglovi.gestionapp.dao.autenticarseguridad;

import org.springframework.dao.EmptyResultDataAccessException;

import co.edu.eafit.coglovi.gestionapp.model.autenticausuario.CambioClave;
import co.edu.eafit.coglovi.gestionapp.model.autenticausuario.User;
import co.edu.eafit.coglovi.gestionapp.model.usuariovehiculo.UsuarioAPP;

public interface AutenticaUsuarioDao {

	/**
	 * Atualiza la clave del usuario logueado
	 * 
	 * @param cambioClave
	 * @throws Exception
	 */
	void cambiarClave(CambioClave cambioClave) throws Exception;

	/**
	 * Verifica si existe el ID_USUARIO en BD
	 * 
	 * @param idUsuario
	 * @return
	 * @throws Exception
	 */
	Boolean existeIdUsusarioAPP(Long idUsuario) throws Exception;

	/**
	 * Verifica si existe la placa en bd
	 * 
	 * @param placa
	 * @return
	 * @throws Exception
	 */
	Boolean existePlaca(String placa) throws Exception;

	/**
	 * Verifica si ya existe un usuario, registrado con el usuarioAcceso que debe ser unico.
	 * 
	 * @param usuarioAcceso
	 * @return
	 * @throws Exception
	 */
	boolean existeUsuarioAcceso(String usuarioAcceso) throws Exception;

	/**
	 * Retorna todos los datos del Usuario logueado.
	 * 
	 * @param idUsuario
	 * @return
	 * @throws Exception
	 */
	UsuarioAPP findUserAppByIdUsuario(Long idUsuario) throws Exception;

	/**
	 * Consulta si el usuario que se loguea desde el aplicativo app, existe en la bd del software central.
	 * 
	 * @param user
	 * @return
	 * @throws EmptyResultDataAccessException
	 * @throws Exception
	 */
	Long validateLogin(User user) throws Exception;

}
