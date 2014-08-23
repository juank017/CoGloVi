package co.edu.eafit.gestionapp.restservice.placas;

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

import co.edu.eafit.coglovi.gestionapp.exception.QxException;
import co.edu.eafit.coglovi.gestionapp.manager.administrarusuarioplaca.AdministraUsuarioPlacaManager;
import co.edu.eafit.coglovi.gestionapp.model.Constantes;
import co.edu.eafit.coglovi.gestionapp.model.autenticausuario.RestResponse;
import co.edu.eafit.coglovi.gestionapp.model.usuariovehiculo.PlacaIn;
import co.edu.eafit.coglovi.gestionapp.model.usuariovehiculo.PlacasResponse;
import co.edu.eafit.coglovi.gestionapp.model.usuariovehiculo.RegistroUsuarioVehiculo;
import co.edu.eafit.coglovi.gestionapp.model.usuariovehiculo.UsuarioAPP;
import co.edu.eafit.coglovi.gestionapp.model.usuariovehiculo.Vehiculo;
import co.edu.eafit.coglovi.gestionapp.transversal.PropertiesManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Servicio que administra las placas de los vehiculos por usuario.
 * 
 * @author daniel.rios
 * 
 */
@Controller
@RequestMapping("placas")
public class PlacasRest {

	@Autowired
	AdministraUsuarioPlacaManager administraUsuarioPlacaManager;
	private final Log logger = LogFactory.getLog(getClass());

	/**
	 * Consulta todas las asociadas a un usuario<br>
	 * Creado el 10/07/2014 a las 10:20:12 <br>
	 * 
	 * @param idUsuario
	 * @param idAlerta
	 * @return AlertaListResponse
	 */
	@RequestMapping("/consultar")
	@ResponseBody
	@Secured("ROLE_APP_FOTODETECCION_REST_C")
	public PlacasResponse consultarPlacasByUser(@RequestParam(value = "data", required = true, defaultValue = "false") String data) {
		PlacasResponse response = null;
		try {
			UsuarioAPP user = new Gson().fromJson(data, UsuarioAPP.class);
			response = administraUsuarioPlacaManager.consultarPlacasByUser(user);
		} catch (Exception e) {
			response = new PlacasResponse();
			response.getRestResponse().setCode(Constantes.CodigoEstadoComucacion.ERROR_TECNICO);
			response.getRestResponse().setDescription(PropertiesManager.getText("qxgestionapp.autenticaSeguridad.Login.iniciarSesion.errorTecnico"));
			logger.error(e.getMessage(), e);
		}
		return response;
	}

	/**
	 * Recibe la informacion de las placas por usuario desde el aplicativo movil.
	 * 
	 * @param usuarioApp
	 * @param placas
	 * @return
	 */
	@RequestMapping("/eliminar")
	@ResponseBody
	@Secured("ROLE_APP_FOTODETECCION_REST_C")
	public RestResponse eliminarPlaca(@RequestParam(value = "usuarioApp", required = true, defaultValue = "false") String usuarioApp,
			@RequestParam(value = "placa", required = true, defaultValue = "false") String placa) {
		UsuarioAPP usuarioAPP = null;
		Vehiculo vehiculo = null;
		RestResponse restResponse = null;
		try {
			usuarioAPP = new Gson().fromJson(usuarioApp, UsuarioAPP.class);
			vehiculo = new Gson().fromJson(placa, Vehiculo.class);
			restResponse = administraUsuarioPlacaManager.eliminarPlacaByUsuario(usuarioAPP, vehiculo);
		} catch (QxException e) {
			restResponse = new RestResponse();
			restResponse.setCode(Constantes.CodigoEstadoComucacion.ERROR_DATOS);
			restResponse.setDescription(PropertiesManager.getText("qxgestionapp.administrarUsuarioPlaca.errorDatos"));
		} catch (Exception e) {
			restResponse = new RestResponse();
			restResponse.setCode(Constantes.CodigoEstadoComucacion.ERROR_TECNICO);
			restResponse.setDescription(PropertiesManager.getText("qxgestionapp.autenticaSeguridad.Login.iniciarSesion.errorTecnico"));
			logger.error(e.getMessage(), e);
		}

		return restResponse;

	}

	/**
	 * Recibe la informacion de un usuario en el sistema con sus respectivas placas a registrar
	 * 
	 * @param usuarioApp
	 * @param registroUsuarioVehiculo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/registrar")
	@ResponseBody
	@Secured("ROLE_APP_FOTODETECCION_REST_C")
	public RestResponse registrarPlaca(@RequestParam(value = "usuarioApp", required = true, defaultValue = "false") String usuarioApp,
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
			restResponse = administraUsuarioPlacaManager.registrarPlaca(usuarioAPP, listRegistroUsuarioVehiculo);
		} catch (QxException e) {
			restResponse = new RestResponse();
			restResponse.setCode(Constantes.CodigoEstadoComucacion.ERROR_DATOS);
			restResponse.setDescription(PropertiesManager.getText("qxgestionapp.administrarUsuarioPlaca.errorDatos.registrarPlacas"));
		} catch (Exception e) {
			restResponse = new RestResponse();
			restResponse.setCode(Constantes.CodigoEstadoComucacion.ERROR_TECNICO);
			restResponse.setDescription(PropertiesManager.getText("qxgestionapp.autenticaSeguridad.Login.iniciarSesion.errorTecnico"));
			logger.error(e.getMessage(), e);
		}

		return restResponse;
	}
}
