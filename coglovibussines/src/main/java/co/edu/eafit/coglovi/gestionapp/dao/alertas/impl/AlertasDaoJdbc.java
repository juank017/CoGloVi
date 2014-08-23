package co.edu.eafit.coglovi.gestionapp.dao.alertas.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import co.edu.eafit.coglovi.gestionapp.dao.alertas.AlertasDao;
import co.edu.eafit.coglovi.gestionapp.dao.util.DaoTemplate;
import co.edu.eafit.coglovi.gestionapp.dao.util.DaoUtil;
import co.edu.eafit.coglovi.gestionapp.model.Constantes.Alertas.TipoDirectorioAlerta;
import co.edu.eafit.coglovi.gestionapp.model.alerta.Alerta;
import co.edu.eafit.coglovi.gestionapp.model.alerta.EstadoEnvio;
import co.edu.eafit.coglovi.gestionapp.model.alerta.UserAlert;
import co.edu.eafit.coglovi.gestionapp.model.usuariovehiculo.UsuarioVehiculo;
import co.edu.eafit.coglovi.gestionapp.transversal.PropertiesManager;

/**
 * Administra las consultas sql para las alertas <br>
 * Creado el 17/06/2014 a las 16:31:29 <br>
 * 
 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
 */
@Repository
public class AlertasDaoJdbc extends DaoTemplate implements AlertasDao {

	@Autowired
	private DaoUtil daoUtil;

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.alertas.AlertasDao#existeAlertaByUserVehiculo(java.lang.Long, java.lang.Long)
	 */
	@Override
	public boolean existeAlertaByUserVehiculo(Long idUser, Long idVehiculo) {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "EstadoEnvio.existeAlertaByUserVehiculo");
		long cantidad = jdbcTemplate.queryForLong(sql, new Object[] { idUser, idVehiculo });
		return cantidad > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.alertas.AlertasDao#existeAlertaIdentificacion(java.lang.String)
	 */
	@Override
	public boolean existeAlertaIdentificacion(String identificadorAlerta) {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "AlertasDaoJdbc.existeAlertaIndentificacion");
		long cantidad = jdbcTemplate.queryForLong(sql, new Object[] { identificadorAlerta });
		return cantidad > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.alertas.AlertasDao#findAlertaByUsuarioAlerta(co.com.quipux.gestionapp.model.alerta.UserAlert)
	 */
	@Override
	public EstadoEnvio findAlertaByUsuarioAlerta(UserAlert userAlert) {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "UsuarioVehiculo.findAlertaByUsuarioAlerta");
		RowMapper<EstadoEnvio> rm = new RowMapper<EstadoEnvio>() {
			public EstadoEnvio mapRow(ResultSet rs, int rowNum) throws SQLException {
				EstadoEnvio estadoEnvio = new EstadoEnvio();
				estadoEnvio.getEnvioAlertaUsuario().getAlerta().setIdAlerta(rs.getLong("id_alerta"));
				estadoEnvio.getEnvioAlertaUsuario().getAlerta().setContenido(rs.getString("contenido"));
				estadoEnvio.getEnvioAlertaUsuario().setIdEnvioAlerta(rs.getLong("Id_Envio_Alerta"));
				estadoEnvio.getEnvioAlertaUsuario().setIdDirectorio(daoUtil.getInteger(rs, "id_directorio"));
				estadoEnvio.getTipoEstadoAlerta().setIdEstado(rs.getInt("Id_Estado"));
				return estadoEnvio;
			}
		};
		try {
			return jdbcTemplate.queryForObject(sql,
					new Object[] { userAlert.getIdUsuario(), userAlert.getIdAlerta(), TipoDirectorioAlerta.ELIMINADO }, rm);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.alertas.AlertasDao#findAlertasByUsuario(java.lang.Long)
	 */
	@Override
	public List<EstadoEnvio> findAlertasByUsuario(Long idUsuario) {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "Alertas.findAlertasByUsuario");
		RowMapper<EstadoEnvio> rm = new RowMapper<EstadoEnvio>() {
			public EstadoEnvio mapRow(ResultSet rs, int rowNum) throws SQLException {
				EstadoEnvio estadoEnvio = new EstadoEnvio();
				estadoEnvio.getEnvioAlertaUsuario().getAlerta().setIdAlerta(rs.getLong("id_alerta"));
				estadoEnvio.getEnvioAlertaUsuario().setIdDirectorio(rs.getInt("id_directorio"));
				estadoEnvio.getEnvioAlertaUsuario().getAlerta().setContenido(rs.getString("contenido"));
				estadoEnvio.getEnvioAlertaUsuario().setIdDirectorio(daoUtil.getInteger(rs, "id_directorio"));
				estadoEnvio.getEnvioAlertaUsuario().setIdEnvioAlerta(rs.getLong("Id_Envio_Alerta"));
				estadoEnvio.getTipoEstadoAlerta().setIdEstado(rs.getInt("Id_Estado"));
				return estadoEnvio;
			}
		};
		try {
			return jdbcTemplate.query(sql, new Object[] { idUsuario, TipoDirectorioAlerta.ELIMINADO }, rm);
		} catch (EmptyResultDataAccessException e) {
			return new ArrayList<EstadoEnvio>();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.alertas.AlertasDao#findUsuarioVehiculo(co.com.quipux.gestionapp.model.alerta.Alerta)
	 */
	@Override
	public List<UsuarioVehiculo> findUsuarioVehiculo(Alerta alerta) {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "UsuarioVehiculo.findUsuarioVehiculo");
		RowMapper<UsuarioVehiculo> rm = new RowMapper<UsuarioVehiculo>() {
			public UsuarioVehiculo mapRow(ResultSet rs, int rowNum) throws SQLException {
				UsuarioVehiculo usuarioVehiculo = new UsuarioVehiculo();
				usuarioVehiculo.getVehiculo().setIdVehiculo(rs.getLong("Id_Vehiculo"));
				usuarioVehiculo.getUsuarioAPP().setIdUsuario(rs.getLong("Id_Usuario"));
				usuarioVehiculo.setPropietario(rs.getString("Propietario"));
				usuarioVehiculo.getTipoDocumentoIdentidad().setIdDocumentoIdentidad(rs.getInt("Id_Documento_Identidad"));
				usuarioVehiculo.setNroDocumento(rs.getString("Nro_Documento"));
				return usuarioVehiculo;
			}
		};
		return jdbcTemplate.query(sql,
				new Object[] { alerta.getCanal(), alerta.getTipoDocumentoIdentidad().getIdDocumentoIdentidad(), alerta.getNumeroDocumento() }, rm);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.alertas.AlertasDao#registrarAlerta(co.com.quipux.gestionapp.model.alerta.Alerta)
	 */
	@Override
	public void registrarAlerta(Alerta alerta) {
		String secuencia = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "Alertas.secuencia");
		alerta.setIdAlerta(daoUtil.getSecuenciaLong(secuencia));
		alerta.setFechaRegistro(new Date());
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "AlertasDaoJdbc.registrarAlerta");
		Object[] param = new Object[] { alerta.getIdAlerta(), alerta.getTipoCanal().getIdTipoCanal(), alerta.getIdentificadorAlerta(),
				alerta.getContenido(), alerta.getCanal(), alerta.getTipoDocumentoIdentidad().getIdDocumentoIdentidad(), alerta.getNumeroDocumento(),
				alerta.getFechaRegistro() };
		jdbcTemplate.update(sql, param);
	}

}
