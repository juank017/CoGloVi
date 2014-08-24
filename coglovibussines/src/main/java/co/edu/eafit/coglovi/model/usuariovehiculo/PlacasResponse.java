package co.edu.eafit.coglovi.model.usuariovehiculo;

import java.util.List;

import co.edu.eafit.coglovi.model.autenticausuario.RestResponse;

public class PlacasResponse {

	private List<PlacaOut> listaPlacas;
	private RestResponse restResponse;

	public List<PlacaOut> getListaPlacas() {
		return listaPlacas;
	}

	public void setListaPlacas(List<PlacaOut> listaPlacas) {
		this.listaPlacas = listaPlacas;
	}

	public RestResponse getRestResponse() {
		if (restResponse == null)
			restResponse = new RestResponse();
		return restResponse;
	}

	public void setRestResponse(RestResponse restResponse) {
		this.restResponse = restResponse;
	}
}
