package co.edu.eafit.gestionapp.restservice.autenticarseguridad;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.edu.eafit.coglovi.gestionapp.exception.QxException;
import co.edu.eafit.coglovi.gestionapp.manager.autenticarseguridad.AutenticaUsuarioManager;
import co.edu.eafit.coglovi.gestionapp.model.Constantes;
import co.edu.eafit.coglovi.gestionapp.model.autenticausuario.CambioClave;
import co.edu.eafit.coglovi.gestionapp.model.autenticausuario.RestResponse;
import co.edu.eafit.coglovi.gestionapp.model.autenticausuario.User;
import co.edu.eafit.coglovi.gestionapp.model.usuariovehiculo.UsuarioAPP;
import co.edu.eafit.coglovi.gestionapp.model.usuariovehiculo.UsuarioVehiculoResponse;
import co.edu.eafit.coglovi.gestionapp.transversal.PropertiesManager;

import com.google.gson.Gson;

/**
 * Servicio que expone metodos relacionados con la autenticacion de usuarios<br>
 * Creado el 8/07/2014 a las  16:10:33 <br>
 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
 */
@Controller
@RequestMapping("user")
public class AutenticaUsuarioRest {

	@Autowired
	AutenticaUsuarioManager auntenticaUsuarioManager;
	private final Log logger = LogFactory.getLog(getClass());

	/**
	 * Recibe la clave nueva para su correspondiente actualizacion
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping("/cambiarClave")
	@ResponseBody
	@Secured("ROLE_APP_FOTODETECCION_REST_C")
	public RestResponse cambiarClave(@RequestParam(value = "data", required = true, defaultValue = "false") String data) {
		CambioClave cambioClave = null;
		RestResponse restResponse = null;
		try {
			cambioClave = new Gson().fromJson(data, CambioClave.class);
			restResponse = auntenticaUsuarioManager.cambiarClave(cambioClave);
		} catch (Exception e) {
			restResponse = new RestResponse();
			restResponse.setCode(Constantes.CodigoEstadoComucacion.ERROR_TECNICO);
			restResponse.setDescription(PropertiesManager.getText("qxgestionapp.autenticaSeguridad.Login.iniciarSesion.errorTecnico"));
			logger.error(e.getMessage(), e);
		}
		return restResponse;
	}

	/**
	 * Recibe la informacion del login e informa si puede acceder al aplicativo
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	@Secured("ROLE_APP_FOTODETECCION_REST_C")
	public UsuarioVehiculoResponse login(@RequestParam(value = "data", required = true, defaultValue = "false") String data) {
		User user = null;
		UsuarioVehiculoResponse usuarioVehiculoResponse = null;
		try {
			user = new Gson().fromJson(data, User.class);
			usuarioVehiculoResponse = auntenticaUsuarioManager.login(user);
		} catch (Exception e) {
			usuarioVehiculoResponse = new UsuarioVehiculoResponse();
			usuarioVehiculoResponse.getRestResponse().setCode(Constantes.CodigoEstadoComucacion.ERROR_TECNICO);
			usuarioVehiculoResponse.getRestResponse().setDescription(
					PropertiesManager.getText("qxgestionapp.autenticaSeguridad.Login.iniciarSesion.errorTecnico"));
			logger.error(e.getMessage(), e);
		}
		return usuarioVehiculoResponse;
	}

	/**
	 * Realiza la recuperacion de la clave<br>
	 * Creado el 4/07/2014 a las 12:41:02 <br>
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping("/recuperarClave")
	@ResponseBody
	@Secured("ROLE_APP_FOTODETECCION_REST_C")
	public RestResponse recuperarClave(@RequestParam(value = "data", required = true, defaultValue = "false") String data) {
		RestResponse restResponse = new RestResponse();
		try {
			User user = new Gson().fromJson(data, User.class);
			UsuarioAPP usuarioAPP = new UsuarioAPP();
			usuarioAPP.setCorreoElectronico(user.getName());
			auntenticaUsuarioManager.recuperarClave(usuarioAPP);
			restResponse.setCode(Constantes.CodigoEstadoComucacion.RESULTADO_EXITOSO);
		} catch (QxException e) {
			restResponse = new RestResponse();
			restResponse.setCode(Constantes.CodigoEstadoComucacion.ERROR_DATOS);
			restResponse.setDescription(PropertiesManager.getText("qxgestionapp.recuperacionClave.usuarioNoExiste"));
		} catch (Exception e) {
			restResponse = new RestResponse();
			restResponse.setCode(Constantes.CodigoEstadoComucacion.ERROR_TECNICO);
			restResponse.setDescription(PropertiesManager.getText("qxgestionapp.autenticaSeguridad.Login.iniciarSesion.errorTecnico"));
			logger.error(e.getMessage(), e);
		}
		return restResponse;
	}
}
