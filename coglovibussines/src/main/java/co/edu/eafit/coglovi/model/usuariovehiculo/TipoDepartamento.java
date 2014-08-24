package co.edu.eafit.coglovi.model.usuariovehiculo;

import java.io.Serializable;

public class TipoDepartamento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9110799197205400073L;
	private Integer idDepartamento;
	private String nombreDepartamento;
	private String codigoDepartamento;
	private TipoPais tipoPais;
	private String abreviatura;
	
	public Integer getIdDepartamento() {
		return idDepartamento;
	}
	public void setIdDepartamento(Integer idDepartamento) {
		this.idDepartamento = idDepartamento;
	}
	public String getNombreDepartamento() {
		return nombreDepartamento;
	}
	public void setNombreDepartamento(String nombreDepartamento) {
		this.nombreDepartamento = nombreDepartamento;
	}
	public String getCodigoDepartamento() {
		return codigoDepartamento;
	}
	public void setCodigoDepartamento(String codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}
	public TipoPais getTipoPais() {
		if(tipoPais == null){
			tipoPais = new TipoPais();
		}
		return tipoPais;
	}
	public void setTipoPais(TipoPais tipoPais) {
		this.tipoPais = tipoPais;
	}
	public String getAbreviatura() {
		return abreviatura;
	}
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}
	
	
}
