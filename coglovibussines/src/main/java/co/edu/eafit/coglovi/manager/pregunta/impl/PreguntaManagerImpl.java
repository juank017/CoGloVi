package co.edu.eafit.coglovi.manager.pregunta.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.eafit.coglovi.dao.pregunta.PreguntaDao;
import co.edu.eafit.coglovi.dao.usuarioapp.UsuarioAPPDao;
import co.edu.eafit.coglovi.dao.util.DaoUtil;
import co.edu.eafit.coglovi.manager.pregunta.PreguntaManager;
import co.edu.eafit.coglovi.model.Constantes;
import co.edu.eafit.coglovi.model.ddd.Pregunta;
import co.edu.eafit.coglovi.model.usuario.UsuarioAPP;

/**
 * Manager para el acceso a datos de las preguntas
 *
 */
@Service
public class PreguntaManagerImpl implements PreguntaManager {

	@Autowired
	private UsuarioAPPDao usuarioAppDao;
	@Autowired
	private DaoUtil daoUtil;

	@Autowired
	private PreguntaDao preguntaDao;
	
	@Autowired
	private UsuarioAPPDao usuarioDao;


	@Override
	public boolean registroPregunta(Pregunta pregunta) throws Exception {
		
		//http://localhost:8081/coglovirest/rest/preguntas/registrar?pregunta={%22pregunta%22:%22Cuanto%20tiempo%20vive%20una%20tortuga%22,%22grupoInteres%22:{%22gruposInteres%22:%22CI%22},%22usuario%22:{%22correoElectronico%22:%22juan3116162188@gmail.com%22}}
		UsuarioAPP usuario=usuarioDao.findUsuario(pregunta.getUsuario());
		pregunta.setUsuario(usuario);
		pregunta.getEstado().setEstado(Constantes.EstadoPreguntas.ESTADO_PENDIENTE);
		preguntaDao.insertPregunta(pregunta);
		return true;
	}
}
