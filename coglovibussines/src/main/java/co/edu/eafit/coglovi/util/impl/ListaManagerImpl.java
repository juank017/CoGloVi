package co.edu.eafit.coglovi.util.impl;

import java.sql.Connection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.annotation.Resource.AuthenticationType;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.eafit.coglovi.core.model.util.Lista;
import co.edu.eafit.coglovi.dao.util.DaoUtil;
import co.edu.eafit.coglovi.dao.util.ListaDao;
import co.edu.eafit.coglovi.dao.util.impl.ListaDaoJdbc;
import co.edu.eafit.coglovi.exception.QxException;
import co.edu.eafit.coglovi.util.ListaManager;

/**
 * Clase que consulta las listas de la base de datos <br>
 * Creado el 23/05/2012 a las 20:19:50 <br>
 * 
 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
 */
@Service
public class ListaManagerImpl implements ListaManager {
	
	@Autowired
	private ListaDao listaDao;
	@Autowired
	DaoUtil daoUtil;

	@Resource(mappedName = "jdbc/Admin_DataSource", name = "jdbc/Admin_DataSource", authenticationType = AuthenticationType.CONTAINER, type = DataSource.class, shareable = true)
	public DataSource dataSource;


	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.qits.core.service.util.ListaManager#findDescColumna(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Lista findDescColumna(String tabla, String descColumna, String campoFiltro, String valorFiltro) throws QxException {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			return listaDao.findDescColumna(tabla, descColumna, campoFiltro, valorFiltro, connection);
		} catch (Exception e) {
			throw new QxException(e.getMessage(), e);
		} finally {
			daoUtil.closeResources(connection);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.qits.core.service.util.ListaManager#findListaPorTabla(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Lista> findListaPorTabla(String tabla, String idColumna, String descColumn) throws QxException {
		Connection connection = null;
		List<Lista> listSelect = null;
		try {
			connection = dataSource.getConnection();
			listSelect = listaDao.findListaPorTabla(tabla, idColumna, descColumn, connection);
		} catch (Exception e) {
			throw new QxException(e.getMessage(), e);
		} finally {
			daoUtil.closeResources(connection);
		}
		return listSelect;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Lista> findListaPorTabla(String tabla, String idColumna, String descColumna, String campoFiltro, String valorFiltro)
			throws QxException {
		Connection connection = null;
		List<Lista> listSelect = null;
		try {
			connection = dataSource.getConnection();
			listSelect = listaDao.findListaPorTabla(tabla, idColumna, descColumna, campoFiltro, valorFiltro, connection);
		} catch (Exception e) {
			throw new QxException(e.getMessage(), e);
		} finally {
			daoUtil.closeResources(connection);
		}
		return listSelect;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.qits.core.service.util.ListaManager#findListaPorTablaFiltradaDosCampos(java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Lista> findListaPorTabla(String tabla, String idColumna, String descColumna, String campoFiltro1, String valorFiltro1,
			String campoFiltro2, String valorFiltro2) throws QxException {
		Connection connection = null;
		List<Lista> listSelect = null;
		try {
			connection = dataSource.getConnection();
			listSelect = listaDao
					.findListaPorTabla(tabla, idColumna, descColumna, campoFiltro1, valorFiltro1, campoFiltro2, valorFiltro2, connection);
		} catch (Exception e) {
			throw new QxException(e.getMessage(), e);
		} finally {
			daoUtil.closeResources(connection);
		}
		return listSelect;
	}
}
