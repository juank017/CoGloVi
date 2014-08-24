package co.edu.eafit.coglovi.restservice.usuarios;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.edu.eafit.coglovi.exception.QxException;
import co.edu.eafit.coglovi.manager.administrarusuarioplaca.AdministraUsuarioPlacaManager;
import co.edu.eafit.coglovi.model.Constantes;
import co.edu.eafit.coglovi.model.autenticausuario.RestResponse;
import co.edu.eafit.coglovi.model.usuariovehiculo.PlacaIn;
import co.edu.eafit.coglovi.model.usuariovehiculo.RegistroUsuarioVehiculo;
import co.edu.eafit.coglovi.model.usuariovehiculo.UsuarioAPP;
import co.edu.eafit.coglovi.transversal.PropertiesManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("usuarios")
public class UsuariosRest {

	@Autowired
	AdministraUsuarioPlacaManager administraUsuarioPlacaManager;
	private final Log logger = LogFactory.getLog(getClass());

	/**
	 * Recibe la nueva informacion del usuario y los envia para su correspondiente actualizacion
	 * 
	 * @param usuarioApp
	 * @return
	 */
	@RequestMapping("/modificar")
	@ResponseBody
	@Secured("ROLE_APP_FOTODETECCION_REST_C")
	public RestResponse modificarUsuario(@RequestParam(value = "usuarioApp", required = true, defaultValue = "false") String usuarioApp) {
		UsuarioAPP usuarioAPP = null;
		RestResponse restResponse = null;
		try {
			usuarioAPP = new Gson().fromJson(usuarioApp, UsuarioAPP.class);
			restResponse = administraUsuarioPlacaManager.modificarUsuario(usuarioAPP);
		} catch (Exception e) {
			restResponse = new RestResponse();
			restResponse.setCode(Constantes.CodigoEstadoComucacion.ERROR_TECNICO);
			restResponse.setDescription(PropertiesManager.getText("qxgestionapp.autenticaSeguridad.Login.iniciarSesion.errorTecnico"));
			logger.error(e.getMessage(), e);
		}
		return restResponse;
	}

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
	@Secured("ROLE_APP_FOTODETECCION_REST_C")
	public RestResponse registrarse(@RequestParam(value = "usuarioApp", required = true, defaultValue = "false") String usuarioApp,
			@RequestParam(value = "placas", required = true, defaultValue = "false") String placas) {

		UsuarioAPP usuarioAPP = null;
		List<RegistroUsuarioVehiculo> listRegistroUsuarioVehiculo = new ArrayList<RegistroUsuarioVehiculo>();
		RegistroUsuarioVehiculo usuarioVehiculo = null;
		List<PlacaIn> listPlacas = new ArrayList<PlacaIn>();
		RestResponse restResponse = null;

		try {
			usuarioAPP = new Gson().fromJson(usuarioApp, UsuarioAPP.class);
			listPlacas = (List<PlacaIn>) new Gson().fromJson(placas, new TypeToken<List<PlacaIn>>() {
			}.getType());

			for (PlacaIn placa : listPlacas) {
				usuarioVehiculo = new RegistroUsuarioVehiculo();
				usuarioVehiculo.getVehiculo().setPlaca(placa.getPlaca());
				usuarioVehiculo.getUsuarioVehiculo().setPropietario(placa.getPropietario());
				usuarioVehiculo.getUsuarioVehiculo().getTipoDocumentoIdentidad().setIdDocumentoIdentidad(placa.getIdDocumento());
				usuarioVehiculo.getUsuarioVehiculo().setNroDocumento(placa.getNroDocumento());
				listRegistroUsuarioVehiculo.add(usuarioVehiculo);
			}
			restResponse = administraUsuarioPlacaManager.registrarse(usuarioAPP, listRegistroUsuarioVehiculo);
		} catch (QxException e) {
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
