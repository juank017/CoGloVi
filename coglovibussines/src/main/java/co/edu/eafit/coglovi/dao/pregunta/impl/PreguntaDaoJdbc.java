package co.edu.eafit.coglovi.dao.pregunta .impl;

import org.springframework.stereotype.Repository;

import co.edu.eafit.coglovi.dao.pregunta.PreguntaDao;
import co.edu.eafit.coglovi.dao.util.DaoTemplate;
import co.edu.eafit.coglovi.model.ddd.Pregunta;
import co.edu.eafit.coglovi.transversal.PropertiesManager;
@Repository
public class PreguntaDaoJdbc extends DaoTemplate implements PreguntaDao{

	
	@Override
	public void insertPregunta(Pregunta pregunta) {
		String sql = PropertiesManager.obtenerCadena("cogloviSQL/cogloviSQL","coglovi.pregunta.insert");
		jdbcTemplate.update(  
				    sql,  
				    new Object[] {pregunta.getPregunta(),pregunta.getEstado().getEstado(),pregunta.getGrupoInteres().getGruposInteres(),pregunta.getUsuario().getIdUsuario()});  
	}

}
