package co.edu.eafit.coglovi.model.reportesestadisticas;

import java.util.ArrayList;
import java.util.List;

public class TipoPreguntaRespuesta {
	
	private TipoPregunta pregunta;
	private List<TipoRespuesta> respuestas;
	
	public TipoPregunta getPregunta() {
		if(pregunta == null){
			pregunta = new TipoPregunta();
		}
		return pregunta;
	}
	public void setPregunta(TipoPregunta pregunta) {
		this.pregunta = pregunta;
	}
	public List<TipoRespuesta> getRespuestas() {
		if(respuestas == null){
			respuestas = new ArrayList<TipoRespuesta>();
		}
		return respuestas;
	}
	public void setRespuestas(List<TipoRespuesta> respuestas) {
		this.respuestas = respuestas;
	}
	
}
