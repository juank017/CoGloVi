package co.edu.eafit.coglovi.gestionapp.dao.seguridad.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import co.edu.eafit.coglovi.gestionapp.dao.seguridad.SeguridadCasDao;
import co.edu.eafit.coglovi.gestionapp.dao.util.DaoTemplate;
import co.edu.eafit.coglovi.gestionapp.model.security.UsuarioSistema;
import co.edu.eafit.coglovi.gestionapp.transversal.PropertiesManager;

/**
 * Dao de acceso a la información de la seguridad de qits
 * 
 */
@Repository
public class SeguridadCasDaoJdbc extends DaoTemplate implements SeguridadCasDao {
	
	
	/*
	 * (non-Javadoc)
	 * @see co.com.quipux.gestionapp.dao.seguridad.SeguridadCasDao#findQuipuxUsuarioCAS(java.lang.String)
	 */
	@Override
	public UsuarioSistema findQuipuxUsuarioCAS(String login) throws Exception {
		String sql = PropertiesManager.obtenerCadena("coreSQL/coreSQL",
				"usuarioSistema.autenticacionUsuario");
		RowMapper<UsuarioSistema> rm = new RowMapper<UsuarioSistema>() {
			public UsuarioSistema mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				UsuarioSistema usuarioSistema = new UsuarioSistema();
				usuarioSistema.setIdUsuario(rs.getInt("ID_USUARIO"));
				usuarioSistema.setClaveAcceso(rs.getString("CLAVE_ACCESO"));
				usuarioSistema.setUsuarioAcceso("USUARIO_ACCESO");
				usuarioSistema.setNombre(rs.getString("NOMBRE"));
				usuarioSistema.setApellido(rs.getString("APELLIDO_1"));
				usuarioSistema.setIdentificacion(rs
						.getString("NUMERO_DOCUMENTO"));
				return usuarioSistema;
			}
		};
		return jdbcTemplate.queryForObject(sql,new Object[]{login}, rm);
	}
	
	/*
	 * (non-Javadoc)
	 * @see co.com.quipux.gestionapp.dao.seguridad.SeguridadCasDao#findQuipuxUsuarioCAS(java.lang.String, java.lang.String)
	 */
	@Override
	public UsuarioSistema findQuipuxUsuarioCAS(String login, String clave) throws Exception {
		String sql = PropertiesManager.obtenerCadena("coreSQL/coreSQL",
				"usuarioSistema.autenticacionUsuarioClave");
		RowMapper<UsuarioSistema> rm = new RowMapper<UsuarioSistema>() {
			public UsuarioSistema mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				UsuarioSistema usuarioSistema = new UsuarioSistema();
				usuarioSistema.setIdUsuario(rs.getInt("ID_USUARIO"));
				usuarioSistema.setClaveAcceso(rs.getString("CLAVE_ACCESO"));
				usuarioSistema.setUsuarioAcceso("USUARIO_ACCESO");
				usuarioSistema.setNombre(rs.getString("NOMBRE"));
				usuarioSistema.setIdentificacion(rs
						.getString("NUMERO_DOCUMENTO"));
				return usuarioSistema;
			}
		};
		return jdbcTemplate.queryForObject(sql,new Object[]{login, clave}, rm);
	}
	
	/*
	 * (non-Javadoc)
	 * @see co.com.quipux.gestionapp.dao.seguridad.SeguridadCasDao#findGrantedAuthority(java.lang.String)
	 */
	@Override
	public List<String> findGrantedAuthority(String login) throws Exception {
		java.util.List<String> authorities = new ArrayList<String>();
		String sql = PropertiesManager.obtenerCadena("coreSQL/coreSQL","usuarioSistema.recursosEspecificos");
		RowMapper<String> rm = new RowMapper<String>() {
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("AUTH");
			}
		};
		authorities = jdbcTemplate.query(sql,new Object[]{login}, rm);
		authorities.add("ROLE_CAMBIO_CONTRASENA_C");
		return authorities;
	}
}
