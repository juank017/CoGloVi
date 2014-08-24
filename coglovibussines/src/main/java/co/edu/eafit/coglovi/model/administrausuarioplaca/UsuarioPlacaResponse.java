package co.edu.eafit.coglovi.model.administrausuarioplaca;

import java.util.ArrayList;
import java.util.List;

import co.edu.eafit.coglovi.model.autenticausuario.RestResponse;
import co.edu.eafit.coglovi.model.usuariovehiculo.UsuarioAPP;
import co.edu.eafit.coglovi.model.usuariovehiculo.Vehiculo;

public class UsuarioPlacaResponse {
	
	private List<Vehiculo> listVehiculos;
	private UsuarioAPP usuarioAPP;
	private RestResponse restResponse;
	
	public RestResponse getRestResponse() {
		if(restResponse == null){
			restResponse = new RestResponse();
		}
		return restResponse;
	}

	public void setRestResponse(RestResponse restResponse) {
		this.restResponse = restResponse;
	}

	public List<Vehiculo> getListVehiculos() {
		if(listVehiculos == null){
			listVehiculos = new ArrayList<Vehiculo>();
		}
		return listVehiculos;
	}
	
	public void setListVehiculos(List<Vehiculo> listVehiculos) {
		this.listVehiculos = listVehiculos;
	}
	
	public UsuarioAPP getUsuarioAPP() {
		if(usuarioAPP == null){
			usuarioAPP = new UsuarioAPP();
		}
		return usuarioAPP;
	}
	
	public void setUsuarioAPP(UsuarioAPP usuarioAPP) {
		this.usuarioAPP = usuarioAPP;
	}
	
	
}
