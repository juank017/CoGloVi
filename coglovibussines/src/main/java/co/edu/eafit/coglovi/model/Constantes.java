package co.edu.eafit.coglovi.model;

public class Constantes {
	public static class Activo {
		public static final String SI = "S";
		public static final String NO = "N";
	}
	public static class CodigoEstadoComucacion{
		public static final int ERROR_DATOS=1;
		public static final int ERROR_TECNICO=2;
	}
	
	public static class RegistroUsuarios{
		public static final int REGISTRO_EXITOSO=100;
		public static final int USUARIO_EXISTE = 102;
	}
	public static class RegistroPregunta{
		public static final int REGISTRO_EXITOSO=100;
		public static final int REGISTRO_ERROR=102;
	}
}
