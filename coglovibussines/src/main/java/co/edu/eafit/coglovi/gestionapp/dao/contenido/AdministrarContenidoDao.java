package co.edu.eafit.coglovi.gestionapp.dao.contenido;

import java.util.List;

import co.edu.eafit.coglovi.gestionapp.model.contenido.ConfiguraContenidoVO;
import co.edu.eafit.coglovi.gestionapp.model.contenido.ConfiguracionContenido;
import co.edu.eafit.coglovi.gestionapp.model.contenido.PreguntasVO;
import co.edu.eafit.coglovi.gestionapp.model.reportesestadisticas.DetalleEncuesta;
import co.edu.eafit.coglovi.gestionapp.model.reportesestadisticas.EncuestaUsuario;
import co.edu.eafit.coglovi.gestionapp.model.reportesestadisticas.TipoPregunta;
import co.edu.eafit.coglovi.gestionapp.model.reportesestadisticas.TipoPreguntaRespuesta;
import co.edu.eafit.coglovi.gestionapp.model.reportesestadisticas.TipoRespuesta;

public interface AdministrarContenidoDao {

	/**
	 * Adiciona una pregunta y/o respuesta frecuente.
	 * 
	 * @param configuracionContenido
	 */
	void adicionarPreguntaRespuesta(ConfiguraContenidoVO configuraContenidoVO);

	/**
	 * Eliminar una pregunta y/o respuesta frecuente.
	 * 
	 * @param configuracionContenido
	 */
	void eliminarPreguntaRespuestaFrecuente(ConfiguracionContenido configuracionContenido);

	/**
	 * Consulta los registros de la tabla configuracionContenido por tipo de opcion.
	 * 
	 * @param tipoOpcion
	 * @return
	 * @throws Exception
	 */
	List<ConfiguracionContenido> findConfiguracionContenidoByOpcion(int tipoOpcion, int tipoClase) throws Exception;

	/**
	 * Obtiene las preguntas configuradas para experiencia de usuario<br>
	 * Creado el 6/08/2014 a las 11:34:30 <br>
	 * 
	 * @return List<TipoPreguntaRespuesta>
	 * @throws Exception
	 */
	List<TipoPreguntaRespuesta> findPreguntasExperiencia() throws Exception;

	/**
	 * Consulta en la base de datos la lista de preguntas frecuentes configurada<br>
	 * Creado el 16/07/2014 a las 8:03:24 <br>
	 * 
	 * @return List<PreguntasVO>
	 * @throws Exception
	 */
	List<PreguntasVO> findPreguntasFrecuentes() throws Exception;

	/**
	 * Consulta sobre la tabla tipo pregunta por identificador primario
	 * 
	 * @param idPregunta
	 * @return
	 * @throws Exception
	 */
	TipoPregunta findTipoPreguntaById(int idPregunta) throws Exception;

	/**
	 * Consulta la tabla tipo respuesta
	 * 
	 * @return
	 * @throws Exception
	 */
	List<TipoRespuesta> findTipoRespuesta() throws Exception;

	/**
	 * Obtiene las respuestas configuradas para una pregunta<br>
	 * Creado el 6/08/2014 a las 11:35:08 <br>
	 * 
	 * @param idPregunta
	 * @return List<TipoRespuesta>
	 * @throws Exception
	 */
	List<TipoRespuesta> findTipoRespuestaByPregunta(Integer idPregunta) throws Exception;

	/**
	 * Registra el detalle de la encuesta por usuario
	 * 
	 * @param detalleEncuesta
	 * @throws Exception
	 */
	void registrarDetalleEncuestaUsuario(DetalleEncuesta detalleEncuesta) throws Exception;

	/**
	 * Registra el encabezado de la encuesta por usuario
	 * 
	 * @param encuestaUser
	 * @throws Exception
	 */
	void registrarEncuestaUsuario(EncuestaUsuario encuestaUser) throws Exception;

	/**
	 * Actualiza pregunta y respuesta frecuente
	 * 
	 * @param configuracionContenido
	 */
	void updatePreguntaRespuestaFrecuente(ConfiguracionContenido configuracionContenido);

	/**
	 * Modifica el nombre de la pregunta seleccionada
	 * 
	 * @param encuestaUsuarioVO
	 */
	void updateTipoPregunta(TipoPregunta tipoPregunta);

	/**
	 * Modifica el nombre de las respuestas
	 * 
	 * @param encuestaUsuarioVO
	 */
	void updateTipoRespuesta(TipoRespuesta tipoRespuesta);

	/**
	 * Actualiza la url del manual de usuario
	 * 
	 * @param urlManualUsuario
	 */
	void updateUrlManualUsuario(String urlManualUsuario);

}
