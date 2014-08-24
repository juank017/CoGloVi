package co.edu.eafit.coglovi.model.alerta;


import java.io.Serializable;

import co.edu.eafit.coglovi.model.usuariovehiculo.UsuarioVehiculo;

public class EnvioAlertaUsuario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6422289107672555025L;
	private Long idEnvioAlerta;
	private Alerta alerta;
	private Integer idDirectorio;
	private UsuarioVehiculo usuarioVehiculo;
	
	
	
	public Integer getIdDirectorio() {
		return idDirectorio;
	}
	public void setIdDirectorio(Integer idDirectorio) {
		this.idDirectorio = idDirectorio;
	}
	public UsuarioVehiculo getUsuarioVehiculo() {
		if(usuarioVehiculo == null){
			usuarioVehiculo =new UsuarioVehiculo();
		}
		return usuarioVehiculo;
	}
	public void setUsuarioVehiculo(UsuarioVehiculo usuarioVehiculo) {
		this.usuarioVehiculo = usuarioVehiculo;
	}
	public Long getIdEnvioAlerta() {
		return idEnvioAlerta;
	}
	public void setIdEnvioAlerta(Long idEnvioAlerta) {
		this.idEnvioAlerta = idEnvioAlerta;
	}
	public Alerta getAlerta() {
		if(alerta == null){
			alerta = new Alerta();
		}
		return alerta;
	}
	public void setAlerta(Alerta alerta) {
		this.alerta = alerta;
	}
	
}
