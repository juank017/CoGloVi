package co.edu.eafit.coglovi.model.alerta;

import java.io.Serializable;
import java.util.Date;

import co.edu.eafit.coglovi.model.usuariovehiculo.TipoDocumentoIdentidad;

public class Alerta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6307736136242750664L;
	private Long idAlerta;
	private TipoCanal tipoCanal;
	private String identificadorAlerta;
	private String contenido;
	private String canal;
	private TipoDocumentoIdentidad tipoDocumentoIdentidad;
	private String numeroDocumento;
	private String mensaje;
	private Date fechaRegistro;

	public Long getIdAlerta() {
		return idAlerta;
	}

	public void setIdAlerta(Long idAlerta) {
		this.idAlerta = idAlerta;
	}

	public TipoCanal getTipoCanal() {
		if (tipoCanal == null) {
			return new TipoCanal();
		}
		return tipoCanal;
	}

	public void setTipoCanal(TipoCanal tipoCanal) {
		this.tipoCanal = tipoCanal;
	}

	public String getIdentificadorAlerta() {
		return identificadorAlerta;
	}

	public void setIdentificadorAlerta(String identificadorAlerta) {
		this.identificadorAlerta = identificadorAlerta;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public TipoDocumentoIdentidad getTipoDocumentoIdentidad() {
		if (tipoDocumentoIdentidad == null) {
			tipoDocumentoIdentidad = new TipoDocumentoIdentidad();
		}
		return tipoDocumentoIdentidad;
	}

	public void setTipoDocumentoIdentidad(TipoDocumentoIdentidad tipoDocumentoIdentidad) {
		this.tipoDocumentoIdentidad = tipoDocumentoIdentidad;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	/**
	 * Se sobre escribe para identificaar una alerta por medio de su idAlerta
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Alerta)) {
			return false;
		}
		final Alerta point = (Alerta) obj;
		return this.idAlerta == point.idAlerta;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

}
