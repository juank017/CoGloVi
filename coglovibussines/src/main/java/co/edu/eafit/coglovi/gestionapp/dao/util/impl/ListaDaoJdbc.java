package co.edu.eafit.coglovi.gestionapp.dao.util.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.eafit.coglovi.core.model.util.Lista;
import co.edu.eafit.coglovi.gestionapp.dao.util.DaoUtil;
import co.edu.eafit.coglovi.gestionapp.dao.util.ListaDao;
import co.edu.eafit.coglovi.gestionapp.transversal.PropertiesManager;


/**
 * TODO: descripción <br>
 * Creado el 23/05/2012 a las 20:48:48 <br>
 * 
 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
 */
@Repository
public class ListaDaoJdbc implements ListaDao {

	@Autowired
	private DaoUtil daoUtil;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.qits.core.dao.util.ListaDao#findDescColumna(java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.sql.Connection)
	 */
	@Override
	public Lista findDescColumna(String tabla, String descColumna, String campoFiltro, String valorFiltro, Connection c) throws Exception {
		Lista item = null;
		String sql;
		ResultSet resultado = null;
		PreparedStatement instruccion = null;
		try {
			sql = PropertiesManager.obtenerCadena("coreSQL/coreSQL", "DescColumnaPorTabla.selectFiltrada");
			sql = sql.replace("<DESCCOLUMNA>", descColumna);
			sql = sql.replace("<TABLA>", tabla);
			sql = sql.replace("<CAMPOFILTRO>", campoFiltro);
			instruccion = c.prepareStatement(sql);
			instruccion.setString(1, valorFiltro);
			resultado = instruccion.executeQuery();
			while (resultado.next()) {
				item = new Lista();
				item.setDescColumna(resultado.getString(descColumna));
			}
		} finally {
			// Garantizo que se cierren ambos objetos
			daoUtil.closeResources(instruccion, resultado);
		}
		return item;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.qits.core.dao.util.ListaDao#findListaPorTabla(java.lang.String, java.lang.String, java.lang.String, java.sql.Connection)
	 */
	@Override
	public List<Lista> findListaPorTabla(String tabla, String idColumna, String descColumna, Connection c) throws Exception {
		List<Lista> listSelect = new ArrayList<Lista>();
		Lista item = null;
		String sql;
		ResultSet resultado = null;
		PreparedStatement instruccion = null;
		try {
			sql = PropertiesManager.obtenerCadena("coreSQL/coreSQL", "listaPorTabla.select");
			sql = sql.replace("<IDCOLUMNA>", idColumna);
			sql = sql.replace("<DESCCOLUMNA>", descColumna);
			sql = sql.replace("<TABLA>", tabla);
			instruccion = c.prepareStatement(sql);
			resultado = instruccion.executeQuery();
			while (resultado.next()) {
				item = new Lista();
				item.setIdColumna(resultado.getInt(idColumna));
				item.setDescColumna(resultado.getString(descColumna));
				listSelect.add(item);
			}
		} finally {
			// Garantizo que se cierren ambos objetos
			daoUtil.closeResources(instruccion, resultado);
		}
		return listSelect;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.qits.core.dao.util.ListaDao#findListaPorTablaFiltrada(java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.Object, java.sql.Connection)
	 */
	@Override
	public List<Lista> findListaPorTabla(String tabla, String idColumna, String descColumna, String campoFiltro, String valorFiltro, Connection c)
			throws Exception {
		List<Lista> listSelect = new ArrayList<Lista>();
		Lista item = null;
		String sql;
		ResultSet resultado = null;
		PreparedStatement instruccion = null;
		try {
			sql = PropertiesManager.obtenerCadena("coreSQL/coreSQL", "listaPorTabla.selectFiltrada");
			sql = sql.replace("<IDCOLUMNA>", idColumna);
			sql = sql.replace("<DESCCOLUMNA>", descColumna);
			sql = sql.replace("<TABLA>", tabla);
			sql = sql.replace("<CAMPOFILTRO>", campoFiltro);
			instruccion = c.prepareStatement(sql);
			instruccion.setString(1, valorFiltro);
			resultado = instruccion.executeQuery();
			while (resultado.next()) {
				item = new Lista();
				item.setIdColumna(resultado.getInt(idColumna));
				item.setDescColumna(resultado.getString(descColumna));
				listSelect.add(item);
			}
		} finally {
			// Garantizo que se cierren ambos objetos
			daoUtil.closeResources(instruccion, resultado);
		}
		return listSelect;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.qits.core.dao.util.ListaDao#findListaPorTablaFiltradaDosCampos(java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.sql.Connection)
	 */
	@Override
	public List<Lista> findListaPorTabla(String tabla, String idColumna, String descColumna, String campoFiltro1, String valorFiltro1,
			String campoFiltro2, String valorFiltro2, Connection c) throws Exception {
		List<Lista> listSelect = new ArrayList<Lista>();
		Lista item = null;
		String sql;
		ResultSet resultado = null;
		PreparedStatement instruccion = null;
		try {
			sql = PropertiesManager.obtenerCadena("coreSQL/coreSQL", "listaPorTabla.selectFiltradaDosCampos");
			sql = sql.replace("<IDCOLUMNA>", idColumna);
			sql = sql.replace("<DESCCOLUMNA>", descColumna);
			sql = sql.replace("<TABLA>", tabla);
			sql = sql.replace("<CAMPOFILTRO1>", campoFiltro1);
			sql = sql.replace("<CAMPOFILTRO2>", campoFiltro2);

			instruccion = c.prepareStatement(sql);
			instruccion.setString(1, valorFiltro1);
			instruccion.setString(2, valorFiltro2);
			resultado = instruccion.executeQuery();
			while (resultado.next()) {
				item = new Lista();
				item.setIdColumna(resultado.getInt(idColumna));
				item.setDescColumna(resultado.getString(descColumna));
				listSelect.add(item);
			}
		} finally {
			// Garantizo que se cierren ambos objetos
			daoUtil.closeResources(instruccion, resultado);
		}
		return listSelect;
	}

}
