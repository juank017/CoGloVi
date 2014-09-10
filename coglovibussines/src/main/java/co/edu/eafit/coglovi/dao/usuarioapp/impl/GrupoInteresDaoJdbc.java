package co.edu.eafit.coglovi.dao.usuarioapp.impl;

import org.springframework.stereotype.Repository;

import co.edu.eafit.coglovi.dao.usuarioapp.GrupoInteresDao;
import co.edu.eafit.coglovi.dao.util.DaoTemplate;
import co.edu.eafit.coglovi.model.usuario.GrupoInteres;

/**
 * Se encarga de ejecutar los comandos sql en la base de datos relacionados con la tabla Usuario_APP<br>
 * Creado el 8/07/2014 a las 16:26:19 <br>
 * 
 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
 */
@Repository
public class GrupoInteresDaoJdbc extends DaoTemplate implements GrupoInteresDao {

	@Override
	public void insert(GrupoInteres grupoInteres) {
		// TODO Auto-generated method stub
	}

}
