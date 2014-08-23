package co.edu.eafit.coglovi.gestionapp.model.usuariovehiculo;

import java.io.Serializable;

public class TipoCiudad implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 699375743957701533L;
	private Integer idCiudad;
	private String codigoCiudad;
	private String nombre_ciudad;
	private TipoDepartamento tipoDepartamento;
	public Integer getIdCiudad() {
		return idCiudad;
	}
	public void setIdCiudad(Integer idCiudad) {
		this.idCiudad = idCiudad;
	}
	public String getCodigoCiudad() {
		return codigoCiudad;
	}
	public void setCodigoCiudad(String codigoCiudad) {
		this.codigoCiudad = codigoCiudad;
	}
	public String getNombre_ciudad() {
		return nombre_ciudad;
	}
	public void setNombre_ciudad(String nombre_ciudad) {
		this.nombre_ciudad = nombre_ciudad;
	}
	public TipoDepartamento getTipoDepartamento() {
		if(tipoDepartamento == null){
			tipoDepartamento = new TipoDepartamento();
		}
		return tipoDepartamento;
	}
	public void setTipoDepartamento(TipoDepartamento tipoDepartamento) {
		this.tipoDepartamento = tipoDepartamento;
	}
	
	
}
