package co.edu.eafit.coglovi.manager.pregunta.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.eafit.coglovi.dao.pregunta.PreguntaDao;
import co.edu.eafit.coglovi.dao.usuarioapp.UsuarioAPPDao;
import co.edu.eafit.coglovi.dao.util.DaoUtil;
import co.edu.eafit.coglovi.manager.pregunta.PreguntaManager;
import co.edu.eafit.coglovi.model.ddd.Pregunta;

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

	@Override
	public boolean registroPregunta(Pregunta pregunta) throws Exception {
		pregunta.getEstado().setEstado(1);
		preguntaDao.insertPregunta(pregunta);
		return true;
	}
}
