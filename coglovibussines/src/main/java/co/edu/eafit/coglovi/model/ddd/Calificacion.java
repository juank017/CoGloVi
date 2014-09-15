package co.edu.eafit.coglovi.model.ddd;

import co.edu.eafit.coglovi.model.usuario.UsuarioAPP;

/**
 * @author carlos.ramirez
 * @version 1.0
 * @created 14-sep.-2014 5:26:55 p. m.
 */
public class Calificacion {

	private int reputacion;
	private Respuesta m_Respuesta;
	private UsuarioAPP usuario;
	public int getReputacion() {
		return reputacion;
	}
	public void setReputacion(int reputacion) {
		this.reputacion = reputacion;
	}
	public Respuesta getM_Respuesta() {
		return m_Respuesta;
	}
	public void setM_Respuesta(Respuesta m_Respuesta) {
		this.m_Respuesta = m_Respuesta;
	}
	public UsuarioAPP getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioAPP usuario) {
		this.usuario = usuario;
	}



}//end Calificacion