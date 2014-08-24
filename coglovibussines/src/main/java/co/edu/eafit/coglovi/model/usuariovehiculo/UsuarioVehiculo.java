package co.edu.eafit.coglovi.model.usuariovehiculo;

public class UsuarioVehiculo {
	private Vehiculo vehiculo;
	private UsuarioAPP usuarioAPP;
	private String propietario;
	private TipoDocumentoIdentidad tipoDocumentoIdentidad;
	private String nroDocumento;
	
	public Vehiculo getVehiculo() {
		if(vehiculo == null){
			vehiculo = new Vehiculo();
		}
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	public UsuarioAPP getUsuarioAPP() {
		if(usuarioAPP == null){
			usuarioAPP = new UsuarioAPP();
		}
		return usuarioAPP;
	}
	public void setUsuarioAPP(UsuarioAPP usuarioAPP) {
		this.usuarioAPP = usuarioAPP;
	}
	public String getPropietario() {
		return propietario;
	}
	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}
	public TipoDocumentoIdentidad getTipoDocumentoIdentidad() {
		if(tipoDocumentoIdentidad == null){
			tipoDocumentoIdentidad = new TipoDocumentoIdentidad();
		}
		return tipoDocumentoIdentidad;
	}
	public void setTipoDocumentoIdentidad(
			TipoDocumentoIdentidad tipoDocumentoIdentidad) {
		this.tipoDocumentoIdentidad = tipoDocumentoIdentidad;
	}
	public String getNroDocumento() {
		return nroDocumento;
	}
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}
	
}
