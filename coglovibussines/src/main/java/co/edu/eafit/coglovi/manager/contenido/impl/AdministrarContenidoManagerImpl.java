package co.edu.eafit.coglovi.manager.contenido.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.eafit.coglovi.dao.contenido.AdministrarContenidoDao;
import co.edu.eafit.coglovi.dao.util.DaoUtil;
import co.edu.eafit.coglovi.manager.contenido.AdministrarContenidoManager;
import co.edu.eafit.coglovi.model.Constantes;
import co.edu.eafit.coglovi.model.Constantes.FiltroBusqueda;
import co.edu.eafit.coglovi.model.autenticausuario.RestResponse;
import co.edu.eafit.coglovi.model.contenido.ConfiguraContenidoVO;
import co.edu.eafit.coglovi.model.contenido.ConfiguracionContenido;
import co.edu.eafit.coglovi.model.contenido.PreguntasVO;
import co.edu.eafit.coglovi.model.reportesestadisticas.DetalleEncuesta;
import co.edu.eafit.coglovi.model.reportesestadisticas.EncuestaUsuario;
import co.edu.eafit.coglovi.model.reportesestadisticas.TipoPreguntaRespuesta;
import co.edu.eafit.coglovi.model.reportesestadisticas.TipoRespuesta;
import co.edu.eafit.coglovi.transversal.PropertiesManager;

/**
 * Clase que implementa los metodos necesarios para la configuraci�n de contenido<br>
 * Creado el 16/07/2014 a las 8:18:30 <br>
 * 
 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
 */
@Service(value = "AdministrarContenidoManager")
public class AdministrarContenidoManagerImpl implements AdministrarContenidoManager {

	@Autowired
	private AdministrarContenidoDao contenidoDao;
	@Autowired
	private DaoUtil daoUtil;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.quipux.gestionapp.manager.contenido.AdministrarContenidoManager#adicionarPreguntaRespuesta(co.com.quipux.gestionapp.model.contenido.
	 * ConfiguraContenidoVO)
	 */
	@Override
	public void adicionarPreguntaRespuesta(ConfiguraContenidoVO configuraContenidoVO) throws Exception {
		contenidoDao.adicionarPreguntaRespuesta(configuraContenidoVO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.manager.contenido.AdministrarContenidoManager#editarPreguntaRespuesta(co.com.quipux.gestionapp.model.contenido.
	 * ConfiguraContenidoVO)
	 */
	@Override
	public void editarPreguntaRespuesta(ConfiguraContenidoVO configuraContenidoVO) throws Exception {
		contenidoDao.updatePreguntaRespuestaFrecuente(configuraContenidoVO.getConfiguracionContenido());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.manager.contenido.AdministrarContenidoManager#eliminarPreguntaRespuesta(java.util.List)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void eliminarPreguntaRespuesta(List<ConfiguracionContenido> listConfiguraContenidoVO) throws Exception {
		for (ConfiguracionContenido configuracionContenido : listConfiguraContenidoVO) {
			contenidoDao.eliminarPreguntaRespuestaFrecuente(configuracionContenido);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.quipux.gestionapp.manager.contenido.AdministrarContenidoManager#findByFiltros(co.com.quipux.gestionapp.model.contenido.ConfiguraContenidoVO
	 * )
	 */
	@Override
	public ConfiguraContenidoVO findByFiltros(ConfiguraContenidoVO configuraContenidoVO) throws Exception {
		List<TipoRespuesta> listTipoRespuesta = null;
		switch (configuraContenidoVO.getCriterioBusqueda()) {
		case FiltroBusqueda.CRITERIO_PREGUNTAS_FRECUENTES:
			configuraContenidoVO.setListPreguntasFrecuentes(contenidoDao.findConfiguracionContenidoByOpcion(
					Constantes.ConfiguracionContenido.TipoOpcion.PREGUNTAS_FRECUENTES, configuraContenidoVO.getTipoClasePregunta()
							.getIdClasePregunta()));
			break;

		case FiltroBusqueda.CRITERIO_MANUAL_USUARIO:
			configuraContenidoVO.setUrlManualUsuario(findUrlManual());
			break;

		case FiltroBusqueda.CRITERIO_ENCUESTA_EXPERIENCIA_USUARIO:
			configuraContenidoVO.getEncuestaUsuarioVO()
					.setTipoPregunta(contenidoDao.findTipoPreguntaById(configuraContenidoVO.getCriterioPregunta()));
			listTipoRespuesta = contenidoDao.findTipoRespuesta();
			if (listTipoRespuesta.isEmpty()) {
				throw new Exception();
			} else {
				configuraContenidoVO.getEncuestaUsuarioVO().setRespuesta1(listTipoRespuesta.get(0));
				configuraContenidoVO.getEncuestaUsuarioVO().setRespuesta2(listTipoRespuesta.get(1));
				configuraContenidoVO.getEncuestaUsuarioVO().setRespuesta3(listTipoRespuesta.get(2));
			}
			break;
		}
		return configuraContenidoVO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.manager.contenido.AdministrarContenidoManager#findPreguntasExperiencia()
	 */
	@Override
	public List<TipoPreguntaRespuesta> findPreguntasExperiencia() throws Exception {
		List<TipoPreguntaRespuesta> listaPreguntas = contenidoDao.findPreguntasExperiencia();
		for (TipoPreguntaRespuesta pregunta : listaPreguntas) {
			pregunta.setRespuestas(contenidoDao.findTipoRespuestaByPregunta(pregunta.getPregunta().getIdPregunta()));
		}
		return listaPreguntas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.manager.contenido.AdministrarContenidoManager#findPreguntasFrecuentes()
	 */
	@Override
	public List<PreguntasVO> findPreguntasFrecuentes() throws Exception {
		return contenidoDao.findPreguntasFrecuentes();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.manager.contenido.AdministrarContenidoManager#findUrlManual()
	 */
	@Override
	public String findUrlManual() throws Exception {
		return daoUtil.findValorParametro(Constantes.TipoParametro.URL_MANUAL_APP_FOTODETECCION);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.manager.contenido.AdministrarContenidoManager#registrarEncuestaExperiencia(co.com.quipux.gestionapp.model.
	 * reportesestadisticas.EncuestaUsuario, java.util.List)
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public RestResponse registrarEncuestaExperiencia(EncuestaUsuario encuestaUser, List<DetalleEncuesta> listDetalleEncuesta) throws Exception {
		RestResponse restResponse = null;
		if ((encuestaUser == null) || listDetalleEncuesta == null) {
			throw new Exception();
		} else {
			// Registro el encabezado de la encuesta
			encuestaUser.setFechaRegistro(new Date());
			contenidoDao.registrarEncuestaUsuario(encuestaUser);
			// Registro el detalle de la encuesta
			for (DetalleEncuesta detalleEncuesta : listDetalleEncuesta) {
				detalleEncuesta.getEncuestaUsuario().setIdEncuesta(encuestaUser.getIdEncuesta());
				contenidoDao.registrarDetalleEncuestaUsuario(detalleEncuesta);
			}
			restResponse = new RestResponse();
			restResponse.setCode(Constantes.CodigoEstadoComucacion.RESULTADO_EXITOSO);
			restResponse.setDescription(PropertiesManager.getText("qxgestionapp.administraContenido.registrarEncuesta.exitoso"));
		}
		return restResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.manager.contenido.AdministrarContenidoManager#saveEncuestaUsuario(co.com.quipux.gestionapp.model.contenido.
	 * ConfiguraContenidoVO)
	 */
	@Override
	public void updateEncuestaUsuario(ConfiguraContenidoVO configuraContenidoVO) throws Exception {
		contenidoDao.updateTipoPregunta(configuraContenidoVO.getEncuestaUsuarioVO().getTipoPregunta());
		contenidoDao.updateTipoRespuesta(configuraContenidoVO.getEncuestaUsuarioVO().getRespuesta1());
		contenidoDao.updateTipoRespuesta(configuraContenidoVO.getEncuestaUsuarioVO().getRespuesta2());
		contenidoDao.updateTipoRespuesta(configuraContenidoVO.getEncuestaUsuarioVO().getRespuesta3());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.manager.contenido.AdministrarContenidoManager#updateUrlManual(co.com.quipux.gestionapp.model.contenido.
	 * ConfiguraContenidoVO)
	 */
	@Override
	public void updateUrlManual(ConfiguraContenidoVO configuraContenidoVO) throws Exception {
		contenidoDao.updateUrlManualUsuario(configuraContenidoVO.getUrlManualUsuario());
	}
}
