package co.edu.eafit.coglovi.gestionapp.model.alerta;

import java.util.ArrayList;
import java.util.List;

import co.edu.eafit.coglovi.gestionapp.model.autenticausuario.RestResponse;

public class AlertaListResponse {

	private List<AlertaVO> listaAlertas;
	private RestResponse restResponse;

	public List<AlertaVO> getListaAlertas() {
		if (listaAlertas == null) {
			listaAlertas = new ArrayList<AlertaVO>();
		}
		return listaAlertas;
	}

	public void setListaAlertas(List<AlertaVO> listaAlertas) {
		this.listaAlertas = listaAlertas;
	}

	public RestResponse getRestResponse() {
		if(restResponse == null){
			restResponse = new RestResponse();
		}
		return restResponse;
	}

	public void setRestResponse(RestResponse restResponse) {
		this.restResponse = restResponse;
	}

	

}
