package co.edu.eafit.coglovi.dao.usuarioapp.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import co.edu.eafit.coglovi.dao.usuarioapp.UsuarioAPPDao;
import co.edu.eafit.coglovi.dao.util.DaoTemplate;
import co.edu.eafit.coglovi.model.usuariovehiculo.UsuarioAPP;
import co.edu.eafit.coglovi.transversal.PropertiesManager;

/**
 * Se encarga de ejecutar los comandos sql en la base de datos relacionados con la tabla Usuario_APP<br>
 * Creado el 8/07/2014 a las 16:26:19 <br>
 * 
 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
 */
@Repository
public class UsuarioAPPDaoJdbc extends DaoTemplate implements UsuarioAPPDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.usuarioapp.UsuarioAPPDao#findUsuarioAPPByIdUser(java.lang.Long)
	 */
	@Override
	public UsuarioAPP findUsuarioAPPByIdUser(Long idUsuario) throws Exception {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "UsuarioAPP.findUsuarioAPPByIdUser");
		RowMapper<UsuarioAPP> rm = new RowMapper<UsuarioAPP>() {
			public UsuarioAPP mapRow(ResultSet rs, int rowNum) throws SQLException {
				UsuarioAPP usuarioVehiculo = new UsuarioAPP();
				usuarioVehiculo.setIdUsuario(rs.getLong("Id_Usuario"));
				usuarioVehiculo.getTipoDocumentoIdentidad().setIdDocumentoIdentidad(rs.getInt("id_documento_identidad"));
				usuarioVehiculo.setNroDocumento(rs.getString("nro_documento"));
				usuarioVehiculo.setNombres(rs.getString("nombres"));
				usuarioVehiculo.setApellidos(rs.getString("apellidos"));
				usuarioVehiculo.setCelular(rs.getString("celular"));
				usuarioVehiculo.getTipoCiudad().setIdCiudad(rs.getInt("id_ciudad"));
				usuarioVehiculo.setDireccion(rs.getString("direccion"));
				usuarioVehiculo.setCorreoElectronico(rs.getString("correo_electronico"));
				usuarioVehiculo.setClave(rs.getString("clave"));
				usuarioVehiculo.setFechaRegistro(rs.getTimestamp("fecha_registro"));
				usuarioVehiculo.setFehaCambioClave(rs.getTimestamp("fecha_cambio_clave"));
				usuarioVehiculo.setEnvioInformacion(rs.getString("envio_informacion"));
				usuarioVehiculo.setCambioClave(rs.getString("cambio_clave"));
				usuarioVehiculo.setActivo(rs.getString("activo"));
				return usuarioVehiculo;
			}
		};
		try {
			return jdbcTemplate.queryForObject(sql, new Object[] { idUsuario }, rm);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.usuarioapp.UsuarioAPPDao#findUsuarioAPPByUserName(java.lang.String)
	 */
	@Override
	public UsuarioAPP findUsuarioAPPByUserName(String user) throws Exception {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "UsuarioAPP.findUsuarioAPPByUserName");
		RowMapper<UsuarioAPP> rm = new RowMapper<UsuarioAPP>() {
			public UsuarioAPP mapRow(ResultSet rs, int rowNum) throws SQLException {
				UsuarioAPP usuarioVehiculo = new UsuarioAPP();
				usuarioVehiculo.setIdUsuario(rs.getLong("Id_Usuario"));
				usuarioVehiculo.getTipoDocumentoIdentidad().setIdDocumentoIdentidad(rs.getInt("id_documento_identidad"));
				usuarioVehiculo.setNroDocumento(rs.getString("nro_documento"));
				usuarioVehiculo.setNombres(rs.getString("nombres"));
				usuarioVehiculo.setApellidos(rs.getString("apellidos"));
				usuarioVehiculo.setCelular(rs.getString("celular"));
				usuarioVehiculo.getTipoCiudad().setIdCiudad(rs.getInt("id_ciudad"));
				usuarioVehiculo.setDireccion(rs.getString("direccion"));
				usuarioVehiculo.setCorreoElectronico(rs.getString("correo_electronico"));
				usuarioVehiculo.setClave(rs.getString("clave"));
				usuarioVehiculo.setFechaRegistro(rs.getTimestamp("fecha_registro"));
				usuarioVehiculo.setFehaCambioClave(rs.getTimestamp("fecha_cambio_clave"));
				usuarioVehiculo.setEnvioInformacion(rs.getString("envio_informacion"));
				usuarioVehiculo.setCambioClave(rs.getString("cambio_clave"));
				usuarioVehiculo.setActivo(rs.getString("activo"));
				return usuarioVehiculo;
			}
		};
		try {
			return jdbcTemplate.queryForObject(sql, new Object[] { user }, rm);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.quipux.gestionapp.dao.usuarioapp.UsuarioAPPDao#registrarRecuperacionClave(co.com.quipux.gestionapp.model.usuariovehiculo.UsuarioAPP)
	 */
	@Override
	public void registrarRecuperacionClave(UsuarioAPP user) throws Exception {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "UsuarioAPP.registrarRecuperacionClave");
		jdbcTemplate.update(sql, new Object[] { user.getClave(), user.getCambioClave(), user.getIdUsuario() });
	}

}
