package co.edu.eafit.coglovi.restservice.usuarios;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.edu.eafit.coglovi.exception.CoGloViException;
import co.edu.eafit.coglovi.manager.seguridad.UsuarioManager;
import co.edu.eafit.coglovi.model.Constantes;
import co.edu.eafit.coglovi.model.core.RestResponse;
import co.edu.eafit.coglovi.model.usuario.GrupoInteres;
import co.edu.eafit.coglovi.model.usuario.UsuarioAPP;
import co.edu.eafit.coglovi.transversal.PropertiesManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("usuarios")
public class UsuariosRest {

	@Autowired
	UsuarioManager usuarioManager;
	private final Log logger = LogFactory.getLog(getClass());



	/**
	 * Recibe la informacion de vehiculos por usuario y los envia para su correspondiente insercion
	 * 
	 * @param usuarioApp
	 * @param vehiculo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/registrar")
	@ResponseBody
//	@Secured("ROLE_COGLOVI_REST_C")
	public RestResponse registrar(@RequestParam(value = "usuarioApp", required = true, defaultValue = "false") String usuarioApp,
			@RequestParam(value = "gruposInteres", required = true, defaultValue = "false") String gruposInteres) {

		UsuarioAPP usuarioAPP = null;
		List<GrupoInteres> listGruposInteres=null;
		try {
			usuarioAPP = new Gson().fromJson(usuarioApp, UsuarioAPP.class);
			listGruposInteres = (List<GrupoInteres>) new Gson().fromJson(gruposInteres, new TypeToken<List<GrupoInteres>>() {
			}.getType());
			usuarioManager.registroUsuario(usuarioAPP,gruposInteres);
			
			restResponse = administraUsuarioPlacaManager.registrarse(usuarioAPP, listRegistroUsuarioVehiculo);
		} catch (CoGloViException e) {
			restResponse = new RestResponse();
			restResponse.setCode(Constantes.CodigoEstadoComucacion.ERROR_DATOS);
			restResponse.setDescription(PropertiesManager.getText("qxgestionapp.administrarUsuarioPlaca.existeUsuario"));
		} catch (Exception e) {
			restResponse = new RestResponse();
			restResponse.setCode(Constantes.CodigoEstadoComucacion.ERROR_TECNICO);
			restResponse.setDescription(PropertiesManager.getText("qxgestionapp.autenticaSeguridad.Login.iniciarSesion.errorTecnico"));
			logger.error(e.getMessage(), e);
		}

		return restResponse;
	}
}
