package co.edu.eafit.coglovi.gestionapp.dao.alertas;

import co.edu.eafit.coglovi.gestionapp.model.alerta.EstadoEnvio;

public interface EstadoEnvioDao {

	/**
	 * Registra un nuevo estado de envio alerta<br>
	 * Creado el 19/06/2014 a las  17:59:21 <br>
	 * @param estadoEnvio
	 */
	void registrarEstadoEnvio(EstadoEnvio estadoEnvio);

	/**
	 * Obtiene el estado actual de un envio alerta<br>
	 * Creado el 20/06/2014 a las  8:26:52 <br>
	 * @param idEstadoEnvio
	 * @return EstadoEnvio
	 */
	EstadoEnvio findEstadoActualAlerta(Long idEstadoEnvio);

	/**
	 * Cierra un estado de envio alerta<br>
	 * Creado el 20/06/2014 a las  8:27:05 <br>
	 * @param estadoEnvio
	 */
	void cerrarEstadoEnvio(EstadoEnvio estadoEnvio);

}
