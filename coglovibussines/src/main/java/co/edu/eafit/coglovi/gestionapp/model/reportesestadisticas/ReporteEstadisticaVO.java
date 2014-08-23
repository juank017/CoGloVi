package co.edu.eafit.coglovi.gestionapp.model.reportesestadisticas;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import co.edu.eafit.coglovi.gestionapp.model.Constantes.FiltroBusqueda;
import co.edu.eafit.coglovi.gestionapp.model.alerta.Alerta;
import co.edu.eafit.coglovi.gestionapp.model.alerta.EnvioAlertaUsuario;
import co.edu.eafit.coglovi.gestionapp.model.alerta.EstadoEnvio;
import co.edu.eafit.coglovi.gestionapp.model.usuariovehiculo.UsuarioAPP;
import co.edu.eafit.coglovi.gestionapp.model.usuariovehiculo.Vehiculo;

public class ReporteEstadisticaVO implements Serializable, Comparable<ReporteEstadisticaVO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7590212407613469418L;
	private int criterioBusqueda;
	private Date fechaInicial;
	private Date fechaFinal;
	private UsuarioAPP usuarioAPP;
	private EnvioAlertaUsuario envioAlertaUsuario;
	private Alerta alerta;
	private EstadoEnvio estadoEnvio;
	private Vehiculo vehiculo;
	private DetalleEncuesta detalleEncuesta;
	private String chartMesAnio;
	private String mesAnio;
	private Integer cantidadTotal;
	private String mes;
	private Date fecha;
	private List<TipoRespuesta> listTipoRespuesta;

	public List<TipoRespuesta> getListTipoRespuesta() {
		return listTipoRespuesta;
	}

	public void setListTipoRespuesta(List<TipoRespuesta> listTipoRespuesta) {
		this.listTipoRespuesta = listTipoRespuesta;
	}

	@Override
	public int compareTo(ReporteEstadisticaVO o) {
		int retorno = 0;

		if (o.getCriterioBusqueda() == FiltroBusqueda.CRITERIO_ESTADISTICAS_ALERTAS_ENVIADAS) {

			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(o.getFecha());
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(this.fecha);

			if (cal1.equals(cal2)) {
				retorno = 0;
			}
			if (cal1.after(cal2)) {
				retorno = -1;
			}
			if (cal1.before(cal2)) {
				retorno = 1;
			}
		}

		if (o.getCriterioBusqueda() == FiltroBusqueda.CRITERIO_RESULTADO_ENCUESTA_EXPERIENCIA_USUARIO) {
			Integer idPregunta = null;
			Integer idPregunta2 = null;
			idPregunta = o.getDetalleEncuesta().getTipoPregunta().getIdPregunta();
			idPregunta2 = this.getDetalleEncuesta().getTipoPregunta().getIdPregunta();

			if (idPregunta == idPregunta2) {
				retorno = 0;
			}
			if (idPregunta > idPregunta2) {
				retorno = -1;
			}
			if (idPregunta < idPregunta2) {
				retorno = 1;
			}
		}
		return retorno;
	}

	public Alerta getAlerta() {
		if (alerta == null) {
			alerta = new Alerta();
		}
		return alerta;
	}

	public Integer getCantidadTotal() {
		return cantidadTotal;
	}

	public String getChartMesAnio() {
		return chartMesAnio;
	}

	public int getCriterioBusqueda() {
		return criterioBusqueda;
	}

	public DetalleEncuesta getDetalleEncuesta() {
		if (detalleEncuesta == null) {
			detalleEncuesta = new DetalleEncuesta();
		}
		return detalleEncuesta;
	}

	public EnvioAlertaUsuario getEnvioAlertaUsuario() {
		if (envioAlertaUsuario == null) {
			envioAlertaUsuario = new EnvioAlertaUsuario();
		}
		return envioAlertaUsuario;
	}

	public EstadoEnvio getEstadoEnvio() {
		if (estadoEnvio == null) {
			estadoEnvio = new EstadoEnvio();
		}
		return estadoEnvio;
	}

	public Date getFecha() {
		return fecha;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public String getMes() {
		return mes;
	}

	public String getMesAnio() {
		return mesAnio;
	}

	public UsuarioAPP getUsuarioAPP() {
		if (usuarioAPP == null) {
			usuarioAPP = new UsuarioAPP();
		}
		return usuarioAPP;
	}

	public Vehiculo getVehiculo() {
		if (vehiculo == null) {
			vehiculo = new Vehiculo();
		}
		return vehiculo;
	}

	public void setAlerta(Alerta alerta) {
		this.alerta = alerta;
	}

	public void setCantidadTotal(Integer cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}

	public void setChartMesAnio(String chartMesAnio) {
		this.chartMesAnio = chartMesAnio;
	}

	public void setCriterioBusqueda(int criterioBusqueda) {
		this.criterioBusqueda = criterioBusqueda;
	}

	public void setDetalleEncuesta(DetalleEncuesta detalleEncuesta) {
		this.detalleEncuesta = detalleEncuesta;
	}

	public void setEnvioAlertaUsuario(EnvioAlertaUsuario envioAlertaUsuario) {
		this.envioAlertaUsuario = envioAlertaUsuario;
	}

	public void setEstadoEnvio(EstadoEnvio estadoEnvio) {
		this.estadoEnvio = estadoEnvio;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public void setMesAnio(String mesAnio) {
		this.mesAnio = mesAnio;
	}

	public void setUsuarioAPP(UsuarioAPP usuarioAPP) {
		this.usuarioAPP = usuarioAPP;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
}
