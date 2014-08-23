package co.edu.eafit.coglovi.gestionapp.quartz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import co.edu.eafit.coglovi.gestionapp.manager.alertas.AdministrarAlertasManager;

public class ExecuteQuartz {

	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private AdministrarAlertasManager administrarAlertasManager;
	
	/**
	 * Se ejecuta automáticamente según la configuración del archivo Spring-Quartz.xml
	 */
	public void run() {
		try {
			administrarAlertasManager.enviarAlertasParse();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
