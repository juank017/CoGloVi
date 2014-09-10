package co.edu.eafit.coglovi.dao.usuarioapp.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import co.edu.eafit.coglovi.dao.usuarioapp.UsuarioAPPDao;
import co.edu.eafit.coglovi.dao.util.DaoTemplate;
import co.edu.eafit.coglovi.model.usuario.UsuarioAPP;
import co.edu.eafit.coglovi.transversal.PropertiesManager;

/**
 * Se encarga de ejecutar los comandos sql en la base de datos relacionados con la tabla Usuario_APP<br>
 * Creado el 8/07/2014 a las 16:26:19 <br>
 * 
 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
 */
@Repository
public class UsuarioAPPDaoJdbc extends DaoTemplate implements UsuarioAPPDao {

	@Override
	public UsuarioAPP findUsuario(UsuarioAPP usuarioApp) throws Exception {
		String sql = PropertiesManager.obtenerCadena("cogloviSQL/cogloviSQL","usuarioSistema.findUsuario");
		RowMapper<UsuarioAPP> rm = new RowMapper<UsuarioAPP>() {
			public UsuarioAPP mapRow(ResultSet rs, int rowNum)throws SQLException {
				UsuarioAPP usuario = new UsuarioAPP();
				usuario.setApellidos(rs.getString("APELLIDOS"));
				usuario.setCelular(rs.getString("CELULAR"));
				usuario.setClave(rs.getString("CLAVE"));
				usuario.setCorreoElectronico(rs.getString("CORREOELECTRONICO"));
				return usuario;
			}
		};
		
		try {
			return jdbcTemplate.queryForObject(sql,new Object[]{usuarioApp.getCorreoElectronico()}, rm);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void insertUsuario(UsuarioAPP usuarioAPP) {
		// TODO Auto-generated method stub
		
	}

}
