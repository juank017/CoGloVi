package co.edu.eafit.coglovi.gestionapp.model.usuariovehiculo;

import java.io.Serializable;

public class Vehiculo implements Serializable {

	private static final long serialVersionUID = -8906810074920035186L;
	private Long idVehiculo;
	private String placa;

	public Long getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(Long idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

}
