package co.edu.eafit.coglovi.gestionapp.model.reportesestadisticas;

import java.io.Serializable;

public class DetalleEncuesta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1320693656597593012L;
	private Long idDetalleEncuesta;
	private EncuestaUsuario encuestaUsuario;
	private TipoPregunta tipoPregunta;
	private TipoRespuesta tipoRespuesta;
	public Long getIdDetalleEncuesta() {
		return idDetalleEncuesta;
	}
	public void setIdDetalleEncuesta(Long idDetalleEncuesta) {
		this.idDetalleEncuesta = idDetalleEncuesta;
	}
	public EncuestaUsuario getEncuestaUsuario() {
		if(encuestaUsuario == null){
			encuestaUsuario = new EncuestaUsuario();
		}
		return encuestaUsuario;
	}
	public void setEncuestaUsuario(EncuestaUsuario encuestaUsuario) {
		this.encuestaUsuario = encuestaUsuario;
	}
	public TipoPregunta getTipoPregunta() {
		if(tipoPregunta == null){
			tipoPregunta = new TipoPregunta();
		}
		return tipoPregunta;
	}
	public void setTipoPregunta(TipoPregunta tipoPregunta) {
		this.tipoPregunta = tipoPregunta;
	}
	public TipoRespuesta getTipoRespuesta() {
		if(tipoRespuesta ==  null){
			tipoRespuesta = new TipoRespuesta();
		}
		return tipoRespuesta;
	}
	public void setTipoRespuesta(TipoRespuesta tipoRespuesta) {
		this.tipoRespuesta = tipoRespuesta;
	}
}
