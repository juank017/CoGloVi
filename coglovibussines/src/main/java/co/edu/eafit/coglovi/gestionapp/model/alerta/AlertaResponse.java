package co.edu.eafit.coglovi.gestionapp.model.alerta;

import co.edu.eafit.coglovi.gestionapp.model.autenticausuario.RestResponse;

public class AlertaResponse {

	private AlertaVO alertaVO;
	private RestResponse restResponse;

	public RestResponse getRestResponse() {
		if (restResponse == null) {
			restResponse = new RestResponse();
		}
		return restResponse;
	}

	public void setRestResponse(RestResponse restResponse) {
		this.restResponse = restResponse;
	}

	public AlertaVO getAlertaVO() {
		if (alertaVO == null) {
			alertaVO = new AlertaVO();
		}
		return alertaVO;
	}

	public void setAlertaVO(AlertaVO alertaVO) {
		this.alertaVO = alertaVO;
	}

}
