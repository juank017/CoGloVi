package co.edu.eafit.coglovi.model.usuariovehiculo;

public class RegistroUsuarioVehiculo {
	
	private Vehiculo vehiculo;
	private UsuarioVehiculo usuarioVehiculo;

	public Vehiculo getVehiculo() {
		if(vehiculo == null){
			vehiculo = new Vehiculo();
		}
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	public UsuarioVehiculo getUsuarioVehiculo() {
		if(usuarioVehiculo == null){
			usuarioVehiculo = new UsuarioVehiculo();
		}
		return usuarioVehiculo;
	}
	public void setUsuarioVehiculo(UsuarioVehiculo usuarioVehiculo) {
		this.usuarioVehiculo = usuarioVehiculo;
	}
	
	
}
