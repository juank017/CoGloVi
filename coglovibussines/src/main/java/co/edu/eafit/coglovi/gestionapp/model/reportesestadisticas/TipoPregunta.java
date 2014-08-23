package co.edu.eafit.coglovi.gestionapp.model.reportesestadisticas;

import java.io.Serializable;

public class TipoPregunta implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1615816945729122145L;
	private Integer idPregunta;
	private String nombrePregunta;
	private String orden;
	private String activo;
	public Integer getIdPregunta() {
		return idPregunta;
	}
	public void setIdPregunta(Integer idPregunta) {
		this.idPregunta = idPregunta;
	}
	public String getNombrePregunta() {
		return nombrePregunta;
	}
	public void setNombrePregunta(String nombrePregunta) {
		this.nombrePregunta = nombrePregunta;
	}
	public String getOrden() {
		return orden;
	}
	public void setOrden(String orden) {
		this.orden = orden;
	}
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	
	
}
