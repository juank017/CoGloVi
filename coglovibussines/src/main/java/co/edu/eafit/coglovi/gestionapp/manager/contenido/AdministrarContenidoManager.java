package co.edu.eafit.coglovi.gestionapp.manager.contenido;

import java.util.List;

import co.edu.eafit.coglovi.gestionapp.model.autenticausuario.RestResponse;
import co.edu.eafit.coglovi.gestionapp.model.contenido.ConfiguraContenidoVO;
import co.edu.eafit.coglovi.gestionapp.model.contenido.ConfiguracionContenido;
import co.edu.eafit.coglovi.gestionapp.model.contenido.PreguntasVO;
import co.edu.eafit.coglovi.gestionapp.model.reportesestadisticas.DetalleEncuesta;
import co.edu.eafit.coglovi.gestionapp.model.reportesestadisticas.EncuestaUsuario;
import co.edu.eafit.coglovi.gestionapp.model.reportesestadisticas.TipoPreguntaRespuesta;

public interface AdministrarContenidoManager {

	/**
	 * Adiciona una pregunta y/o respuesta frecuente.
	 * 
	 * @param configuraContenidoVO
	 * @throws Exception
	 */
	void adicionarPreguntaRespuesta(ConfiguraContenidoVO configuraContenidoVO) throws Exception;

	/**
	 * Edita las preguntas y respuestas frecuentes
	 * 
	 * @param configuraContenidoVO
	 * @throws Exception
	 */
	void editarPreguntaRespuesta(ConfiguraContenidoVO configuraContenidoVO) throws Exception;

	/**
	 * Elimina una pregunta y/o respuesta frecuente
	 * 
	 * @param listConfiguraContenidoVO
	 * @throws Exception
	 */
	void eliminarPreguntaRespuesta(List<ConfiguracionContenido> listConfiguraContenidoVO) throws Exception;

	/**
	 * 
	 * @param configuraContenidoVO
	 * @return
	 * @throws Exception
	 */
	ConfiguraContenidoVO findByFiltros(ConfiguraContenidoVO configuraContenidoVO) throws Exception;

	/**
	 * Obtiene la lista de preguntas con sus respuestas posibles para el cuestionario de experiencia de usuario<br>
	 * Creado el 6/08/2014 a las 11:25:30 <br>
	 * 
	 * @return List<TipoPreguntaRespuesta>
	 * @throws Exception
	 */
	List<TipoPreguntaRespuesta> findPreguntasExperiencia() throws Exception;

	/**
	 * Obtiene la lista de preguntas frecuentes<br>
	 * Creado el 16/07/2014 a las 8:02:06 <br>
	 * 
	 * @return List<ConfiguracionContenido>
	 * @throws Exception
	 */
	List<PreguntasVO> findPreguntasFrecuentes() throws Exception;

	/**
	 * Consulta la url del manual de usuario.
	 * 
	 * @return
	 * @throws Exception
	 */
	String findUrlManual() throws Exception;

	/**
	 * Recibe la encuesta de experiencia por usuario
	 * 
	 * @param encuestaUser
	 * @param listDetalleEncuesta
	 * @return
	 * @throws Exception
	 */
	RestResponse registrarEncuestaExperiencia(EncuestaUsuario encuestaUser, List<DetalleEncuesta> listDetalleEncuesta) throws Exception;

	/**
	 * Modifica la informacion de tipo pregunta y tipo respuesta
	 * 
	 * @param configuraContenidoVO
	 * @return
	 * @throws Exception
	 */
	void updateEncuestaUsuario(ConfiguraContenidoVO configuraContenidoVO) throws Exception;

	/**
	 * Modifica la Url del manual de usuario
	 * 
	 * @param configuraContenidoVO
	 */
	void updateUrlManual(ConfiguraContenidoVO configuraContenidoVO) throws Exception;

}
