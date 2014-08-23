package co.edu.eafit.coglovi.gestionapp.dao.autenticarseguridad.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import co.edu.eafit.coglovi.gestionapp.dao.autenticarseguridad.AutenticaUsuarioDao;
import co.edu.eafit.coglovi.gestionapp.dao.util.DaoTemplate;
import co.edu.eafit.coglovi.gestionapp.model.autenticausuario.CambioClave;
import co.edu.eafit.coglovi.gestionapp.model.autenticausuario.User;
import co.edu.eafit.coglovi.gestionapp.model.usuariovehiculo.UsuarioAPP;
import co.edu.eafit.coglovi.gestionapp.transversal.PropertiesManager;

@Repository
public class AutenticaUsuarioDaoJdbc extends DaoTemplate implements AutenticaUsuarioDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.quipux.gestionapp.dao.autenticarseguridad.AutenticaUsuarioDao#cambiarClave(co.com.quipux.gestionapp.model.autenticausuario.CambioClave)
	 */
	@Override
	public void cambiarClave(CambioClave cambioClave) throws Exception {
		String sql = PropertiesManager.obtenerCadena("coreSQL/coreSQL", "usuarioAPP.cambiarClave");
		Object[] param = new Object[] { cambioClave.getClave(), new Date(), "N", cambioClave.getIdUsuario() };
		jdbcTemplate.update(sql, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.autenticarseguridad.AutenticaUsuarioDao#existeIdUsusarioAPP(java.lang.Long)
	 */
	@Override
	public Boolean existeIdUsusarioAPP(Long idUsuario) throws Exception {
		String sql = PropertiesManager.obtenerCadena("coreSQL/coreSQL", "UsuarioAPP.existeUsuarioAPPByIdUser");
		long cantidad = jdbcTemplate.queryForLong(sql, new Object[] { idUsuario });
		return cantidad > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.autenticarseguridad.AutenticaUsuarioDao#existePlaca(java.lang.Long)
	 */
	@Override
	public Boolean existePlaca(String placa) throws Exception {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "AdministraUsuarioPlacaDaoJdbc.existePlaca");
		long cantidad = jdbcTemplate.queryForLong(sql, new Object[] { placa.trim().toUpperCase() });
		return cantidad > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.autenticarseguridad.AutenticaUsuarioDao#existeUsuarioAcceso(java.lang.String)
	 */
	@Override
	public boolean existeUsuarioAcceso(String usuarioAcceso) throws Exception {
		String sql = PropertiesManager.obtenerCadena("coreSQL/coreSQL", "usuarioAPP.existeUsuarioAPP");
		long cantidad = jdbcTemplate.queryForLong(sql, new Object[] { usuarioAcceso });
		return cantidad > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.autenticarseguridad.AutenticaUsuarioDao#findUserAppByIdUsuario(java.lang.Long)
	 */
	@Override
	public UsuarioAPP findUserAppByIdUsuario(Long idUsuario) throws Exception {
		String sql = PropertiesManager.obtenerCadena("coreSQL/coreSQL", "usuarioAPP.consultarUsuarioAPP");
		RowMapper<UsuarioAPP> rm = new RowMapper<UsuarioAPP>() {
			public UsuarioAPP mapRow(ResultSet rs, int rowNum) throws SQLException {
				UsuarioAPP usuarioAPP = new UsuarioAPP();
				usuarioAPP.setIdUsuario(rs.getLong("ID_USUARIO"));
				usuarioAPP.getTipoDocumentoIdentidad().setIdDocumentoIdentidad(rs.getInt("ID_DOCUMENTO_IDENTIDAD"));
				usuarioAPP.setNroDocumento(rs.getString("NRO_DOCUMENTO"));
				usuarioAPP.setNombres(rs.getString("NOMBRES"));
				usuarioAPP.setApellidos(rs.getString("APELLIDOS"));
				usuarioAPP.setCelular(rs.getString("CELULAR"));
				usuarioAPP.getTipoCiudad().getTipoDepartamento().setIdDepartamento(rs.getInt("ID_DEPARTAMENTO"));
				usuarioAPP.getTipoCiudad().setIdCiudad(rs.getInt("ID_CIUDAD"));
				usuarioAPP.getTipoCiudad().setNombre_ciudad("NOMBRE_CIUDAD");
				usuarioAPP.setDireccion(rs.getString("DIRECCION"));
				usuarioAPP.setCorreoElectronico(rs.getString("CORREO_ELECTRONICO"));
				usuarioAPP.setClave(rs.getString("CLAVE"));
				usuarioAPP.setFechaRegistro(rs.getTimestamp("FECHA_REGISTRO"));
				usuarioAPP.setFehaCambioClave(rs.getTimestamp("FECHA_CAMBIO_CLAVE"));
				usuarioAPP.setEnvioInformacion(rs.getString("ENVIO_INFORMACION"));
				usuarioAPP.setCambioClave(rs.getString("CAMBIO_CLAVE"));
				usuarioAPP.setActivo(rs.getString("ACTIVO"));
				usuarioAPP.getTipoDocumentoIdentidad().setAbreviatura(rs.getString("abreviatura"));
				return usuarioAPP;
			}
		};
		return jdbcTemplate.queryForObject(sql, new Object[] { idUsuario }, rm);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.autenticarseguridad.AuntenticaUsuarioDao#validateLogin(co.com.quipux.gestionapp.model.security.User)
	 */
	@Override
	public Long validateLogin(User user) throws Exception {
		Long idUsuario = null;
		try {
			String sql = PropertiesManager.obtenerCadena("coreSQL/coreSQL", "usuarioAPP.autenticacion");
			idUsuario = jdbcTemplate.queryForLong(sql, new Object[] { user.getName(), user.getPassword() });
		} catch (EmptyResultDataAccessException e) {
			idUsuario = null;
		}
		return idUsuario;
	}

}
