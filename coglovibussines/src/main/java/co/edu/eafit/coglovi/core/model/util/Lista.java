package co.edu.eafit.coglovi.core.model.util;

import java.io.Serializable;

/**TODO: descripción  <br>
 * Creado el 23/05/2012 a las  20:30:11 <br>
 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
 */
public class Lista implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3149542048800563039L;

	int idColumna;
	
	String descColumna;

	public int getIdColumna() {
		return idColumna;
	}

	public void setIdColumna(int idColumna) {
		this.idColumna = idColumna;
	}

	public String getDescColumna() {
		return descColumna;
	}

	public void setDescColumna(String descColumna) {
		this.descColumna = descColumna;
	}
}

