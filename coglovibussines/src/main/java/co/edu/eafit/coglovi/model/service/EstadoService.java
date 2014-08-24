package co.edu.eafit.coglovi.model.service;

import java.io.Serializable;

public class EstadoService implements Serializable {

	private static final long serialVersionUID = -9103879508326519835L;
	private Integer CodigoEstado;
	private String descripcion;

	public Integer getCodigoEstado() {
		return CodigoEstado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setCodigoEstado(Integer codigoEstado) {
		CodigoEstado = codigoEstado;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
