package co.edu.eafit.coglovi.gestionapp.dao.administrarusuarioplaca;

import java.util.List;

import co.edu.eafit.coglovi.gestionapp.model.usuariovehiculo.PlacaOut;
import co.edu.eafit.coglovi.gestionapp.model.usuariovehiculo.RegistroUsuarioVehiculo;
import co.edu.eafit.coglovi.gestionapp.model.usuariovehiculo.UsuarioAPP;
import co.edu.eafit.coglovi.gestionapp.model.usuariovehiculo.UsuarioVehiculo;
import co.edu.eafit.coglovi.gestionapp.model.usuariovehiculo.Vehiculo;

public interface AdministraUsuarioPlacaDao {

	/**
	 * Desactiva el registro de usuario por vehiculos, para cuaando existan alertas enviadas.
	 * 
	 * @param vehiculo
	 * @param usuarioAPP
	 * @param activo
	 * @throws Exception
	 */
	void activarUsuarioVehiculo(Vehiculo vehiculo, UsuarioAPP usuarioAPP, String activo) throws Exception;

	/**
	 * Consulta las placas por usuario y en caso de tener placas registradas como no propietario Se retornan los datos del propietario del vehiculo.
	 * 
	 * @param usuarioAPP
	 * @return
	 * @throws Exception
	 */
	List<PlacaOut> consultarPlacasByUser(UsuarioAPP usuarioAPP) throws Exception;

	/**
	 * Elimina la relacion del vehiculo con el usuario
	 * 
	 * @param vehiculo
	 * @param usuarioAPP
	 * @throws Exception
	 */
	void eliminarPlacaByUsuario(Vehiculo vehiculo, UsuarioAPP usuarioAPP) throws Exception;

	/**
	 * Verifica si la placa ya existe en bd
	 * 
	 * @param registroUsuarioVehiculo
	 * @return
	 * @throws Exception
	 */
	Boolean existePlaca(RegistroUsuarioVehiculo registroUsuarioVehiculo) throws Exception;

	/**
	 * Verifica si ya existe la placa relacionada al usuario
	 * 
	 * @param registroUsuarioVehiculo
	 * @return
	 */
	boolean existePlacaByUsuario(RegistroUsuarioVehiculo registroUsuarioVehiculo);

	/**
	 * Consulta el id vehiculo por placa
	 * 
	 * @param placa
	 * @return
	 * @throws Exception
	 */
	Long findIdVehiculoByPlaca(String placa) throws Exception;

	/**
	 * Se inactiva registro de usuario por vehiculos
	 * 
	 * @param vehiculo
	 * @param usuarioAPP
	 * @param activo
	 * @throws Exception
	 */
	void inactivarUsuarioVehiculo(Vehiculo vehiculo, UsuarioAPP usuarioAPP, String activo) throws Exception;

	/**
	 * Modifica la informacion del usuario logueado
	 * 
	 * @param usuarioAPP
	 * @throws Exception
	 */
	void modificarUsuarioAPP(UsuarioAPP usuarioAPP) throws Exception;

	/**
	 * Registra el usuario del aplicativo.
	 * 
	 * @param usuarioAPP
	 * @throws Exception
	 */
	void registrarUsuarioAPP(UsuarioAPP usuarioAPP) throws Exception;

	/**
	 * Registra las placas de los vehiculos por usuario.
	 * 
	 * @param usuarioVehiculo
	 * @throws Exception
	 */
	void registrarUsuarioVehiculo(UsuarioVehiculo usuarioVehiculo) throws Exception;

	/**
	 * Registra la placa del vehiculo por usuario
	 * 
	 * @param vehiculo
	 * @throws Exception
	 */
	void registrarVehiculo(Vehiculo vehiculo) throws Exception;
}
