package co.edu.eafit.coglovi.gestionapp.manager.reportesestadisticas.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.eafit.coglovi.gestionapp.dao.reportesestadisticas.ReporteEstadisticaDao;
import co.edu.eafit.coglovi.gestionapp.manager.reportesestadisticas.ReporteEstadisticaManager;
import co.edu.eafit.coglovi.gestionapp.model.Constantes.FiltroBusqueda;
import co.edu.eafit.coglovi.gestionapp.model.Constantes.Alertas.TipoEstadoAlerta;
import co.edu.eafit.coglovi.gestionapp.model.Constantes.Alertas.TipoRespuesta;
import co.edu.eafit.coglovi.gestionapp.model.reportesestadisticas.ReporteEstadisticaVO;

@Service
public class ReporteEstadisticaManagerImpl implements ReporteEstadisticaManager {

	@Autowired
	private ReporteEstadisticaDao reporteEstadisticaDao;
	private final Integer NRO_VECES_BY_MES = 2;
	private final Integer NRO_VECES_BY_PREGUNTAS = 3;

	@Override
	public List<ReporteEstadisticaVO> findChartByFiltros(ReporteEstadisticaVO reporteEstadisticaVO) throws Exception {
		List<ReporteEstadisticaVO> listReporteEstadistica = null;
		List<ReporteEstadisticaVO> listAlertasEnviadas = null;
		List<ReporteEstadisticaVO> listPreguntasRespuestas = null;
		
		switch (reporteEstadisticaVO.getCriterioBusqueda()) {
		case FiltroBusqueda.CRITERIO_ESTADISTICAS_ALERTAS_ENVIADAS:
			listAlertasEnviadas = reporteEstadisticaDao.findAlertasEnviadas(reporteEstadisticaVO);
			// Ordenamos la lista por anio mes
			listReporteEstadistica = ordenarListaByMesAnio(listAlertasEnviadas);
			break;
		case FiltroBusqueda.CRITERIO_VEHICULOS_REGISTRADOS_MES:
			listReporteEstadistica = reporteEstadisticaDao.findVehiculosRegistrados(reporteEstadisticaVO);
//			Collections.sort(listReporteEstadistica);
			break;
		case FiltroBusqueda.CRITERIO_RESULTADO_ENCUESTA_EXPERIENCIA_USUARIO:
			listPreguntasRespuestas = reporteEstadisticaDao.findEncuestaExperienciaUsuario(reporteEstadisticaVO);
			listReporteEstadistica = ordenarListaByPreguntas(listPreguntasRespuestas);
			break;
		}
		return listReporteEstadistica;
	}

	@Override
	public List<ReporteEstadisticaVO> findListByFiltros(ReporteEstadisticaVO reporteEstadisticaVO) throws Exception {
		List<ReporteEstadisticaVO> listReporteEstadistica = null;
		switch (reporteEstadisticaVO.getCriterioBusqueda()) {
		case FiltroBusqueda.CRITERIO_PERSONAS_REGISTRADAS:
			listReporteEstadistica = reporteEstadisticaDao.findPersonasRegistradas(reporteEstadisticaVO);
			break;
		case FiltroBusqueda.CRITERIO_PLACAS_MAYOR_COMPARENDOS_MES:
			listReporteEstadistica = reporteEstadisticaDao.findPlacasMayorNroComparendos(reporteEstadisticaVO);
			break;
		}
		return listReporteEstadistica;
	}

	/**
	 * Recibe una lista con meses y completa su estructura, para que el seteo en la grafica permita visualizar alertas en un solo estado por mes.
	 * Ademas se ordena por mes y anio
	 * 
	 * @param listReporteEstadistica
	 * @return
	 * @throws ParseException
	 */
	private List<ReporteEstadisticaVO> ordenarListaByMesAnio(List<ReporteEstadisticaVO> listReporteEstadistica) throws ParseException {
		List<ReporteEstadisticaVO> listReporteEstadisticaReturn = new ArrayList<ReporteEstadisticaVO>();
		Integer contChartAnioMesActual = 0;
		Integer estadoAlerta = null;
		String chartAnioMesActual = null;
		SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");

		if (!listReporteEstadistica.isEmpty()) {
			for (ReporteEstadisticaVO reporteEstadisticaVO2 : listReporteEstadistica) {
				reporteEstadisticaVO2.setFecha(formatter.parse(reporteEstadisticaVO2.getMesAnio()));
				chartAnioMesActual = reporteEstadisticaVO2.getChartMesAnio();
				if (chartAnioMesActual != null) {
					for (ReporteEstadisticaVO reporteEstadisticaVO3 : listReporteEstadistica) {
						if (chartAnioMesActual.equals(reporteEstadisticaVO3.getChartMesAnio())) {
							contChartAnioMesActual++;
							estadoAlerta = reporteEstadisticaVO3.getEstadoEnvio().getTipoEstadoAlerta().getIdEstado();
						}
					}
				}
				if (contChartAnioMesActual != 0 && contChartAnioMesActual < NRO_VECES_BY_MES) {
					ReporteEstadisticaVO reporteEstadisticaVO3 = new ReporteEstadisticaVO();
					reporteEstadisticaVO3.setCantidadTotal(0);
					reporteEstadisticaVO3.setMesAnio(chartAnioMesActual);
					reporteEstadisticaVO3.setFecha(formatter.parse(reporteEstadisticaVO2.getMesAnio()));
					reporteEstadisticaVO3.getEstadoEnvio().getTipoEstadoAlerta()
							.setIdEstado(estadoAlerta == TipoEstadoAlerta.ENVIADA ? TipoEstadoAlerta.ENTREGADA : TipoEstadoAlerta.ENVIADA);
					listReporteEstadisticaReturn.add(reporteEstadisticaVO3);
				}
				contChartAnioMesActual = 0;
			}
			listReporteEstadisticaReturn.addAll(listReporteEstadistica);
			for (ReporteEstadisticaVO reporteEstadisticaVO : listReporteEstadisticaReturn) {
				reporteEstadisticaVO.setCriterioBusqueda(FiltroBusqueda.CRITERIO_ESTADISTICAS_ALERTAS_ENVIADAS);
			}
			Collections.sort(listReporteEstadisticaReturn);
		}
		return listReporteEstadisticaReturn;
	}

	/**
	 * Recibe una lista con preguntas y completa su estructura, para que el seteo en la grafica permita visualizar una o mas respuestas por mes.
	 * Adicionalmente se ordena la lista por pregunta.
	 * 
	 * @param listReporteEstadistica
	 * @return
	 * @throws ParseException
	 */
	private List<ReporteEstadisticaVO> ordenarListaByPreguntas(List<ReporteEstadisticaVO> listReporteEstadistica) throws ParseException {
		String chartPregunta = null;
		Integer contChartPreguntas = 0;
		Integer respuesta = null;
		Integer idPregunta = null;
		List<ReporteEstadisticaVO> listReporteEstadisticaReturn = new ArrayList<ReporteEstadisticaVO>();
		if (!listReporteEstadistica.isEmpty()) {
			for (ReporteEstadisticaVO reporteEstadisticaVO : listReporteEstadistica) {
				chartPregunta = reporteEstadisticaVO.getDetalleEncuesta().getTipoPregunta().getNombrePregunta();
				if (chartPregunta != null) {
					for (ReporteEstadisticaVO reporteEstadisticaVO2 : listReporteEstadistica) {
						if (chartPregunta.equals(reporteEstadisticaVO2.getDetalleEncuesta().getTipoPregunta().getNombrePregunta())) {
							contChartPreguntas++;
							respuesta = reporteEstadisticaVO2.getDetalleEncuesta().getTipoRespuesta().getIdRespuesta();
							idPregunta = reporteEstadisticaVO2.getDetalleEncuesta().getTipoPregunta().getIdPregunta();
						}
					}
				}

				if (contChartPreguntas != 0 && contChartPreguntas < NRO_VECES_BY_PREGUNTAS) {
					ReporteEstadisticaVO reporteEstadisticaVO3 = new ReporteEstadisticaVO();
					ReporteEstadisticaVO reporteEstadisticaVO4 = new ReporteEstadisticaVO();
					reporteEstadisticaVO3.setCantidadTotal(0);
					reporteEstadisticaVO3.getDetalleEncuesta().getTipoPregunta().setIdPregunta(idPregunta);
					reporteEstadisticaVO3.getDetalleEncuesta().getTipoPregunta().setNombrePregunta(chartPregunta);
					reporteEstadisticaVO4.setCantidadTotal(0);
					reporteEstadisticaVO4.getDetalleEncuesta().getTipoPregunta().setIdPregunta(idPregunta);
					reporteEstadisticaVO4.getDetalleEncuesta().getTipoPregunta().setNombrePregunta(chartPregunta);

					switch (respuesta) {
					case TipoRespuesta.SI:
						reporteEstadisticaVO3.getDetalleEncuesta().getTipoRespuesta().setIdRespuesta(TipoRespuesta.MEDIANAMENTE);
						reporteEstadisticaVO4.getDetalleEncuesta().getTipoRespuesta().setIdRespuesta(TipoRespuesta.NO);
						break;
					case TipoRespuesta.NO:
						reporteEstadisticaVO3.getDetalleEncuesta().getTipoRespuesta().setIdRespuesta(TipoRespuesta.MEDIANAMENTE);
						reporteEstadisticaVO4.getDetalleEncuesta().getTipoRespuesta().setIdRespuesta(TipoRespuesta.SI);
						break;
					case TipoRespuesta.MEDIANAMENTE:
						reporteEstadisticaVO3.getDetalleEncuesta().getTipoRespuesta().setIdRespuesta(TipoRespuesta.SI);
						reporteEstadisticaVO4.getDetalleEncuesta().getTipoRespuesta().setIdRespuesta(TipoRespuesta.NO);
						break;
					}
					listReporteEstadisticaReturn.add(reporteEstadisticaVO3);
					listReporteEstadisticaReturn.add(reporteEstadisticaVO4);
				}
				contChartPreguntas = 0;
			}
		}
		listReporteEstadisticaReturn.addAll(listReporteEstadistica);
		for (ReporteEstadisticaVO reporteEstadisticaVO : listReporteEstadisticaReturn) {
			reporteEstadisticaVO.setCriterioBusqueda(FiltroBusqueda.CRITERIO_RESULTADO_ENCUESTA_EXPERIENCIA_USUARIO);
		}
		Collections.sort(listReporteEstadisticaReturn);
		return listReporteEstadisticaReturn;
	}
}
