package co.edu.eafit.coglovi.gestionapp.model.service;

import java.util.List;

import co.edu.eafit.coglovi.gestionapp.model.alerta.Alerta;

public class RegistroAlertaInDTO {

	private List<Alerta> listaAlertas;

	public List<Alerta> getListaAlertas() {
		return listaAlertas;
	}

	public void setListaAlertas(List<Alerta> listaAlertas) {
		this.listaAlertas = listaAlertas;
	}
}
