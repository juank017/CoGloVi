package co.edu.eafit.coglovi.gestionapp.model.contenido;

import java.io.Serializable;

import co.edu.eafit.coglovi.gestionapp.model.reportesestadisticas.TipoPregunta;
import co.edu.eafit.coglovi.gestionapp.model.reportesestadisticas.TipoRespuesta;

public class EncuestaUsuarioVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7517184003545370567L;
	private TipoPregunta tipoPregunta;
	private TipoRespuesta respuesta1;
	private TipoRespuesta respuesta2;
	private TipoRespuesta respuesta3;
	
	public class Respuesta{
		private String respuesta;
		private long idRespuesta;
		public String getRespuesta() {
			return respuesta;
		}
		public void setRespuesta(String respuesta) {
			this.respuesta = respuesta;
		}
		public long getIdRespuesta() {
			return idRespuesta;
		}
		public void setIdRespuesta(long idRespuesta) {
			this.idRespuesta = idRespuesta;
		}
	}

	public TipoPregunta getTipoPregunta() {
		if (tipoPregunta == null) {
			tipoPregunta = new TipoPregunta();
		}
		return tipoPregunta;
	}

	public void setTipoPregunta(TipoPregunta tipoPregunta) {
		this.tipoPregunta = tipoPregunta;
	}

	public TipoRespuesta getRespuesta1() {
		if(respuesta1 == null){
			respuesta1 = new TipoRespuesta();
		}
		return respuesta1;
	}

	public void setRespuesta1(TipoRespuesta respuesta1) {
		this.respuesta1 = respuesta1;
	}

	public TipoRespuesta getRespuesta2() {
		if(respuesta2 == null){
			respuesta2 = new TipoRespuesta();
		}
		return respuesta2;
	}

	public void setRespuesta2(TipoRespuesta respuesta2) {
		this.respuesta2 = respuesta2;
	}

	public TipoRespuesta getRespuesta3() {
		if(respuesta3 == null){
			respuesta3 = new TipoRespuesta();
		}
		return respuesta3;
	}

	public void setRespuesta3(TipoRespuesta respuesta3) {
		this.respuesta3 = respuesta3;
	}
}
