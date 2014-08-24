package co.edu.eafit.coglovi.manager.alertas;

import java.util.List;

import org.apache.http.client.ClientProtocolException;

import co.edu.eafit.coglovi.manager.notificacionmanager.SendDataPush;
import co.edu.eafit.coglovi.model.alerta.Alerta;
import co.edu.eafit.coglovi.model.alerta.EstadoEnvio;

public interface EstadoAlertaManager {

	/**
	 * Realiza el cambio de estado de una alerta<br>
	 * Creado el 20/06/2014 a las  7:44:42 <br>
	 * @param estadoEnvio
	 */
	void cambiaEstadoAlerta(EstadoEnvio estadoEnvio);

	
	/**
	 * Envia una alerta a Parse y actualiaz el estado <br>
	 * Creado el 24/06/2014 a las  10:31:35 <br>
	 * @param estadoEnvio
	 * @param push
	 * @param lista
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	void enviarAlertaParse(EstadoEnvio estadoEnvio,SendDataPush push, List<Alerta> lista)throws Exception;

}
