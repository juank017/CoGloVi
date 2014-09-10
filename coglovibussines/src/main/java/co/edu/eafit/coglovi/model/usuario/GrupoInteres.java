package co.edu.eafit.coglovi.model.usuario;

import java.util.List;

public class GrupoInteres {
	private UsuarioAPP usuarioApp;
	private List<String> gruposInteres;

	public List<String> getGruposInteres() {
		return gruposInteres;
	}

	public void setGruposInteres(List<String> gruposInteres) {
		this.gruposInteres = gruposInteres;
	}

	public UsuarioAPP getUsuarioApp() {
		return usuarioApp;
	}

	public void setUsuarioApp(UsuarioAPP usuarioApp) {
		this.usuarioApp = usuarioApp;
	}
}
