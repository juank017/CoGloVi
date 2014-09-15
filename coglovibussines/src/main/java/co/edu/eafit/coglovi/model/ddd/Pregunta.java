package co.edu.eafit.coglovi.model.ddd;

import java.util.Date;

import co.edu.eafit.coglovi.model.usuario.GrupoInteres;
import co.edu.eafit.coglovi.model.usuario.UsuarioAPP;


/**
 * @version 1.0
 * @created 14-sep.-2014 5:26:58 p. m.
 */
public class Pregunta {

	private Date fechaRegistro;
	private String pregunta;
	private Estado estado;
	private GrupoInteres grupoInteres;
	private UsuarioAPP usuario;

	
	public Date getFechaRegistro() {
		return fechaRegistro;
	}


	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}


	public String getPregunta() {
		return pregunta;
	}


	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}


	public Estado getEstado() {
		if(estado==null){
			estado=new Estado();
		}
		return estado;
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
	}



	public UsuarioAPP getUsuario() {
		if(usuario==null){
			usuario=new UsuarioAPP();
		}
		
		return usuario;
	}


	public void setUsuario(UsuarioAPP usuario) {
		this.usuario = usuario;
	}


	/**
	 * 
	 * @param estado
	 */
	public void cambiarEstado(int estado){

	}


	public GrupoInteres getGrupoInteres() {
		if(grupoInteres==null){
			grupoInteres=new GrupoInteres();
		}
		return grupoInteres;
	}


	public void setGrupoInteres(GrupoInteres grupoInteres) {
		this.grupoInteres = grupoInteres;
	}
}//end Pregunta