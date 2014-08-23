package co.edu.eafit.gestionapp.restservice.contenido;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.edu.eafit.coglovi.gestionapp.manager.contenido.AdministrarContenidoManager;
import co.edu.eafit.coglovi.gestionapp.model.Constantes;
import co.edu.eafit.coglovi.gestionapp.model.autenticausuario.RestResponse;
import co.edu.eafit.coglovi.gestionapp.model.contenido.ContenidoResponse;
import co.edu.eafit.coglovi.gestionapp.model.reportesestadisticas.DetalleEncuesta;
import co.edu.eafit.coglovi.gestionapp.model.reportesestadisticas.EncuestaUsuario;
import co.edu.eafit.coglovi.gestionapp.model.reportesestadisticas.PreguntasExperiencia;
import co.edu.eafit.coglovi.gestionapp.transversal.PropertiesManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Servicio Rest que expone las consultas sobre la configuración de contenido<br>
 * Creado el 16/07/2014 a las 8:19:45 <br>
 * 
 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
 */
@Controller
@RequestMapping("contenido")
public class ConsultarContenidoRest {

	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private AdministrarContenidoManager contenidoManager;

	/**
	 * Obtiene la lista de preguntas configuradas con sus posibles respuestas para el cuestionario de experiencia de usuario<br>
	 * Creado el 6/08/2014 a las 14:21:08 <br>
	 * 
	 * @return PreguntasExperiencia
	 */
	@RequestMapping("/preguntasExperiencia")
	@ResponseBody
	@Secured("ROLE_APP_FOTODETECCION_REST_C")
	public PreguntasExperiencia consultarPreguntasExperiencia() {
		PreguntasExperiencia experiencia = new PreguntasExperiencia();
		try {
			experiencia.setListaPreguntas(contenidoManager.findPreguntasExperiencia());
			if (experiencia.getListaPreguntas().isEmpty()) {
				experiencia.getResponse().setCode(Constantes.CodigoEstadoComucacion.EXITOSO_SIN_RESULTADO);
			} else {
				experiencia.getResponse().setCode(Constantes.CodigoEstadoComucacion.RESULTADO_EXITOSO);
			}
		} catch (Exception e) {
			experiencia.getResponse().setCode(Constantes.CodigoEstadoComucacion.ERROR_TECNICO);
			experiencia.getResponse().setDescription(PropertiesManager.getText("qxgestionapp.autenticaSeguridad.Login.iniciarSesion.errorTecnico"));
			logger.error(e.getMessage(), e);
		}
		return experiencia;
	}

	/**
	 * Obtiene la lista de preguntas frecuentes<br>
	 * Creado el 16/07/2014 a las 8:01:38 <br>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preguntasFrecuentes", method = RequestMethod.GET)
	@ResponseBody
	@Secured("ROLE_APP_FOTODETECCION_REST_C")
	public ContenidoResponse consultarPreguntasFrecuentes() {
		ContenidoResponse response = new ContenidoResponse();
		try {
			response.setListaPreguntas(contenidoManager.findPreguntasFrecuentes());
			if (response.getListaPreguntas().isEmpty()) {
				response.getResponse().setCode(Constantes.CodigoEstadoComucacion.EXITOSO_SIN_RESULTADO);
			} else {
				response.getResponse().setCode(Constantes.CodigoEstadoComucacion.RESULTADO_EXITOSO);
			}
		} catch (Exception e) {
			response.getResponse().setCode(Constantes.CodigoEstadoComucacion.ERROR_TECNICO);
			response.getResponse().setDescription(PropertiesManager.getText("qxgestionapp.errorGenerico"));
			logger.error(e.getMessage(), e);
		}
		return response;
	}

	/**
	 * Obtiene la url del manual de usuario
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ubicacionManual", method = RequestMethod.GET)
	@ResponseBody
	@Secured("ROLE_APP_FOTODETECCION_REST_C")
	public ContenidoResponse consultarUbicacionManual() {
		ContenidoResponse response = new ContenidoResponse();
		try {
			response.setUrlManual(contenidoManager.findUrlManual());
			if (response.getUrlManual() == null || "".equals(response.getUrlManual())) {
				response.getResponse().setCode(Constantes.CodigoEstadoComucacion.EXITOSO_SIN_RESULTADO);
			} else {
				response.getResponse().setCode(Constantes.CodigoEstadoComucacion.RESULTADO_EXITOSO);
			}
		} catch (Exception e) {
			response.getResponse().setCode(Constantes.CodigoEstadoComucacion.ERROR_TECNICO);
			response.getResponse().setDescription(PropertiesManager.getText("qxgestionapp.errorGenerico"));
			logger.error(e.getMessage(), e);
		}
		return response;
	}

	/**
	 * Recibe la encuesta de experiencia por usuario
	 * 
	 * @param encuestaUsuario
	 * @param detalleEncuesta
	 * @return
	 */
	@RequestMapping("/registrarEncuesta")
	@ResponseBody
	@Secured("ROLE_APP_FOTODETECCION_REST_C")
	public RestResponse registrarEncuestaExperiencia(
			@RequestParam(value = "encuestaUsuario", required = true, defaultValue = "false") String encuestaUsuario,
			@RequestParam(value = "detalleEncuesta", required = true, defaultValue = "false") String detalleEncuesta) {
		EncuestaUsuario encuestaUser = null;
		List<DetalleEncuesta> listDetalleEncuesta = null;
		RestResponse restResponse = null;
		try {
			encuestaUser = new Gson().fromJson(encuestaUsuario, EncuestaUsuario.class);
			listDetalleEncuesta = (List<DetalleEncuesta>) new Gson().fromJson(detalleEncuesta, new TypeToken<List<DetalleEncuesta>>() {
			}.getType());
			restResponse = contenidoManager.registrarEncuestaExperiencia(encuestaUser, listDetalleEncuesta);
		} catch (Exception e) {
			restResponse = new RestResponse();
			restResponse.setCode(Constantes.CodigoEstadoComucacion.ERROR_TECNICO);
			restResponse.setDescription(PropertiesManager.getText("qxgestionapp.autenticaSeguridad.Login.iniciarSesion.errorTecnico"));
			logger.error(e.getMessage(), e);
		}
		return restResponse;
	}

}
