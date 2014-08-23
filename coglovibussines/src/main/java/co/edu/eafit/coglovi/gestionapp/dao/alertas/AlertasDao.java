package co.edu.eafit.coglovi.gestionapp.dao.alertas;

import java.util.List;

import co.edu.eafit.coglovi.gestionapp.model.alerta.Alerta;
import co.edu.eafit.coglovi.gestionapp.model.alerta.EstadoEnvio;
import co.edu.eafit.coglovi.gestionapp.model.alerta.UserAlert;
import co.edu.eafit.coglovi.gestionapp.model.usuariovehiculo.UsuarioVehiculo;

public interface AlertasDao {

	/**
	 * Verifica si existe informacion del envio de alertas por usuario y vehiculo
	 * 
	 * @param idUser
	 * @param idVehiculo
	 * @return
	 */
	boolean existeAlertaByUserVehiculo(Long idUser, Long idVehiculo);

	/**
	 * Valida si existe una alerta con su identificacion <br>
	 * Creado el 18/06/2014 a las 17:04:40 <br>
	 * 
	 * @param identificadorAlerta
	 * @return
	 * @throws Exception
	 */
	boolean existeAlertaIdentificacion(String identificadorAlerta);

	/**
	 * Consulta una alerta especifica registrada a un usuario<br>
	 * Creado el 25/06/2014 a las 15:43:31 <br>
	 * 
	 * @param userAlert
	 * @return estadoEnvio
	 */
	EstadoEnvio findAlertaByUsuarioAlerta(UserAlert userAlert);

	/**
	 * Obtiene una lista de alertas asociadas a un usuario<br>
	 * Creado el 10/07/2014 a las 10:42:47 <br>
	 * 
	 * @param idUsuario
	 * @return List<EstadoEnvio>
	 */
	List<EstadoEnvio> findAlertasByUsuario(Long idUsuario);

	/**
	 * Obtiene los usuarios registrados asociados a un canal e identificacion<br>
	 * Creado el 25/06/2014 a las 15:43:51 <br>
	 * 
	 * @param alerta
	 * @return
	 */
	List<UsuarioVehiculo> findUsuarioVehiculo(Alerta alerta);

	/**
	 * Registra una alerta en la base de datos<br>
	 * Creado el 17/06/2014 a las 16:37:19 <br>
	 * 
	 * @param alerta
	 * @throws Exception
	 */
	void registrarAlerta(Alerta alerta);

}
