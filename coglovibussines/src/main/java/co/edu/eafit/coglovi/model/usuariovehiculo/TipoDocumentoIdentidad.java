package co.edu.eafit.coglovi.model.usuariovehiculo;

import java.io.Serializable;

public class TipoDocumentoIdentidad implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1169064208176768220L;
	private Integer idDocumentoIdentidad;
	private String nombreDocumento;
	private String codigoDocumento;
	private String tipoPersona;
	private String abreviatura;
	
	public Integer getIdDocumentoIdentidad() {
		return idDocumentoIdentidad;
	}
	public void setIdDocumentoIdentidad(Integer idDocumentoIdentidad) {
		this.idDocumentoIdentidad = idDocumentoIdentidad;
	}
	public String getNombreDocumento() {
		return nombreDocumento;
	}
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}
	public String getCodigoDocumento() {
		return codigoDocumento;
	}
	public void setCodigoDocumento(String codigoDocumento) {
		this.codigoDocumento = codigoDocumento;
	}
	public String getTipoPersona() {
		return tipoPersona;
	}
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	public String getAbreviatura() {
		return abreviatura;
	}
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}
	
	

}
