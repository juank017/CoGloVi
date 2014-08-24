package co.edu.eafit.coglovi.dao.alertas;

import java.util.List;

import co.edu.eafit.coglovi.model.alerta.EnvioAlertaUsuario;
import co.edu.eafit.coglovi.model.alerta.EstadoEnvio;
import co.edu.eafit.coglovi.model.alerta.UserAlert;

public interface EnvioAlertasDao {

	/**
	 * Registra en la tabla envio_alerta_usuario<br>
	 * Creado el 19/06/2014 a las  17:44:47 <br>
	 * @param envioAlertaUsuario
	 */
	void registrarEnvioAlerta(EnvioAlertaUsuario envioAlertaUsuario);

	/**
	 * Obtiene las alertas pendientes para ser enviadas o que no se entregaron<br>
	 * Creado el 20/06/2014 a las  14:56:46 <br>
	 * @return List<EstadoEnvio>
	 */
	List<EstadoEnvio> findAlertasEnvioParse();

	/**
	 * Consulta el EnvioAlertaUsuario por usuario y alerta<br>
	 * Creado el 11/07/2014 a las  7:48:54 <br>
	 * @param userAlert
	 * @return EnvioAlertaUsuario
	 */
	EnvioAlertaUsuario findEnvioByAlertaAndUsuario(UserAlert userAlert);

	/**
	 * Actualiza el directorio de la alerta <br>
	 * Creado el 11/07/2014 a las  7:49:30 <br>
	 * @param envioAlerta
	 */
	void updateDirectorioAlerta(EnvioAlertaUsuario envioAlerta);

}
