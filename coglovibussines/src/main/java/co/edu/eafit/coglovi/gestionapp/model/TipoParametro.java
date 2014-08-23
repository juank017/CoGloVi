/* @(#)TipoParametro.java  1.0  25/05/2012
 *
 * @autor QUIPUX S.A.
 * @version 1.0
 * @modify 25/05/2012 16:32
 */

package co.edu.eafit.coglovi.gestionapp.model;

import java.io.Serializable;

/**TipoParametro.java  1.1  25/05/2012
 * <p>
 * Clase que proporciona las propiedades, y los metodos getters y setters para 
 * trasladar y modificar la informacion de la tabla TIPO_PARAMETRO
 * <p>
 * @author QUIPUX S.A.
 * @version 1.1
 * @since 1.1
 * @modify 25/05/2012 16:32
 */

/**
 * tipoParametro
 * 
 */
public class TipoParametro implements Serializable {
	// Atributos de la clase

	private static final long serialVersionUID = -4445834777592167101L;
	private int idParametro;
	private int orden;
	private String nombreParametro;
	private String valorParametro;


	public TipoParametro() {
		super();
	}

	public TipoParametro(int idParametro) {
		super();
		this.idParametro = idParametro;
	}

	public int getIdParametro() {
		return this.idParametro;
	}

	public int getOrden() {
		return this.orden;
	}

	public String getNombreParametro() {
		return this.nombreParametro;
	}

	public String getValorParametro() {
		return this.valorParametro;
	}

	public void setIdParametro(int idParametro) {
		this.idParametro = idParametro;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public void setNombreParametro(String nombreParametro) {
		this.nombreParametro = nombreParametro;
	}

	public void setValorParametro(String valorParametro) {
		this.valorParametro = valorParametro;
	}

}
