package co.edu.eafit.coglovi.model.contenido;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ConfiguraContenidoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1210392642761086902L;
	private int criterioBusqueda;
	private int criterioPregunta;
	private List<ConfiguracionContenido> listPreguntasFrecuentes;
	private ConfiguracionContenido configuracionContenido;
	private String urlManualUsuario;
	private EncuestaUsuarioVO encuestaUsuarioVO;
	private TipoClasePregunta tipoClasePregunta; 
	private int idUserSession;
	
	public int getCriterioBusqueda() {
		return criterioBusqueda;
	}
	public void setCriterioBusqueda(int criterioBusqueda) {
		this.criterioBusqueda = criterioBusqueda;
	}
	public int getCriterioPregunta() {
		return criterioPregunta;
	}
	public void setCriterioPregunta(int criterioPregunta) {
		this.criterioPregunta = criterioPregunta;
	}
	public List<ConfiguracionContenido> getListPreguntasFrecuentes() {
		if(listPreguntasFrecuentes == null){
			listPreguntasFrecuentes = new ArrayList<ConfiguracionContenido>();
		}
		return listPreguntasFrecuentes;
	}
	public void setListPreguntasFrecuentes(List<ConfiguracionContenido> listPreguntasFrecuentes) {
		this.listPreguntasFrecuentes = listPreguntasFrecuentes;
	}
	public ConfiguracionContenido getConfiguracionContenido() {
		if(configuracionContenido == null){
			configuracionContenido = new ConfiguracionContenido();
		}
		return configuracionContenido;
	}
	public void setConfiguracionContenido(ConfiguracionContenido configuracionContenido) {
		this.configuracionContenido = configuracionContenido;
	}
	public String getUrlManualUsuario() {
		return urlManualUsuario;
	}
	public void setUrlManualUsuario(String urlManualUsuario) {
		this.urlManualUsuario = urlManualUsuario;
	}
	public EncuestaUsuarioVO getEncuestaUsuarioVO() {
		if(encuestaUsuarioVO == null){
			encuestaUsuarioVO = new EncuestaUsuarioVO();
		}
		return encuestaUsuarioVO;
	}
	public void setEncuestaUsuarioVO(EncuestaUsuarioVO encuestaUsuarioVO) {
		this.encuestaUsuarioVO = encuestaUsuarioVO;
	}
	public TipoClasePregunta getTipoClasePregunta() {
		if(tipoClasePregunta == null){
			tipoClasePregunta = new TipoClasePregunta();
		}
		return tipoClasePregunta;
	}
	public void setTipoClasePregunta(TipoClasePregunta tipoClasePregunta) {
		this.tipoClasePregunta = tipoClasePregunta;
	}
	public int getIdUserSession() {
		return idUserSession;
	}
	public void setIdUserSession(int idUserSession) {
		this.idUserSession = idUserSession;
	}
	
	
}
