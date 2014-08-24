/* @(#)UsuarioSistema.java  1.0  23/05/2012
 *
 * @autor QUIPUX S.A.
 * @version 1.0
 * @modify 23/05/2012 10:40
 */

package co.edu.eafit.coglovi.model.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**UsuarioSistema.java  1.1  23/05/2012
 * <p>
 * Clase que proporciona las propiedades, y los metodos getters y setters para 
 * trasladar y modificar la informacion de la tabla USUARIO_SISTEMA
 * <p>
 * @author QUIPUX S.A.
 * @version 1.1
 * @since 1.1
 * @modify 23/05/2012 10:40
 */

/**
 * usuarioSistema
 * 
 */
public class UsuarioSistema implements Serializable {
	
	private static final long serialVersionUID = -6461021731873327902L;

	// Atributos de la clase
	private Integer idUsuario;
	private String usuarioAcceso;
	private String claveAcceso;
	private String nombre;
	private String apellido;
	private String identificacion;
	private List<String> recursos;
	
	public Integer getIdUsuario() {
		return idUsuario;
	}



	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}



	public String getUsuarioAcceso() {
		return usuarioAcceso;
	}



	public void setUsuarioAcceso(String usuarioAcceso) {
		this.usuarioAcceso = usuarioAcceso;
	}



	public String getClaveAcceso() {
		return claveAcceso;
	}



	public void setClaveAcceso(String claveAcceso) {
		this.claveAcceso = claveAcceso;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getIdentificacion() {
		return identificacion;
	}



	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public void setRecursos(List<String> recursos) {
		this.recursos = recursos;
	}



	public UsuarioSistema() {
	}

	

	public List<String> getRecursos() {
		if (recursos == null) {
			recursos = new ArrayList<String>();
		}
		return recursos;
	}



	public String getApellido() {
		return apellido;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	
}
