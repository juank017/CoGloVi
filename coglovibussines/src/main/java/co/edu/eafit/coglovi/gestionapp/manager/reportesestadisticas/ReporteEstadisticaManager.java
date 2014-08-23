package co.edu.eafit.coglovi.gestionapp.manager.reportesestadisticas;

import java.util.List;

import co.edu.eafit.coglovi.gestionapp.model.reportesestadisticas.ReporteEstadisticaVO;

public interface ReporteEstadisticaManager {

	/**
	 * Recibe los datos de consulta para graficos.
	 * 
	 * @param reporteEstadisticaVO
	 * @return
	 * @throws java.text.ParseException
	 */
	List<ReporteEstadisticaVO> findChartByFiltros(ReporteEstadisticaVO reporteEstadisticaVO) throws Exception;

	/**
	 * Recibe los datos de consulta para reportes.
	 * 
	 * @param reporteEstadisticaVO
	 * @return
	 */
	List<ReporteEstadisticaVO> findListByFiltros(ReporteEstadisticaVO reporteEstadisticaVO) throws Exception;

}
