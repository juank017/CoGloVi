package co.edu.eafit.coglovi.gestionapp.model.reportesestadisticas;

import java.util.ArrayList;
import java.util.List;

import co.edu.eafit.coglovi.gestionapp.model.autenticausuario.RestResponse;

public class PreguntasExperiencia {

	private RestResponse response;
	private List<TipoPreguntaRespuesta> listaPreguntas;
	
	public List<TipoPreguntaRespuesta> getListaPreguntas() {
		if(listaPreguntas == null){
			listaPreguntas = new ArrayList<TipoPreguntaRespuesta>();
		}
		return listaPreguntas;
	}
	public void setListaPreguntas(List<TipoPreguntaRespuesta> listaPreguntas) {
		this.listaPreguntas = listaPreguntas;
	}
	public RestResponse getResponse() {
		if(response == null){
			response = new RestResponse();
		}
		return response;
	}
	public void setResponse(RestResponse response) {
		this.response = response;
	}
	
	
	
}
