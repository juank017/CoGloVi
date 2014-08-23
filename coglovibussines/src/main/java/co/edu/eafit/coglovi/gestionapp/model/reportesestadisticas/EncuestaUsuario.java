package co.edu.eafit.coglovi.gestionapp.model.reportesestadisticas;

import java.io.Serializable;
import java.util.Date;

import co.edu.eafit.coglovi.gestionapp.model.usuariovehiculo.UsuarioAPP;

public class EncuestaUsuario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3852092983387373574L;
	private Integer idEncuesta;
	private UsuarioAPP usuarioAPP;
	private Date fechaRegistro;
	private String recomendacion;

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public Integer getIdEncuesta() {
		return idEncuesta;
	}

	public String getRecomendacion() {
		return recomendacion;
	}

	public UsuarioAPP getUsuarioAPP() {
		if (usuarioAPP == null) {
			usuarioAPP = new UsuarioAPP();
		}
		return usuarioAPP;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public void setIdEncuesta(Integer idEncuesta) {
		this.idEncuesta = idEncuesta;
	}

	public void setRecomendacion(String recomendacion) {
		this.recomendacion = recomendacion;
	}

	public void setUsuarioAPP(UsuarioAPP usuarioAPP) {
		this.usuarioAPP = usuarioAPP;
	}
}
