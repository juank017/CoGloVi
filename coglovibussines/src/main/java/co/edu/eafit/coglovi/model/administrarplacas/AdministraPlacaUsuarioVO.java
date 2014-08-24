package co.edu.eafit.coglovi.model.administrarplacas;

import java.io.Serializable;

import co.edu.eafit.coglovi.model.usuariovehiculo.TipoDocumentoIdentidad;
import co.edu.eafit.coglovi.model.usuariovehiculo.UsuarioAPP;
import co.edu.eafit.coglovi.model.usuariovehiculo.Vehiculo;

public class AdministraPlacaUsuarioVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8753730947350060958L;
	private int criterioBusqueda;
	private UsuarioAPP usuarioAPP;
	private Vehiculo vehiculo;
	private TipoDocumentoIdentidad tipoDocumentoIdentidad;


	public int getCriterioBusqueda() {
		return criterioBusqueda;
	}


	public TipoDocumentoIdentidad getTipoDocumentoIdentidad() {
		if (tipoDocumentoIdentidad == null) {
			tipoDocumentoIdentidad = new TipoDocumentoIdentidad();
		}
		return tipoDocumentoIdentidad;
	}

	public UsuarioAPP getUsuarioAPP() {
		if (usuarioAPP == null) {
			usuarioAPP = new UsuarioAPP();
		}
		return usuarioAPP;
	}

	public Vehiculo getVehiculo() {
		if (vehiculo == null) {
			vehiculo = new Vehiculo();
		}
		return vehiculo;
	}

	public void setCriterioBusqueda(int criterioBusqueda) {
		this.criterioBusqueda = criterioBusqueda;
	}

	public void setTipoDocumentoIdentidad(TipoDocumentoIdentidad tipoDocumentoIdentidad) {
		this.tipoDocumentoIdentidad = tipoDocumentoIdentidad;
	}

	public void setUsuarioAPP(UsuarioAPP usuarioAPP) {
		this.usuarioAPP = usuarioAPP;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

}
