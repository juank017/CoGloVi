package co.edu.eafit.coglovi.dao.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DaoUtil {

	/**
	 * Metodo que se encarga de liberar los recursos asociados a una operacion
	 * en base de datos
	 * 
	 * @param connection
	 */
	void closeResources(Connection connection);

	/**
	 * Metodo que se encarga de liberar los recursos asociados a una operacion
	 * en base de datos
	 * 
	 * @param preparedStatement
	 */
	void closeResources(PreparedStatement preparedStatement);

	/**
	 * Metodo que se encarga de liberar los recursos asociados a una operacion
	 * en base de datos
	 * 
	 * @param preparedStatement
	 * @param resultado
	 */
	void closeResources(PreparedStatement preparedStatement, ResultSet resultado);

	/**
	 * TODO: descripci�n del m�todo <br>
	 * Creado el 20/06/2014 a las 16:13:40 <br>
	 * 
	 * @param idParametro
	 * @return
	 */
	String findValorParametro(int idParametro);

	/**
	 * Evita poner 0 en objeto que se encuentra null en la BD<br>
	 * Creado el 11/07/2014 a las 8:54:07 <br>
	 * 
	 * @param rs
	 * @param strColName
	 * @return
	 * @throws SQLException
	 */
	Integer getInteger(ResultSet rs, String strColName) throws SQLException;

	/**
	 * Obtiene el siguiente elemento de la secuencia
	 * 
	 * @param sequenceName
	 * @return
	 */
	int getSecuenciaInt(String sequenceName);

	/**
	 * Obtiene el siguiente elemento de la secuencia<br>
	 * Creado el 17/06/2014 a las 17:16:34 <br>
	 * 
	 * @param sequenceName
	 * @return long
	 */
	long getSecuenciaLong();

}
