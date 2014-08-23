package co.edu.eafit.coglovi.gestionapp.model.contenido;

import java.util.ArrayList;
import java.util.List;

import co.edu.eafit.coglovi.gestionapp.model.autenticausuario.RestResponse;

public class ContenidoResponse {

	private RestResponse response;
	private List<PreguntasVO> listaPreguntas;
	private String urlManual;

	public String getUrlManual() {
		return urlManual;
	}

	public void setUrlManual(String urlManual) {
		this.urlManual = urlManual;
	}

	public RestResponse getResponse() {
		if (response == null) {
			response = new RestResponse();
		}
		return response;
	}

	public void setResponse(RestResponse response) {
		this.response = response;
	}

	public List<PreguntasVO> getListaPreguntas() {
		if (listaPreguntas == null) {
			listaPreguntas = new ArrayList<PreguntasVO>();
		}
		return listaPreguntas;
	}

	public void setListaPreguntas(List<PreguntasVO> listaPreguntas) {
		this.listaPreguntas = listaPreguntas;
	}

}
