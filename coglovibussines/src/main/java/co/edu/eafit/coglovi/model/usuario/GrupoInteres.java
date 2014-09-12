package co.edu.eafit.coglovi.model.usuario;


public class GrupoInteres {
	private UsuarioAPP usuarioApp;
	private String gruposInteres;

	public UsuarioAPP getUsuarioApp() {
		if(usuarioApp==null){
			usuarioApp=new UsuarioAPP();
		}
		return usuarioApp;
	}

	public void setUsuarioApp(UsuarioAPP usuarioApp) {
		this.usuarioApp = usuarioApp;
	}

	public String getGruposInteres() {
		return gruposInteres;
	}

	public void setGruposInteres(String gruposInteres) {
		this.gruposInteres = gruposInteres;
	}
}
