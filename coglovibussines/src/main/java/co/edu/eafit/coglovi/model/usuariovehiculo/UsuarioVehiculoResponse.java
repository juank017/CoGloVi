package co.edu.eafit.coglovi.model.usuariovehiculo;

import co.edu.eafit.coglovi.model.autenticausuario.RestResponse;

public class UsuarioVehiculoResponse {

	private UsuarioAPP usuarioAPP;
	private RestResponse restResponse;

	public RestResponse getRestResponse() {
		if (restResponse == null) {
			restResponse = new RestResponse();
		}
		return restResponse;
	}

	public UsuarioAPP getUsuarioAPP() {
		if(usuarioAPP == null){
			usuarioAPP = new UsuarioAPP();
		}
		return usuarioAPP;
	}

	public void setRestResponse(RestResponse restResponse) {
		this.restResponse = restResponse;
	}

	public void setUsuarioAPP(UsuarioAPP usuarioAPP) {
		this.usuarioAPP = usuarioAPP;
	}
}
