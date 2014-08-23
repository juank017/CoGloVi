package co.edu.eafit.coglovi.gestionapp.dao.reportesestadisticas;

import java.util.List;

import co.edu.eafit.coglovi.gestionapp.exception.QxException;
import co.edu.eafit.coglovi.gestionapp.model.reportesestadisticas.ReporteEstadisticaVO;
import co.edu.eafit.coglovi.gestionapp.model.reportesestadisticas.TipoRespuesta;

public interface ReporteEstadisticaDao {

	/**
	 * Consulta alertas enviadas.
	 * 
	 * @param reporteEstadisticaVO
	 * @return
	 * @throws QxException
	 */
	List<ReporteEstadisticaVO> findAlertasEnviadas(ReporteEstadisticaVO reporteEstadisticaVO) throws QxException;

	/**
	 * Consulta los resultados de las encuestas de la experiencia del usuario.
	 * 
	 * @param reporteEstadisticaVO
	 * @return
	 * @throws QxException
	 */
	List<ReporteEstadisticaVO> findEncuestaExperienciaUsuario(ReporteEstadisticaVO reporteEstadisticaVO) throws QxException;

	/**
	 * Consulta personas registrados en el sistema.
	 * 
	 * @param reporteEstadisticaVO
	 * @return
	 * @throws QxException
	 */
	List<ReporteEstadisticaVO> findPersonasRegistradas(ReporteEstadisticaVO reporteEstadisticaVO) throws QxException;

	/**
	 * Consulta las placas con mayor numero de comparendos por mes.
	 * 
	 * @param reporteEstadisticaVO
	 * @return
	 * @throws QxException
	 */
	List<ReporteEstadisticaVO> findPlacasMayorNroComparendos(ReporteEstadisticaVO reporteEstadisticaVO) throws QxException;

	/**
	 * Consulta los vehiculos registrados por mes.
	 * 
	 * @param reporteEstadisticaVO
	 * @return
	 * @throws QxException
	 */
	List<ReporteEstadisticaVO> findVehiculosRegistrados(ReporteEstadisticaVO reporteEstadisticaVO) throws QxException;
	
	/**
	 * Consulta las respustas de experiencia por usuario en BD
	 * @return
	 * @throws QxException
	 */
	List<TipoRespuesta> findTipoRespuesta() throws QxException;

}
