package co.edu.eafit.coglovi.gestionapp.model.usuariovehiculo;

import java.io.Serializable;
import java.util.Date;


public class UsuarioAPP implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8105064581098347654L;
	private Long idUsuario;
	private TipoDocumentoIdentidad tipoDocumentoIdentidad;
	private String nroDocumento;
	private String nombres;
	private String apellidos;
	private String celular;
	private TipoCiudad tipoCiudad;
	private String direccion;
	private String correoElectronico;
	private String clave;
	private Date fechaRegistro;
	private Date fehaCambioClave;
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
		if(tipoDocumentoIdentidad == null){
			tipoDocumentoIdentidad = new TipoDocumentoIdentidad();
		}
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
		if(tipoCiudad == null){
			tipoCiudad = new TipoCiudad();
		}
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
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Date getFehaCambioClave() {
		return fehaCambioClave;
	}
	public void setFehaCambioClave(Date fehaCambioClave) {
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
