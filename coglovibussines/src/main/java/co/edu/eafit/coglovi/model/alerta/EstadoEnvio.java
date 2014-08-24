package co.edu.eafit.coglovi.model.alerta;

import java.io.Serializable;
import java.util.Date;

public class EstadoEnvio implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2958857825936934146L;
	private Long idEstadoEnvio;
	private EnvioAlertaUsuario envioAlertaUsuario;
	private TipoEstadoAlerta tipoEstadoAlerta;
	private Date fechaInicio;
	private Date fechaFinal;
	private String activo;
	
	public Long getIdEstadoEnvio() {
		return idEstadoEnvio;
	}
	public void setIdEstadoEnvio(Long idEstadoEnvio) {
		this.idEstadoEnvio = idEstadoEnvio;
	}
	public EnvioAlertaUsuario getEnvioAlertaUsuario() {
		if(envioAlertaUsuario == null){
			envioAlertaUsuario = new EnvioAlertaUsuario();
		}
		return envioAlertaUsuario;
	}
	public void setEnvioAlertaUsuario(EnvioAlertaUsuario envioAlertaUsuario) {
		this.envioAlertaUsuario = envioAlertaUsuario;
	}
	public TipoEstadoAlerta getTipoEstadoAlerta() {
		if(tipoEstadoAlerta == null){
			tipoEstadoAlerta = new TipoEstadoAlerta();
		}
		return tipoEstadoAlerta;
	}
	public void setTipoEstadoAlerta(TipoEstadoAlerta tipoEstadoAlerta) {
		this.tipoEstadoAlerta = tipoEstadoAlerta;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}

	
}
