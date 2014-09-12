package co.edu.eafit.coglovi.restservice.usuarios;

import java.util.Date;
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
	@RequestMapping("/registrar")
	@ResponseBody
	public RestResponse registrar(@RequestParam(value = "usuarioApp", required = true, defaultValue = "false") String usuarioApp,
			@RequestParam(value = "gruposInteres", required = true, defaultValue = "false") String gruposInteres) {
		
		UsuarioAPP usuarioAPP = null;
		List<GrupoInteres> listGruposInteres=null;
		RestResponse restResponse;
		restResponse = new RestResponse();
		try {
			usuarioAPP = new Gson().fromJson(usuarioApp, UsuarioAPP.class);
			listGruposInteres = (List<GrupoInteres>) new Gson().fromJson(gruposInteres, new TypeToken<List<GrupoInteres>>() {
			}.getType());
			boolean isRegistred=usuarioManager.registroUsuario(usuarioAPP,listGruposInteres);
			if(isRegistred){
				restResponse.setCode(Constantes.RegistroUsuarios.REGISTRO_EXITOSO);
			}else{
				restResponse.setCode(Constantes.RegistroUsuarios.USUARIO_EXISTE);
			}
		} catch (CoGloViException e) {
			restResponse.setCode(Constantes.CodigoEstadoComucacion.ERROR_DATOS);
			restResponse.setDescription(PropertiesManager.getText("coglovi.autenticaSeguridad.Login.iniciarSesion.errorDatos"));
		} catch (Exception e) {
			restResponse.setCode(Constantes.CodigoEstadoComucacion.ERROR_TECNICO);
			restResponse.setDescription(PropertiesManager.getText("coglovi.autenticaSeguridad.Login.iniciarSesion.errorTecnico"));
			logger.error(e.getMessage(), e);
		}
		return restResponse;
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public RestResponse login(@RequestParam(value = "usuarioApp", required = true, defaultValue = "false") String usuarioApp) {
		UsuarioAPP usuarioAPP = null;
		RestResponse restResponse= new RestResponse();
		try {
			usuarioAPP = new Gson().fromJson(usuarioApp, UsuarioAPP.class);
			boolean isValidUser=usuarioManager.isValidUsuario(usuarioAPP);
			if(isValidUser){
				restResponse.setCode(Constantes.RegistroUsuarios.REGISTRO_EXITOSO);
			}else{
				restResponse.setCode(Constantes.RegistroUsuarios.USUARIO_EXISTE);
			}
		} catch (CoGloViException e) {
			restResponse.setCode(Constantes.CodigoEstadoComucacion.ERROR_DATOS);
			restResponse.setDescription(PropertiesManager.getText("coglovi.autenticaSeguridad.Login.iniciarSesion.errorDatos"));
		} catch (Exception e) {
			restResponse.setCode(Constantes.CodigoEstadoComucacion.ERROR_TECNICO);
			restResponse.setDescription(PropertiesManager.getText("coglovi.autenticaSeguridad.Login.iniciarSesion.errorTecnico"));
			logger.error(e.getMessage(), e);
		}
		return restResponse;
	}

		
	
	
	public static void main(String[] args) {
		Gson gson=new Gson();
		UsuarioAPP usuarioApp= new UsuarioAPP();
		usuarioApp.setNombres("Pepe");
		usuarioApp.setIdUsuario(2L);
		usuarioApp.setFechaRegistro(new Date());
		
		String gsonString=gson.toJson(usuarioApp);
		System.out.println(gsonString);
	}
}
