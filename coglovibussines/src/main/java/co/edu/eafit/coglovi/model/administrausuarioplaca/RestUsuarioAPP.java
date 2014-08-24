package co.edu.eafit.coglovi.model.administrausuarioplaca;

import co.edu.eafit.coglovi.model.usuariovehiculo.TipoCiudad;
import co.edu.eafit.coglovi.model.usuariovehiculo.TipoDocumentoIdentidad;

public class RestUsuarioAPP {

	private Long idUsuario;
	private TipoDocumentoIdentidad tipoDocumentoIdentidad;
	private String nroDocumento;
	private String nombres;
	private String apellidos;
	private String celular;
	private TipoCiudad tipoCiudad;
	private String direccion;
	private String correoElectronico;
	private String usuarioAcceso;
	private String clave;
	private Long fechaRegistro;
	private Long fehaCambioClave;
	private String envioInformacion;
	private String cambioClave;
	private String activo;
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public TipoDocumentoIdentidad getTipoDocumentoIdentidad() {
		return tipoDocumentoIdentidad;
	}
	public void setTipoDocumentoIdentidad(TipoDocumentoIdentidad tipoDocumentoIdentidad) {
		this.tipoDocumentoIdentidad = tipoDocumentoIdentidad;
	}
	public String getNroDocumento() {
		return nroDocumento;
	}
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public TipoCiudad getTipoCiudad() {
		return tipoCiudad;
	}
	public void setTipoCiudad(TipoCiudad tipoCiudad) {
		this.tipoCiudad = tipoCiudad;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public String getUsuarioAcceso() {
		return usuarioAcceso;
	}
	public void setUsuarioAcceso(String usuarioAcceso) {
		this.usuarioAcceso = usuarioAcceso;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public Long getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Long fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Long getFehaCambioClave() {
		return fehaCambioClave;
	}
	public void setFehaCambioClave(Long fehaCambioClave) {
		this.fehaCambioClave = fehaCambioClave;
	}
	public String getEnvioInformacion() {
		return envioInformacion;
	}
	public void setEnvioInformacion(String envioInformacion) {
		this.envioInformacion = envioInformacion;
	}
	public String getCambioClave() {
		return cambioClave;
	}
	public void setCambioClave(String cambioClave) {
		this.cambioClave = cambioClave;
	}
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}	
}
