package co.edu.eafit.coglovi.dao.usuarioapp.impl;

import org.springframework.stereotype.Repository;

import co.edu.eafit.coglovi.dao.usuarioapp.GrupoInteresDao;
import co.edu.eafit.coglovi.dao.util.DaoTemplate;
import co.edu.eafit.coglovi.model.usuario.GrupoInteres;
import co.edu.eafit.coglovi.transversal.PropertiesManager;

@Repository
public class GrupoInteresDaoJdbc extends DaoTemplate implements GrupoInteresDao {
	@Override
	public void insert(GrupoInteres grupoInteres) {
			String sql = PropertiesManager.obtenerCadena("cogloviSQL/cogloviSQL","coglovi.grupointeres.insert");
			jdbcTemplate.update( sql,new Object[] { grupoInteres.getUsuarioApp().getIdUsuario(),grupoInteres.getGruposInteres()});  
	}
}
