package co.edu.eafit.coglovi.model.usuariovehiculo;

public class PlacaIn {

	private String placa;
	private Integer idDocumento;
	private String nroDocumento;
	private String propietario;

	public Integer getIdDocumento() {
		return idDocumento;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public String getPlaca() {
		return placa;
	}

	public String getPropietario() {
		return propietario;
	}

	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

}
