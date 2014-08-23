package co.edu.eafit.coglovi.gestionapp.manager.administrarplacas;

import java.util.List;

import co.edu.eafit.coglovi.gestionapp.exception.QxException;
import co.edu.eafit.coglovi.gestionapp.model.administrarplacas.AdministraPlacaUsuarioVO;

public interface AdministraPlacaUsuarioManager {
	
	/**
	 * Valida las propiedades del objeto y genera el llamado a la capa de persistencia de datos
	 * Para la respectiva consulta de placas o usuarios registrados
	 * @param administraPlacaUsuarioVO
	 * @return
	 * @throws QxException
	 */
	List<AdministraPlacaUsuarioVO> findByFiltros(AdministraPlacaUsuarioVO administraPlacaUsuarioVO) throws QxException;
}
