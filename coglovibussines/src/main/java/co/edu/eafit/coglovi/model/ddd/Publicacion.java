package co.edu.eafit.coglovi.model.ddd;

import co.edu.eafit.coglovi.model.usuario.GrupoInteres;
import co.edu.eafit.coglovi.model.usuario.UsuarioAPP;

/**
 * @version 1.0
 * @created 14-sep.-2014 5:26:58 p. m.
 */
public class Publicacion {

	private byte[] archivo;
	private String nombre;
	private GrupoInteres grupoInteres;
	private UsuarioAPP usuario;
	public byte[] getArchivo() {
		return archivo;
	}
	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public UsuarioAPP getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioAPP usuario) {
		this.usuario = usuario;
	}
	public GrupoInteres getGrupoInteres() {
		return grupoInteres;
	}
	public void setGrupoInteres(GrupoInteres grupoInteres) {
		this.grupoInteres = grupoInteres;
	}


}//end Publicacion