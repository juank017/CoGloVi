package co.edu.eafit.gestionapp.restservice.alertas;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.edu.eafit.coglovi.gestionapp.exception.QxException;
import co.edu.eafit.coglovi.gestionapp.manager.alertas.AdministrarAlertasManager;
import co.edu.eafit.coglovi.gestionapp.model.Constantes;
import co.edu.eafit.coglovi.gestionapp.model.Constantes.Alertas.TipoEstadoAlerta;
import co.edu.eafit.coglovi.gestionapp.model.alerta.AlertaListResponse;
import co.edu.eafit.coglovi.gestionapp.model.alerta.AlertaResponse;
import co.edu.eafit.coglovi.gestionapp.model.alerta.AlertaVO;
import co.edu.eafit.coglovi.gestionapp.model.alerta.EstadoEnvio;
import co.edu.eafit.coglovi.gestionapp.model.alerta.UserAlert;
import co.edu.eafit.coglovi.gestionapp.model.autenticausuario.RestResponse;
import co.edu.eafit.coglovi.gestionapp.model.usuariovehiculo.UsuarioAPP;
import co.edu.eafit.coglovi.gestionapp.transversal.PropertiesManager;

import com.google.gson.Gson;

/**
 * Servicio que consulta las alertas relacionadas con los usuarios<br>
 * Creado el 8/07/2014 a las 15:59:24 <br>
 * 
 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
 */
@Controller
@RequestMapping("alertas")
public class AlertasRest {

	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private AdministrarAlertasManager administrarAlertasManager;

	/**
	 * Consulta la alerta y la registra como entregada <br>
	 * Creado el 25/06/2014 a las 16:38:55 <br>
	 * 
	 * @param idUsuario
	 * @param idAlerta
	 * @return AlertaResponse
	 */
	@RequestMapping(value = "/consultarAlerta/{idUsuario}/{idAlerta}", method = RequestMethod.GET)
	@Secured("ROLE_APP_FOTODETECCION_REST_C")
	public @ResponseBody AlertaResponse consultarAlerta(@PathVariable Long idUsuario, @PathVariable Long idAlerta) {
		AlertaResponse response = new AlertaResponse();
		UserAlert userAlert = new UserAlert();
		try {
			userAlert.setIdAlerta(idAlerta);
			userAlert.setIdUsuario(idUsuario);
			EstadoEnvio estadoEnvio = administrarAlertasManager.findAlertaByUsuarioAlerta(userAlert);
			if (estadoEnvio != null) {
				response.setAlertaVO(new Gson().fromJson(estadoEnvio.getEnvioAlertaUsuario().getAlerta().getContenido(), AlertaVO.class));
				response.getAlertaVO().setIdAlerta(estadoEnvio.getEnvioAlertaUsuario().getAlerta().getIdAlerta());
				response.getAlertaVO().setIdDirectorio(estadoEnvio.getEnvioAlertaUsuario().getIdDirectorio());
				response.getRestResponse().setCode(Constantes.CodigoEstadoComucacion.RESULTADO_EXITOSO);
				if (estadoEnvio.getTipoEstadoAlerta().getIdEstado() == TipoEstadoAlerta.ENVIADA) {
					administrarAlertasManager.registrarEstadoEntregado(estadoEnvio);
				}
			} else {
				response = new AlertaResponse();
				response.getRestResponse().setCode(Constantes.CodigoEstadoComucacion.EXITOSO_SIN_RESULTADO);
			}

		} catch (Exception e) {
			response.getRestResponse().setCode(Constantes.CodigoEstadoComucacion.ERROR_TECNICO);
			response.getRestResponse().setDescription(PropertiesManager.getText("qxgestionapp.errorGenerico"));
			logger.error(e.getMessage(), e);
		}
		return response;
	}

	/**
	 * Consulta todas las alertas asociadas a un usuario<br>
	 * Creado el 10/07/2014 a las 10:20:12 <br>
	 * 
	 * @param idUsuario
	 * @param idAlerta
	 * @return AlertaListResponse
	 */
	@RequestMapping(value = "/consultarAlertas")
	@ResponseBody
	@Secured("ROLE_APP_FOTODETECCION_REST_C")
	public AlertaListResponse consultarAlertasByUser(@RequestParam(value = "data", required = true, defaultValue = "false") String data) {
		AlertaListResponse response = new AlertaListResponse();
		try {
			UsuarioAPP user = new Gson().fromJson(data, UsuarioAPP.class);
			List<EstadoEnvio> lista = administrarAlertasManager.findAlertasByUsuario(user.getIdUsuario());
			if (!lista.isEmpty()) {
				AlertaVO alerta = null;
				for (EstadoEnvio estadoEnvio : lista) {
					// si aun no le ha llegado al usuario se actualiza el estado a entregado
					if (estadoEnvio.getTipoEstadoAlerta().getIdEstado() == TipoEstadoAlerta.PENDIENTE_ENVIO
							|| estadoEnvio.getTipoEstadoAlerta().getIdEstado() == TipoEstadoAlerta.ENVIADA) {
						administrarAlertasManager.registrarEstadoEntregado(estadoEnvio);
					}
					alerta = new Gson().fromJson(estadoEnvio.getEnvioAlertaUsuario().getAlerta().getContenido(), AlertaVO.class);
					alerta.setIdAlerta(estadoEnvio.getEnvioAlertaUsuario().getAlerta().getIdAlerta());
					alerta.setIdDirectorio(estadoEnvio.getEnvioAlertaUsuario().getIdDirectorio());
					response.getListaAlertas().add(alerta);
				}
				response.getRestResponse().setCode(Constantes.CodigoEstadoComucacion.RESULTADO_EXITOSO);
			} else {
				response = new AlertaListResponse();
				response.getRestResponse().setCode(Constantes.CodigoEstadoComucacion.EXITOSO_SIN_RESULTADO);
			}
		} catch (Exception e) {
			response.getRestResponse().setCode(Constantes.CodigoEstadoComucacion.ERROR_TECNICO);
			response.getRestResponse().setDescription(PropertiesManager.getText("qxgestionapp.autenticaSeguridad.Login.iniciarSesion.errorTecnico"));
			logger.error(e.getMessage(), e);
		}
		return response;
	}

	/**
	 * Modifica el directorio de una alerta<br>
	 * Creado el 10/07/2014 a las 17:37:02 <br>
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/modificarDirectorio")
	@ResponseBody
	@Secured("ROLE_APP_FOTODETECCION_REST_C")
	public RestResponse modificarDirectorio(@RequestParam(value = "data", required = true, defaultValue = "false") String data) {
		RestResponse response = new RestResponse();
		try {
			UserAlert userAlert = new Gson().fromJson(data, UserAlert.class);
			administrarAlertasManager.modificarDirectorioAlerta(userAlert);
			response.setCode(Constantes.CodigoEstadoComucacion.RESULTADO_EXITOSO);
		} catch (QxException e) {
			response.setCode(Constantes.CodigoEstadoComucacion.ERROR_DATOS);
			response.setDescription(PropertiesManager.getText("qxgestionapp.recuperacionClave.usuarioNoExiste"));
			logger.error(PropertiesManager.getText("qxgestionapp.recuperacionClave.usuarioNoExiste"));
		} catch (Exception e) {
			response.setCode(Constantes.CodigoEstadoComucacion.ERROR_TECNICO);
			response.setDescription(PropertiesManager.getText("qxgestionapp.autenticaSeguridad.Login.iniciarSesion.errorTecnico"));
			logger.error(e.getMessage(), e);
		}
		return response;
	}
}
