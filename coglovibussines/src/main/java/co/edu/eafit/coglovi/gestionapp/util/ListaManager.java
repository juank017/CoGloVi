package co.edu.eafit.coglovi.gestionapp.util;

import java.util.List;

import co.edu.eafit.coglovi.core.model.util.Lista;
import co.edu.eafit.coglovi.gestionapp.exception.QxException;
/**
 * Interfaz de manager ListaManager utilizado para consulta listas desplegables <br>
 * Creado el 23/05/2012 a las 20:21:34 <br>
 * 
 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
 */

public interface ListaManager {

	/**
	 * Obtiene la descripcion de un elemento de la tabla.
	 * 
	 * @param tabla
	 * @param descColumna
	 * @param campoFiltro
	 * @param valorFiltro
	 * @return
	 * @throws QxException
	 */
	Lista findDescColumna(String tabla, String descColumna, String campoFiltro, String valorFiltro) throws QxException;

	/**
	 * TODO: descripción del método <br>
	 * Creado el 23/05/2012 a las 20:35:53 <br>
	 * 
	 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
	 * @param tabla
	 * @param idColumna
	 * @param descColumn
	 * @return
	 */
	List<Lista> findListaPorTabla(String tabla, String idColumna, String descColumn) throws QxException;

	/**
	 * TODO: descripción del método <br>
	 * Creado el 23/05/2012 a las 21:35:23 <br>
	 * 
	 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
	 * @param tabla
	 * @param idColumna
	 * @param descColumna
	 * @param campoFiltro
	 * @param valorFiltro
	 * @return
	 * @throws QxException
	 */
	List<Lista> findListaPorTabla(String tabla, String idColumna, String descColumna, String campoFiltro, String valorFiltro) throws QxException;

	/**
	 * TODO: descripción del método <br>
	 * Creado el 6/05/2013 a las 18:03:55 <br>
	 * 
	 * @param tabla
	 * @param idColumna
	 * @param descColumna
	 * @param campoFiltro1
	 * @param valorFiltro1
	 * @param campoFiltro2
	 * @param valorFiltro2
	 * @return
	 * @throws QxException
	 */
	List<Lista> findListaPorTabla(String tabla, String idColumna, String descColumna, String campoFiltro1, String valorFiltro1, String campoFiltro2,
			String valorFiltro2) throws QxException;

}
