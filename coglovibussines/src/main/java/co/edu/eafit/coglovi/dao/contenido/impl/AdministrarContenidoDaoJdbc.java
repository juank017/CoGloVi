package co.edu.eafit.coglovi.dao.contenido.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import co.edu.eafit.coglovi.dao.contenido.AdministrarContenidoDao;
import co.edu.eafit.coglovi.dao.util.DaoTemplate;
import co.edu.eafit.coglovi.dao.util.DaoUtil;
import co.edu.eafit.coglovi.model.Constantes;
import co.edu.eafit.coglovi.model.Constantes.ConfiguracionContenido.TipoOpcion;
import co.edu.eafit.coglovi.model.contenido.ConfiguraContenidoVO;
import co.edu.eafit.coglovi.model.contenido.ConfiguracionContenido;
import co.edu.eafit.coglovi.model.contenido.PreguntasVO;
import co.edu.eafit.coglovi.model.reportesestadisticas.DetalleEncuesta;
import co.edu.eafit.coglovi.model.reportesestadisticas.EncuestaUsuario;
import co.edu.eafit.coglovi.model.reportesestadisticas.TipoPregunta;
import co.edu.eafit.coglovi.model.reportesestadisticas.TipoPreguntaRespuesta;
import co.edu.eafit.coglovi.model.reportesestadisticas.TipoRespuesta;
import co.edu.eafit.coglovi.transversal.PropertiesManager;

/**
 * Clase que implementa los metodos necesarios para las operaciones en la base de datos acerca de la configuracion del contenido<br>
 * Creado el 16/07/2014 a las 10:31:14 <br>
 * 
 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
 */
@Repository
public class AdministrarContenidoDaoJdbc extends DaoTemplate implements AdministrarContenidoDao {

	@Autowired
	private DaoUtil daoUtil;

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.contenido.AdministrarContenidoDao# findConfiguracionContenidoByOpcion(int)
	 */
	@Override
	public List<ConfiguracionContenido> findConfiguracionContenidoByOpcion(int tipoOpcion, int tipoClase) throws Exception {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "ConfiguracionContenido.findByIdOpcion");
		RowMapper<ConfiguracionContenido> rm = new RowMapper<ConfiguracionContenido>() {
			public ConfiguracionContenido mapRow(ResultSet rs, int rowNum) throws SQLException {
				ConfiguracionContenido configuracionContenido = new ConfiguracionContenido();
				configuracionContenido.setIdConfiguracionContenido(rs.getLong("ID_CONFIGURACION_CONTENIDO"));
				configuracionContenido.setPregunta(rs.getString("PREGUNTA"));
				configuracionContenido.setRespuesta(rs.getString("RESPUESTA"));
				return configuracionContenido;
			}
		};
		return jdbcTemplate.query(sql, new Object[] { tipoOpcion, tipoClase }, rm);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.contenido.AdministrarContenidoDao#findPreguntasExperiencia()
	 */
	@Override
	public List<TipoPreguntaRespuesta> findPreguntasExperiencia() throws Exception {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "ConfiguracionContenido.findPreguntasExperiencia");
		RowMapper<TipoPreguntaRespuesta> rm = new RowMapper<TipoPreguntaRespuesta>() {
			public TipoPreguntaRespuesta mapRow(ResultSet rs, int rowNum) throws SQLException {
				TipoPreguntaRespuesta tipoPregunta = new TipoPreguntaRespuesta();
				tipoPregunta.getPregunta().setIdPregunta(rs.getInt("ID_PREGUNTA"));
				tipoPregunta.getPregunta().setNombrePregunta(rs.getString("NOMBRE_PREGUNTA"));
				tipoPregunta.getPregunta().setOrden(rs.getString("ORDEN"));
				return tipoPregunta;
			}
		};
		return jdbcTemplate.query(sql, new Object[] {}, rm);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.contenido.AdministrarContenidoDao# findPreguntasFrecuentes()
	 */
	@Override
	public List<PreguntasVO> findPreguntasFrecuentes() throws Exception {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "ConfiguracionContenido.findPreguntasFrecuentes");
		RowMapper<PreguntasVO> rm = new RowMapper<PreguntasVO>() {
			public PreguntasVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				PreguntasVO pregunta = new PreguntasVO();
				pregunta.setPregunta(rs.getString("pregunta"));
				pregunta.setRespuesta(rs.getString("respuesta"));
				pregunta.setIdClase(rs.getInt("id_clase_pregunta"));
				return pregunta;
			}
		};
		return jdbcTemplate.query(sql, new Object[] { TipoOpcion.PREGUNTAS_FRECUENTES }, rm);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.contenido.AdministrarContenidoDao# findTipoPreguntaById(int)
	 */
	@Override
	public TipoPregunta findTipoPreguntaById(int idPregunta) throws Exception {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "ConfiguracionContenido.findTipoPreguntaById");
		RowMapper<TipoPregunta> rm = new RowMapper<TipoPregunta>() {
			public TipoPregunta mapRow(ResultSet rs, int rowNum) throws SQLException {
				TipoPregunta tipoPregunta = new TipoPregunta();
				tipoPregunta.setIdPregunta(rs.getInt("ID_PREGUNTA"));
				tipoPregunta.setNombrePregunta(rs.getString("NOMBRE_PREGUNTA"));
				return tipoPregunta;
			}
		};
		return jdbcTemplate.queryForObject(sql, new Object[] { idPregunta }, rm);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.contenido.AdministrarContenidoDao#findTipoRespuesta()
	 */
	@Override
	public List<TipoRespuesta> findTipoRespuesta() throws Exception {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "ConfiguracionContenido.findTipoRespuesta");
		RowMapper<TipoRespuesta> rm = new RowMapper<TipoRespuesta>() {
			public TipoRespuesta mapRow(ResultSet rs, int rowNum) throws SQLException {
				TipoRespuesta tipoRespuesta = new TipoRespuesta();
				tipoRespuesta.setIdRespuesta(rs.getInt("ID_RESPUESTA"));
				tipoRespuesta.setNombreRespuesta(rs.getString("NOMBRE_RESPUESTA"));
				return tipoRespuesta;
			}
		};
		return jdbcTemplate.query(sql, new Object[] {}, rm);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.contenido.AdministrarContenidoDao#findTipoRespuestaByPregunta(java.lang.Integer)
	 */
	@Override
	public List<TipoRespuesta> findTipoRespuestaByPregunta(Integer idPregunta) throws Exception {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "ConfiguracionContenido.findTipoRespuestaByPregunta");
		RowMapper<TipoRespuesta> rm = new RowMapper<TipoRespuesta>() {
			public TipoRespuesta mapRow(ResultSet rs, int rowNum) throws SQLException {
				TipoRespuesta tipoRespuesta = new TipoRespuesta();
				tipoRespuesta.setIdRespuesta(rs.getInt("ID_RESPUESTA"));
				tipoRespuesta.setNombreRespuesta(rs.getString("NOMBRE_RESPUESTA"));
				return tipoRespuesta;
			}
		};
		return jdbcTemplate.query(sql, new Object[] { idPregunta }, rm);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.contenido.AdministrarContenidoDao# registrarDetalleEncuestaUsuario
	 * (co.com.quipux.gestionapp.model.reportesestadisticas .DetalleEncuesta)
	 */
	@Override
	public void registrarDetalleEncuestaUsuario(DetalleEncuesta detalleEncuesta) throws Exception {
		String secuencia = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "ConfiguracionContenido.secuencua.detalleEncuestaUsuario");
		detalleEncuesta.setIdDetalleEncuesta(daoUtil.getSecuenciaLong(secuencia));
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "ConfiguracionContenido.insert.detalleEncuestaUsuario");
		Object[] param = new Object[] { detalleEncuesta.getIdDetalleEncuesta(), detalleEncuesta.getEncuestaUsuario().getIdEncuesta(),
				detalleEncuesta.getTipoPregunta().getIdPregunta(), detalleEncuesta.getTipoRespuesta().getIdRespuesta() };
		jdbcTemplate.update(sql, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.contenido.AdministrarContenidoDao# registrarEncuestaUsuario
	 * (co.com.quipux.gestionapp.model.reportesestadisticas .EncuestaUsuario)
	 */
	@Override
	public void registrarEncuestaUsuario(EncuestaUsuario encuestaUser) throws Exception {
		String secuencia = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "ConfiguracionContenido.secuencua.encuestaUsuario");
		encuestaUser.setIdEncuesta(daoUtil.getSecuenciaInt(secuencia));
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "ConfiguracionContenido.insert.encuestaUsuario");
		Object[] param = new Object[] { encuestaUser.getIdEncuesta(), encuestaUser.getUsuarioAPP().getIdUsuario(), encuestaUser.getFechaRegistro(),
				encuestaUser.getRecomendacion() };
		jdbcTemplate.update(sql, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.contenido.AdministrarContenidoDao#updatePreguntaRespuestaFrecuente(co.com.quipux.gestionapp.model.contenido.
	 * ConfiguracionContenido)
	 */
	@Override
	public void updatePreguntaRespuestaFrecuente(ConfiguracionContenido configuracionContenido) {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "ConfiguracionContenido.editarPreguntaRespuesta");
		Object[] param = new Object[] { configuracionContenido.getPregunta(), configuracionContenido.getRespuesta(),
				configuracionContenido.getIdConfiguracionContenido() };
		jdbcTemplate.update(sql, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.quipux.gestionapp.dao.contenido.AdministrarContenidoDao#eliminarPreguntaRespuestaFrecuente(co.com.quipux.gestionapp.model.contenido.
	 * ConfiguracionContenido)
	 */
	@Override
	public void eliminarPreguntaRespuestaFrecuente(ConfiguracionContenido configuracionContenido) {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "ConfiguracionContenido.eliminarPreguntaRespuesta");
		Object[] param = new Object[] { configuracionContenido.getIdConfiguracionContenido() };
		jdbcTemplate.update(sql, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.contenido.AdministrarContenidoDao#adicionarPreguntaRespuesta(co.com.quipux.gestionapp.model.contenido.
	 * ConfiguracionContenido)
	 */
	@Override
	public void adicionarPreguntaRespuesta(ConfiguraContenidoVO configuraContenidoVO) {
		String secuencia = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "ConfiguracionContenido.secuencia");
		configuraContenidoVO.getConfiguracionContenido().setIdConfiguracionContenido(daoUtil.getSecuenciaLong(secuencia));
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "ConfiguracionContenido.insertarPreguntaRespuesta");
		Object[] param = new Object[] { configuraContenidoVO.getConfiguracionContenido().getIdConfiguracionContenido(),
				TipoOpcion.PREGUNTAS_FRECUENTES, configuraContenidoVO.getIdUserSession(),
				configuraContenidoVO.getTipoClasePregunta().getIdClasePregunta(), configuraContenidoVO.getConfiguracionContenido().getPregunta(),
				configuraContenidoVO.getConfiguracionContenido().getRespuesta(), "S"};
		jdbcTemplate.update(sql, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.quipux.gestionapp.dao.contenido.AdministrarContenidoDao#editTipoPregunta(co.com.quipux.gestionapp.model.contenido.EncuestaUsuarioVO)
	 */
	@Override
	public void updateTipoPregunta(TipoPregunta tipoPregunta) {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "ConfiguracionContenido.updateNombreTipoPregunta");
		Object[] param = new Object[] { tipoPregunta.getNombrePregunta(), tipoPregunta.getIdPregunta() };
		jdbcTemplate.update(sql, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.quipux.gestionapp.dao.contenido.AdministrarContenidoDao#editTipoRespuesta(co.com.quipux.gestionapp.model.contenido.EncuestaUsuarioVO)
	 */
	@Override
	public void updateTipoRespuesta(TipoRespuesta tipoRespuesta) {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "ConfiguracionContenido.updateNombreTipoRespuesta");
		Object[] param = new Object[] { tipoRespuesta.getNombreRespuesta(), tipoRespuesta.getIdRespuesta() };
		jdbcTemplate.update(sql, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.contenido.AdministrarContenidoDao#updateUrlManualUsuario(java.lang.String)
	 */
	@Override
	public void updateUrlManualUsuario(String urlManualUsuario) {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "ConfiguracionContenido.updateUrlManualUsuario");
		Object[] param = new Object[] { urlManualUsuario, Constantes.TipoParametro.URL_MANUAL_APP_FOTODETECCION };
		jdbcTemplate.update(sql, param);
	}
}
