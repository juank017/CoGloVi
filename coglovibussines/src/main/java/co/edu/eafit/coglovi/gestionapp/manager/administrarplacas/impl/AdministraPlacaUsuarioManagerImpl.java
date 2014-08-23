package co.edu.eafit.coglovi.gestionapp.manager.administrarplacas.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.eafit.coglovi.gestionapp.dao.administrarplacas.AdministraPlacaUsuarioDao;
import co.edu.eafit.coglovi.gestionapp.exception.QxException;
import co.edu.eafit.coglovi.gestionapp.manager.administrarplacas.AdministraPlacaUsuarioManager;
import co.edu.eafit.coglovi.gestionapp.model.Constantes.FiltroBusqueda;
import co.edu.eafit.coglovi.gestionapp.model.administrarplacas.AdministraPlacaUsuarioVO;

@Service
public class AdministraPlacaUsuarioManagerImpl implements AdministraPlacaUsuarioManager {

	@Autowired
	private AdministraPlacaUsuarioDao placaUsuarioDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.quipux.gestionapp.manager.administrarplacas.AdministraPlacaUsuarioManager#findByFiltros(co.com.quipux.gestionapp.model.administrarplacas
	 * .AdministraPlacaUsuarioVO)
	 */
	@Override
	public List<AdministraPlacaUsuarioVO> findByFiltros(AdministraPlacaUsuarioVO administraPlacaUsuarioVO) throws QxException {
		List<AdministraPlacaUsuarioVO> listPlacaUsuario = null;
		try {
			switch (administraPlacaUsuarioVO.getCriterioBusqueda()) {
			case FiltroBusqueda.CRITERIO_PLACA:
				listPlacaUsuario = placaUsuarioDao.findByPlaca(administraPlacaUsuarioVO);
				break;
			case FiltroBusqueda.CRITERIO_IDENTIFICACION:
				listPlacaUsuario = placaUsuarioDao.findByIdentificacion(administraPlacaUsuarioVO);
				break;
			}
		} catch (Exception e) {
			throw new QxException(e.getMessage(), e);
		}
		return listPlacaUsuario;
	}
}
