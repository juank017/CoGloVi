package co.edu.eafit.coglovi.gestionapp.dao.alertas.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import co.edu.eafit.coglovi.gestionapp.dao.alertas.EnvioAlertasDao;
import co.edu.eafit.coglovi.gestionapp.dao.util.DaoTemplate;
import co.edu.eafit.coglovi.gestionapp.dao.util.DaoUtil;
import co.edu.eafit.coglovi.gestionapp.model.Constantes.Alertas.TipoEstadoAlerta;
import co.edu.eafit.coglovi.gestionapp.model.alerta.EnvioAlertaUsuario;
import co.edu.eafit.coglovi.gestionapp.model.alerta.EstadoEnvio;
import co.edu.eafit.coglovi.gestionapp.model.alerta.UserAlert;
import co.edu.eafit.coglovi.gestionapp.transversal.PropertiesManager;

/**
 * Administra los sql para envio alertas a usuarios<br>
 * Creado el 19/06/2014 a las 15:07:56 <br>
 * 
 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
 */
@Repository
public class EnvioAlertasDaoJdbc extends DaoTemplate implements EnvioAlertasDao {

	@Autowired
	private DaoUtil daoUtil;

	/*
	 * (non-Javadoc)
	 * @see co.com.quipux.gestionapp.dao.alertas.EnvioAlertasDao#findAlertasEnvioParse()
	 */
	@Override
	public List<EstadoEnvio> findAlertasEnvioParse() {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "EnvioAlerta.findAlertasEnvioParse");
		RowMapper<EstadoEnvio> rm = new RowMapper<EstadoEnvio>() {
			public EstadoEnvio mapRow(ResultSet rs, int rowNum) throws SQLException {
				EstadoEnvio estadoEnvio = new EstadoEnvio();
				estadoEnvio.getEnvioAlertaUsuario().getAlerta().setIdAlerta(rs.getLong("Id_Alerta"));
				estadoEnvio.getEnvioAlertaUsuario().getAlerta().setCanal(rs.getString("canal"));
				estadoEnvio.getEnvioAlertaUsuario().setIdEnvioAlerta(rs.getLong("Id_Envio_Alerta"));
				return estadoEnvio;
			}
		};
		return jdbcTemplate.query(sql,new Object[]{TipoEstadoAlerta.PENDIENTE_ENVIO, TipoEstadoAlerta.SIN_ENTREGAR}, rm);
	}

	/*
	 * (non-Javadoc)
	 * @see co.com.quipux.gestionapp.dao.alertas.EnvioAlertasDao#findEnvioByAlertaAndUsuario(co.com.quipux.gestionapp.model.alerta.UserAlert)
	 */
	@Override
	public EnvioAlertaUsuario findEnvioByAlertaAndUsuario(UserAlert userAlert) {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "EnvioAlerta.findEnvioByAlertaAndUsuario");
		RowMapper<EnvioAlertaUsuario> rm = new RowMapper<EnvioAlertaUsuario>() {
			public EnvioAlertaUsuario mapRow(ResultSet rs, int rowNum) throws SQLException {
				EnvioAlertaUsuario alertaUsuario= new EnvioAlertaUsuario();
				alertaUsuario.getAlerta().setIdAlerta(rs.getLong("id_alerta"));
				alertaUsuario.getUsuarioVehiculo().getUsuarioAPP().setIdUsuario(rs.getLong("id_usuario"));
				alertaUsuario.setIdEnvioAlerta(rs.getLong("id_envio_alerta"));
				return alertaUsuario;
			}
		};
		try {
			return jdbcTemplate.queryForObject(sql,new Object[]{userAlert.getIdAlerta(), userAlert.getIdUsuario()}, rm);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.alertas.EnvioAlertasDao#registrarEnvioAlerta(co.com.quipux.gestionapp.model.alerta.EnvioAlertaUsuario)
	 */
	@Override
	public void registrarEnvioAlerta(EnvioAlertaUsuario envioAlertaUsuario) {
		String secuencia = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "EnvioAlertaUsuario.secuencia");
		envioAlertaUsuario.setIdEnvioAlerta(daoUtil.getSecuenciaLong(secuencia));
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "EnvioAlertaUsuario.registrarEnvioAlerta");
		Object[] param = new Object[] { envioAlertaUsuario.getIdEnvioAlerta(), envioAlertaUsuario.getAlerta().getIdAlerta(),
				envioAlertaUsuario.getUsuarioVehiculo().getVehiculo().getIdVehiculo(),
				envioAlertaUsuario.getUsuarioVehiculo().getUsuarioAPP().getIdUsuario(), envioAlertaUsuario.getIdDirectorio() };
		jdbcTemplate.update(sql, param);

	}

	/*
	 * (non-Javadoc)
	 * @see co.com.quipux.gestionapp.dao.alertas.EnvioAlertasDao#updateDirectorioAlerta(co.com.quipux.gestionapp.model.alerta.EnvioAlertaUsuario)
	 */
	@Override
	public void updateDirectorioAlerta(EnvioAlertaUsuario envioAlerta) {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "EnvioAlertaUsuario.updateDirectorioAlerta");
		Object[] param = new Object[] { envioAlerta.getIdDirectorio(), envioAlerta.getIdEnvioAlerta() };
		jdbcTemplate.update(sql, param);
		
	}

}
