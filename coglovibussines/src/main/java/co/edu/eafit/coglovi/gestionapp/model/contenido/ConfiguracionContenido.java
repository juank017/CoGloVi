package co.edu.eafit.coglovi.gestionapp.model.contenido;

import java.io.Serializable;
import java.util.Date;

import co.edu.eafit.coglovi.gestionapp.model.security.UsuarioSistema;

public class ConfiguracionContenido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1173496537615371185L;
	private Long idConfiguracionContenido;
	private TipoOpcion tipoOpcion;
	private UsuarioSistema usuarioSistema;
	private Date fechaInicio;
	private Date fechaFin;
	private String texto;
	private String url;
	private String pregunta;
	private String respuesta;
	private String activo;

	public Long getIdConfiguracionContenido() {
		return idConfiguracionContenido;
	}

	public void setIdConfiguracionContenido(Long idConfiguracionContenido) {
		this.idConfiguracionContenido = idConfiguracionContenido;
	}

	public TipoOpcion getTipoOpcion() {
		if (tipoOpcion == null) {
			tipoOpcion = new TipoOpcion();
		}
		return tipoOpcion;
	}

	public void setTipoOpcion(TipoOpcion tipoOpcion) {
		this.tipoOpcion = tipoOpcion;
	}

	public UsuarioSistema getUsuarioSistema() {
		if (usuarioSistema == null) {
			usuarioSistema = new UsuarioSistema();
		}
		return usuarioSistema;
	}

	public void setUsuarioSistema(UsuarioSistema usuarioSistema) {
		this.usuarioSistema = usuarioSistema;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

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

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

}
