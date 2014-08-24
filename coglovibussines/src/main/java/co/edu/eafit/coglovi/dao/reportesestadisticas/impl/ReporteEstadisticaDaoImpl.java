package co.edu.eafit.coglovi.dao.reportesestadisticas.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import co.edu.eafit.coglovi.dao.reportesestadisticas.ReporteEstadisticaDao;
import co.edu.eafit.coglovi.dao.util.DaoTemplate;
import co.edu.eafit.coglovi.dao.util.DaoUtil;
import co.edu.eafit.coglovi.exception.QxException;
import co.edu.eafit.coglovi.model.Constantes.TipoParametro;
import co.edu.eafit.coglovi.model.Constantes.Alertas.TipoEstadoAlerta;
import co.edu.eafit.coglovi.model.reportesestadisticas.ReporteEstadisticaVO;
import co.edu.eafit.coglovi.model.reportesestadisticas.TipoRespuesta;
import co.edu.eafit.coglovi.transversal.PropertiesManager;

@Repository
public class ReporteEstadisticaDaoImpl extends DaoTemplate implements ReporteEstadisticaDao {

	@Autowired
	private DaoUtil daoUtil;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.quipux.gestionapp.dao.reportesestadisticas.ReporteEstadisticaDao#findAlertasEnviadas(co.com.quipux.gestionapp.model.reportesestadisticas
	 * .ReporteEstadisticaVO)
	 */
	@Override
	public List<ReporteEstadisticaVO> findAlertasEnviadas(ReporteEstadisticaVO reporteEstadisticaVO) throws QxException {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "ReporteEstadistica.findAlertasEnviadas");
		RowMapper<ReporteEstadisticaVO> rm = new RowMapper<ReporteEstadisticaVO>() {
			public ReporteEstadisticaVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ReporteEstadisticaVO reporteEstadisticaVO = new ReporteEstadisticaVO();
				reporteEstadisticaVO.setCantidadTotal(rs.getInt("CANTIDAD_TOTAL"));
				reporteEstadisticaVO.setChartMesAnio(rs.getString("CHARTANIOMES"));
				reporteEstadisticaVO.setMesAnio(rs.getString("MESANIO"));
				reporteEstadisticaVO.getEstadoEnvio().getTipoEstadoAlerta().setIdEstado(rs.getInt("ID_ESTADO"));
				return reporteEstadisticaVO;
			}
		};
		return jdbcTemplate.query(sql, new Object[] { TipoEstadoAlerta.ENVIADA, TipoEstadoAlerta.ENTREGADA, reporteEstadisticaVO.getFechaInicial(),
				reporteEstadisticaVO.getFechaFinal() }, rm);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.reportesestadisticas.ReporteEstadisticaDao#findEncuestaExperienciaUsuario(co.com.quipux.gestionapp.model.
	 * reportesestadisticas.ReporteEstadisticaVO)
	 */
	@Override
	public List<ReporteEstadisticaVO> findEncuestaExperienciaUsuario(ReporteEstadisticaVO reporteEstadisticaVO) throws QxException {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "ReporteEstadistica.findEncuestaExperienciaUsuario");
		RowMapper<ReporteEstadisticaVO> rm = new RowMapper<ReporteEstadisticaVO>() {
			public ReporteEstadisticaVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ReporteEstadisticaVO reporteEstadisticaVO = new ReporteEstadisticaVO();
				reporteEstadisticaVO.setCantidadTotal(rs.getInt("CANTIDAD_TOTAL"));
				reporteEstadisticaVO.getDetalleEncuesta().getTipoRespuesta().setIdRespuesta(rs.getInt("ID_RESPUESTA"));
				reporteEstadisticaVO.getDetalleEncuesta().getTipoPregunta().setIdPregunta(rs.getInt("ID_PREGUNTA"));
				reporteEstadisticaVO.getDetalleEncuesta().getTipoRespuesta().setNombreRespuesta(rs.getString("NOMBRE_RESPUESTA"));
				reporteEstadisticaVO.getDetalleEncuesta().getTipoPregunta().setNombrePregunta(rs.getString("NOMBRE_PREGUNTA"));
				return reporteEstadisticaVO;
			}
		};
		return jdbcTemplate.query(sql, new Object[] { reporteEstadisticaVO.getFechaInicial(), reporteEstadisticaVO.getFechaFinal() }, rm);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.quipux.gestionapp.dao.reportesestadisticas.ReporteEstadisticaDao#findPersonasRegistradas(co.com.quipux.gestionapp.model.reportesestadisticas
	 * .ReporteEstadisticaVO)
	 */
	@Override
	public List<ReporteEstadisticaVO> findPersonasRegistradas(ReporteEstadisticaVO reporteEstadisticaVO) throws QxException {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "ReporteEstadistica.findPersonasRegistradas");
		RowMapper<ReporteEstadisticaVO> rm = new RowMapper<ReporteEstadisticaVO>() {
			public ReporteEstadisticaVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ReporteEstadisticaVO reporteEstadisticaVO = new ReporteEstadisticaVO();
				reporteEstadisticaVO.getUsuarioAPP().setIdUsuario(rs.getLong("ID_USUARIO"));
				reporteEstadisticaVO.getUsuarioAPP().setNroDocumento(rs.getString("ABREVIATURA") + " " + rs.getString("NRO_DOCUMENTO"));
				reporteEstadisticaVO.getUsuarioAPP().setNombres(rs.getString("NOMBRES") + " " + rs.getString("APELLIDOS"));
				reporteEstadisticaVO.getUsuarioAPP().setCorreoElectronico(rs.getString("CORREO_ELECTRONICO"));
				return reporteEstadisticaVO;
			}
		};
		return jdbcTemplate.query(sql, new Object[] { reporteEstadisticaVO.getFechaInicial(), reporteEstadisticaVO.getFechaFinal() }, rm);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.reportesestadisticas.ReporteEstadisticaDao#findPlacasMayorNroComparendos(co.com.quipux.gestionapp.model.
	 * reportesestadisticas.ReporteEstadisticaVO)
	 */
	@Override
	public List<ReporteEstadisticaVO> findPlacasMayorNroComparendos(ReporteEstadisticaVO reporteEstadisticaVO) throws QxException {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "ReporteEstadistica.findPlacasMayorNroComparendos");
		RowMapper<ReporteEstadisticaVO> rm = new RowMapper<ReporteEstadisticaVO>() {
			public ReporteEstadisticaVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ReporteEstadisticaVO reporteEstadisticaVO = new ReporteEstadisticaVO();
				reporteEstadisticaVO.setMes(rs.getString("MES"));
				reporteEstadisticaVO.getVehiculo().setPlaca(rs.getString("PLACA"));
				reporteEstadisticaVO.setCantidadTotal(rs.getInt("CANTIDAD_TOTAL"));
				return reporteEstadisticaVO;
			}
		};
		return jdbcTemplate.query(
				sql,
				new Object[] { reporteEstadisticaVO.getFechaInicial(), reporteEstadisticaVO.getFechaFinal(),
						daoUtil.findValorParametro(TipoParametro.MAYOR_NRO_COMPARENDOS_MES_FOTODETECCION) }, rm);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.reportesestadisticas.ReporteEstadisticaDao#findTipoRespuesta()
	 */
	@Override
	public List<TipoRespuesta> findTipoRespuesta() throws QxException {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "ReporteEstadistica.findTipoRespuesta");
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
	 * @see co.com.quipux.gestionapp.dao.reportesestadisticas.ReporteEstadisticaDao#findVehiculosRegistrados(co.com.quipux.gestionapp.model.
	 * reportesestadisticas.ReporteEstadisticaVO)
	 */
	@Override
	public List<ReporteEstadisticaVO> findVehiculosRegistrados(ReporteEstadisticaVO reporteEstadisticaVO) throws QxException {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "ReporteEstadistica.findVehiculosRegistrados");
		RowMapper<ReporteEstadisticaVO> rm = new RowMapper<ReporteEstadisticaVO>() {
			public ReporteEstadisticaVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ReporteEstadisticaVO reporteEstadisticaVO = new ReporteEstadisticaVO();
				reporteEstadisticaVO.setCantidadTotal(rs.getInt("CANTIDAD_TOTAL"));
				reporteEstadisticaVO.setChartMesAnio(rs.getString("MESANIO"));
				return reporteEstadisticaVO;
			}
		};
		return jdbcTemplate.query(sql, new Object[] { reporteEstadisticaVO.getFechaInicial(), reporteEstadisticaVO.getFechaFinal() }, rm);
	}

}
