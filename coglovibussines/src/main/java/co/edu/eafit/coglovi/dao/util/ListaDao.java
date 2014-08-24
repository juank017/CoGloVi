package co.edu.eafit.coglovi.dao.util;

import java.sql.Connection;
import java.util.List;

import co.edu.eafit.coglovi.core.model.util.Lista;

/**
 * TODO: descripción <br>
 * Creado el 23/05/2012 a las 21:06:01 <br>
 * 
 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
 */
public interface ListaDao {

	/**
	 * Obtiene la descripcion de un elemento de la tabla.
	 * 
	 * @param tabla
	 * @param descColumna
	 * @param campoFiltro
	 * @param valorFiltro
	 * @param c
	 * @return
	 * @throws Exception
	 */
	Lista findDescColumna(String tabla, String descColumna, String campoFiltro, String valorFiltro, Connection c) throws Exception;

	/**
	 * Obtiene y almacena en caché las listas desplegables que se vayan necesitando <br>
	 * Creado el 23/05/2012 a las 21:06:16 <br>
	 * 
	 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
	 * @param tabla
	 * @param idColumna
	 * @param descColumna
	 * @param c
	 * @return
	 * @throws Exception
	 */
	List<Lista> findListaPorTabla(String tabla, String idColumna, String descColumna, Connection c) throws Exception;

	/**
	 * Obtiene y almacena en caché las listas desplegables que se vayan necesitando por un filtro determinado<br>
	 * Creado el 23/05/2012 a las 21:08:19 <br>
	 * 
	 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
	 * @param tabla
	 * @param idColumna
	 * @param descColumna
	 * @param campoFiltro
	 * @param valorFiltro
	 * @param c
	 * @return
	 * @throws Exception
	 */
	List<Lista> findListaPorTabla(String tabla, String idColumna, String descColumna, String campoFiltro, String valorFiltro, Connection c)
			throws Exception;

	/**
	 * Obtiene datos para listas desplegables, filtrado por dos criterios<br>
	 * Creado el 6/05/2013 a las 14:32:42 <br>
	 * 
	 * @param tabla
	 * @param idColumna
	 * @param descColumna
	 * @param campoFiltro1
	 * @param valorFiltro1
	 * @param campoFiltro2
	 * @param valorFiltro2
	 * @param c
	 * @return
	 * @throws Exception
	 */
	List<Lista> findListaPorTabla(String tabla, String idColumna, String descColumna, String campoFiltro1, String valorFiltro1, String campoFiltro2,
			String valorFiltro2, Connection c) throws Exception;

}
