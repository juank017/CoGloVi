package co.edu.eafit.coglovi.gestionapp.model.service;

import java.util.ArrayList;
import java.util.List;

public class RegistroAlertaOutDTO {
	private EstadoService estadoService;
	private List<ResultadoOutDTO> listaError;
	
	public EstadoService getEstadoService() {
		if(estadoService == null){
			estadoService = new EstadoService();
		}
		return estadoService;
	}
	public void setEstadoService(EstadoService estadoService) {
		this.estadoService = estadoService;
	}
	public List<ResultadoOutDTO> getListaError() {
		if(listaError == null){
			listaError = new ArrayList<ResultadoOutDTO>();
		}
		return listaError;
	}
	public void setListaError(List<ResultadoOutDTO> listaError) {
		this.listaError = listaError;
	}

}
