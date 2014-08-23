package co.edu.eafit.coglovi.gestionapp.model.alerta;

public class AlertaVO {
	private Long idAlerta;
	private String numero;
	private String placa;
	private String descripcion;
	private String fecha;
	private String hora;
	private String direccion;
	private Integer idDirectorio;
	
	public Integer getIdDirectorio() {
		return idDirectorio;
	}
	public void setIdDirectorio(Integer idDirectorio) {
		this.idDirectorio = idDirectorio;
	}
	public Long getIdAlerta() {
		return idAlerta;
	}
	public void setIdAlerta(Long idAlerta) {
		this.idAlerta = idAlerta;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
}
