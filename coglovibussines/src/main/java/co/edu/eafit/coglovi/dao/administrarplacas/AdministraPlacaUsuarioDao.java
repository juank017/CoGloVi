package co.edu.eafit.coglovi.dao.administrarplacas;

import java.util.List;

import co.edu.eafit.coglovi.exception.QxException;
import co.edu.eafit.coglovi.model.administrarplacas.AdministraPlacaUsuarioVO;

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
