package co.edu.eafit.coglovi.dao.alertas.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import co.edu.eafit.coglovi.dao.alertas.EstadoEnvioDao;
import co.edu.eafit.coglovi.dao.util.DaoTemplate;
import co.edu.eafit.coglovi.dao.util.DaoUtil;
import co.edu.eafit.coglovi.model.Constantes.Activo;
import co.edu.eafit.coglovi.model.alerta.EstadoEnvio;
import co.edu.eafit.coglovi.transversal.PropertiesManager;

/**
 * Ejecuta las operaciones en la base de datos con relacion al estado de una alerta<br>
 * Creado el 20/06/2014 a las 8:04:18 <br>
 * 
 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
 */
@Repository
public class EstadoEnvioDaoJdbc extends DaoTemplate implements EstadoEnvioDao {

	@Autowired
	private DaoUtil daoUtil;

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.alertas.EstadoEnvioDao#actualizarEstadoEnvio(co.com.quipux.gestionapp.model.alerta.EstadoEnvio)
	 */
	@Override
	public void cerrarEstadoEnvio(EstadoEnvio estadoEnvio) {
		estadoEnvio.setFechaFinal(new Date());
		estadoEnvio.setActivo(Activo.NO);
		String update = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "EstadoEnvio.cerrarEstadoEnvio");
		Object[] param = new Object[] { estadoEnvio.getFechaFinal(), estadoEnvio.getActivo() , estadoEnvio.getIdEstadoEnvio()};
		jdbcTemplate.update(update, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.alertas.EstadoEnvioDao#findEstadoActualAlerta(java.lang.Long)
	 */
	@Override
	public EstadoEnvio findEstadoActualAlerta(Long idEnvioAlerta) {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "EstadoEnvioDaoJdbc.findEstadoActualAlerta");
		RowMapper<EstadoEnvio> rm = new RowMapper<EstadoEnvio>() {
			public EstadoEnvio mapRow(ResultSet rs, int rowNum) throws SQLException {
				EstadoEnvio estadoEnvio = new EstadoEnvio();
				estadoEnvio.setIdEstadoEnvio(rs.getLong("id_estado_envio"));
				estadoEnvio.getEnvioAlertaUsuario().setIdEnvioAlerta(rs.getLong("id_envio_alerta"));
				estadoEnvio.getTipoEstadoAlerta().setIdEstado(rs.getInt("id_estado"));
				estadoEnvio.setFechaInicio(rs.getDate("fecha_inicio"));
				estadoEnvio.setFechaFinal(rs.getDate("fecha_final"));
				return estadoEnvio;
			}
		};
		try {
			return jdbcTemplate.queryForObject(sql, new Object[] { idEnvioAlerta }, rm);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.alertas.EstadoEnvioDao#registrarEstadoEnvio(co.com.quipux.gestionapp.model.alerta.EstadoEnvio)
	 */
	@Override
	public void registrarEstadoEnvio(EstadoEnvio estadoEnvio){
		estadoEnvio.setFechaInicio(new Date());
		estadoEnvio.setActivo(Activo.SI);
		String secuencia = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "EstadoEnvio.secuencia");
		estadoEnvio.setIdEstadoEnvio(daoUtil.getSecuenciaLong(secuencia));
		String insert = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "EstadoEnvio.registrarEstadoEnvio");
		Object[] param = new Object[] { estadoEnvio.getIdEstadoEnvio(), estadoEnvio.getEnvioAlertaUsuario().getIdEnvioAlerta(),
				estadoEnvio.getTipoEstadoAlerta().getIdEstado(), estadoEnvio.getFechaInicio(), estadoEnvio.getActivo() };
		jdbcTemplate.update(insert, param);
	}

}
