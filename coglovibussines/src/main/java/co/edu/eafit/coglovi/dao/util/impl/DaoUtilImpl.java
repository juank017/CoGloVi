package co.edu.eafit.coglovi.dao.util.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import co.edu.eafit.coglovi.dao.util.DaoTemplate;
import co.edu.eafit.coglovi.dao.util.DaoUtil;
import co.edu.eafit.coglovi.transversal.PropertiesManager;

/**
 * TODO: descripci�n <br>
 * Creado el 17/06/2014 a las 17:17:09 <br>
 * 
 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
 */
@Repository
public class DaoUtilImpl extends DaoTemplate implements DaoUtil {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.quipux.gestionapp.dao.util.DaoUtil#closeResources(java.sql.Connection
	 * )
	 */
	@Override
	public void closeResources(Connection connection) {
		try {
			if (connection != null)
				connection.close();
		} catch (Exception e) {
			// logger.error(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.util.DaoUtil#closeResources(java.sql.
	 * PreparedStatement)
	 */
	@Override
	public void closeResources(PreparedStatement preparedStatement) {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (Exception e) {
				// logger.error(e.getMessage(), e);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.util.DaoUtil#closeResources(java.sql.
	 * PreparedStatement, java.sql.ResultSet)
	 */
	@Override
	public void closeResources(PreparedStatement preparedStatement,
			ResultSet resultado) {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (Exception e) {
				// logger.error(e.getMessage(), e);
			}
		}
		if (resultado != null) {
			try {
				resultado.close();
			} catch (Exception e) {
				// logger.error(e.getMessage(), e);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.util.DaoUtil#findValorParametro(int)
	 */
	@Override
	public String findValorParametro(int idParametro) {
		String sql = PropertiesManager.obtenerCadena("coreSQL/coreSQL",
				"util.findValorParametro");
		return jdbcTemplate.queryForObject(sql, new Object[] { idParametro },
				String.class);
	}

	@Override
	public Integer getInteger(ResultSet rs, String strColName)
			throws SQLException {
		int nValue = rs.getInt(strColName);
		if (rs.wasNull())
			return null;
		return new Integer(nValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.quipux.gestionapp.dao.util.DaoUtil#getSecuenciaInteger(java.lang
	 * .String)
	 */
	@Override
	public int getSecuenciaInt(String sequenceName) {
		String sql = PropertiesManager.obtenerCadena("cogloviSQL/cogloviSQL",
				"core.lastinsertid");
		return jdbcTemplate.queryForInt(sql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.quipux.gestionapp.dao.util.DaoUtil#getSecuenciaLong(java.lang.
	 * String)
	 */
	@Override
	public long getSecuenciaLong() {
		String sql = PropertiesManager.obtenerCadena("cogloviSQL/cogloviSQL","core.lastinsertid");
		return jdbcTemplate.queryForLong(sql);
	}
}
