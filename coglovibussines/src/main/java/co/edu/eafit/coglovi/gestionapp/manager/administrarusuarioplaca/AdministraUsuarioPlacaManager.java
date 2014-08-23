package co.edu.eafit.coglovi.gestionapp.manager.administrarusuarioplaca;

import java.util.List;

import co.edu.eafit.coglovi.gestionapp.exception.QxException;
import co.edu.eafit.coglovi.gestionapp.model.autenticausuario.RestResponse;
import co.edu.eafit.coglovi.gestionapp.model.usuariovehiculo.PlacasResponse;
import co.edu.eafit.coglovi.gestionapp.model.usuariovehiculo.RegistroUsuarioVehiculo;
import co.edu.eafit.coglovi.gestionapp.model.usuariovehiculo.UsuarioAPP;
import co.edu.eafit.coglovi.gestionapp.model.usuariovehiculo.Vehiculo;

public interface AdministraUsuarioPlacaManager {

	/**
	 * Administra los datos del UsuarioAPP para redireccionarlos a su respectiva consulta de placas por usuario
	 * 
	 * @param usuarioAPP
	 * @return
	 * @throws Exception
	 */
	PlacasResponse consultarPlacasByUser(UsuarioAPP usuarioAPP) throws QxException, Exception;

	/**
	 * Administra los datos del usuario con sus correspondientes placasa a desasociar.
	 * 
	 * @param usuarioAPP
	 * @param listPlacas
	 * @return
	 * @throws Exception
	 */
	RestResponse eliminarPlacaByUsuario(UsuarioAPP usuarioAPP, Vehiculo vehiculo) throws Exception;

	/**
	 * Administra la informacion del usuario logueado, para su correspondiente modificacion datos
	 * 
	 * @param usuarioAPP
	 * @return
	 * @throws Exception
	 */
	RestResponse modificarUsuario(UsuarioAPP usuarioAPP) throws Exception;

	/**
	 * Administra los datos del Usuario registrado en el sistema y el registro de placas
	 * 
	 * @param usuarioAPP
	 * @param listRegistroUsuarioVehiculo
	 * @return
	 * @throws Exception
	 */
	RestResponse registrarPlaca(UsuarioAPP usuarioAPP, List<RegistroUsuarioVehiculo> listRegistroUsuarioVehiculo) throws Exception;

	/**
	 * Administra los datos del UsuarioAPP y del vehiculo, y los redirecciona a sus correspondientes metodos de validacion y persistencia de datos.
	 * 
	 * @param usuarioAPP
	 * @param listRegistroUsuarioVehiculo
	 * @return
	 * @throws Exception
	 */
	RestResponse registrarse(UsuarioAPP usuarioAPP, List<RegistroUsuarioVehiculo> listRegistroUsuarioVehiculo) throws QxException, Exception;
}
