package co.edu.eafit.coglovi.model.reportesestadisticas;

import java.io.Serializable;

public class TipoRespuesta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3465963876260433752L;
	private Integer idRespuesta;
	private String nombreRespuesta;
	private String activo;
	public Integer getIdRespuesta() {
		return idRespuesta;
	}
	public void setIdRespuesta(Integer idRespuesta) {
		this.idRespuesta = idRespuesta;
	}
	public String getNombreRespuesta() {
		return nombreRespuesta;
	}
	public void setNombreRespuesta(String nombreRespuesta) {
		this.nombreRespuesta = nombreRespuesta;
	}
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
}
