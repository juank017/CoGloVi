package co.edu.eafit.coglovi.gestionapp.model.alerta;

public class UserAlert {

	private Long idUsuario;
	private Long idAlerta;
	private int idDirectorio;
	
	public int getIdDirectorio() {
		return idDirectorio;
	}
	public void setIdDirectorio(int idDirectorio) {
		this.idDirectorio = idDirectorio;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Long getIdAlerta() {
		return idAlerta;
	}
	public void setIdAlerta(Long idAlerta) {
		this.idAlerta = idAlerta;
	}
	
}
