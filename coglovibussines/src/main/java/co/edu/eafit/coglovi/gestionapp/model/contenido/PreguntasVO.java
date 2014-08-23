package co.edu.eafit.coglovi.gestionapp.model.contenido;

import java.io.Serializable;

public class PreguntasVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7030605297948313570L;
	private Long idConfiguracionContenido;
	private String pregunta;
	private String respuesta;
	private Integer idClase;
	
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public Integer getIdClase() {
		return idClase;
	}
	public void setIdClase(Integer idClase) {
		this.idClase = idClase;
	}
	public Long getIdConfiguracionContenido() {
		return idConfiguracionContenido;
	}
	public void setIdConfiguracionContenido(Long idConfiguracionContenido) {
		this.idConfiguracionContenido = idConfiguracionContenido;
	}
		
}
