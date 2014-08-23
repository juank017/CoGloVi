package co.edu.eafit.coglovi.gestionapp.dao.administrarplacas;

import java.util.List;

import co.edu.eafit.coglovi.gestionapp.exception.QxException;
import co.edu.eafit.coglovi.gestionapp.model.administrarplacas.AdministraPlacaUsuarioVO;

public interface AdministraPlacaUsuarioDao {

	/**
	 * Consulta usuarios registrados por tipo y nro identificacion
	 * 
	 * @param administraPlacaUsuarioVO
	 * @return
	 */
	List<AdministraPlacaUsuarioVO> findByIdentificacion(AdministraPlacaUsuarioVO administraPlacaUsuarioVO) throws QxException;

	/**
	 * Consulta por placas registradas.
	 * 
	 * @param administraPlacaUsuarioVO
	 * @return
	 */
	List<AdministraPlacaUsuarioVO> findByPlaca(AdministraPlacaUsuarioVO administraPlacaUsuarioVO) throws QxException;

}
