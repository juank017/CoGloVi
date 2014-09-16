package co.edu.eafit.coglovi.restservice.preguntas;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.edu.eafit.coglovi.exception.CoGloViException;
import co.edu.eafit.coglovi.manager.pregunta.PreguntaManager;
import co.edu.eafit.coglovi.model.Constantes;
import co.edu.eafit.coglovi.model.core.RestResponse;
import co.edu.eafit.coglovi.model.ddd.Estado;
import co.edu.eafit.coglovi.model.ddd.Pregunta;
import co.edu.eafit.coglovi.model.usuario.GrupoInteres;
import co.edu.eafit.coglovi.model.usuario.UsuarioAPP;
import co.edu.eafit.coglovi.transversal.PropertiesManager;

import com.google.gson.Gson;

@Controller
@RequestMapping("preguntas")
public class PreguntasRest {
	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private PreguntaManager preguntaManager;
	
	@RequestMapping("/registrar")
	@ResponseBody
	public RestResponse login(@RequestParam(value = "pregunta", required = true, defaultValue = "false") String pregunta) {
		RestResponse restResponse= new RestResponse();
		try {
			Pregunta preguntaDto=null;
			preguntaDto = new Gson().fromJson(pregunta, Pregunta.class);
			boolean isValidUser=preguntaManager.registroPregunta(preguntaDto);
			if(isValidUser){
				restResponse.setCode(Constantes.RegistroPregunta.REGISTRO_EXITOSO);
			}else{
				restResponse.setCode(Constantes.RegistroPregunta.REGISTRO_ERROR);
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
		Pregunta pregunta=new Pregunta();
		GrupoInteres grupoInteres=new GrupoInteres();
		UsuarioAPP usuario=new UsuarioAPP();
		usuario.setCorreoElectronico("juan3116162188@gmail.com");
		grupoInteres.setGruposInteres("CI");
		pregunta.setUsuario(usuario);
		pregunta.setPregunta("Cuanto tiempo vive una tortuga");
		pregunta.setGrupoInteres(grupoInteres);
		System.out.println(gson.toJson(pregunta));
	}
}
