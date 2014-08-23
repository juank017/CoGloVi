package co.edu.eafit.coglovi.gestionapp.model.contenido;

import java.io.Serializable;

public class TipoClasePregunta implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4472492119119957428L;
	private Integer idClasePregunta;
	private String nombreClase;
	private String activo;
	
	public Integer getIdClasePregunta() {
		return idClasePregunta;
	}
	public void setIdClasePregunta(Integer idClasePregunta) {
		this.idClasePregunta = idClasePregunta;
	}
	public String getNombreClase() {
		return nombreClase;
	}
	public void setNombreClase(String nombreClase) {
		this.nombreClase = nombreClase;
	}
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	

}
