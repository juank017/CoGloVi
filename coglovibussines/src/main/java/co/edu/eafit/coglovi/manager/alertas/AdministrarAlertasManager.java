package co.edu.eafit.coglovi.manager.alertas;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import co.edu.eafit.coglovi.exception.QxException;
import co.edu.eafit.coglovi.model.alerta.Alerta;
import co.edu.eafit.coglovi.model.alerta.EstadoEnvio;
import co.edu.eafit.coglovi.model.alerta.UserAlert;

public interface AdministrarAlertasManager {

	/**
	 * Se encarga de registrar la alerta y su estado inicial<br>
	 * Creado el 19/06/2014 a las  17:42:46 <br>
	 * @param alerta
	 * @throws Exception
	 */
	void registrarInforAlerta(Alerta alerta)  throws QxException;

	/**
	 * Consuta si una alerta existe<br>
	 * Creado el 20/06/2014 a las  11:22:32 <br>
	 * @param identificadorAlerta
	 * @return
	 */
	boolean existeAlertaIdentificacion(String identificadorAlerta);

	/**
	 * TODO: descripción del método <br>
	 * Creado el 20/06/2014 a las  15:00:38 <br>
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	void enviarAlertasParse() throws ClientProtocolException, IOException;

	/**
	 * Obtiene una alerta especifica registrada a un usuario<br>
	 * Creado el 25/06/2014 a las  15:39:53 <br>
	 * @param userAlert
	 * @return estadoEnvio
	 */
	EstadoEnvio findAlertaByUsuarioAlerta(UserAlert userAlert);

	/**
	 * Se encarga de registrar el estado de la alerta a entregada<br>
	 * Creado el 25/06/2014 a las  16:33:46 <br>
	 * @param estadoEnvio
	 */
	void registrarEstadoEntregado(EstadoEnvio estadoEnvio);

	/**
	 * Obtiene una lista de alertas asociadas a un usuario<br>
	 * Creado el 10/07/2014 a las  10:41:17 <br>
	 * @param idUsuario
	 * @return List<EstadoEnvio>
	 */
	List<EstadoEnvio> findAlertasByUsuario(Long idUsuario);

	/**
	 * Actualiza el directorio de una alerta<br>
	 * Creado el 10/07/2014 a las  17:46:50 <br>
	 * @param userAlert
	 * @throws Exception 
	 */
	void modificarDirectorioAlerta(UserAlert userAlert) throws Exception;

}
