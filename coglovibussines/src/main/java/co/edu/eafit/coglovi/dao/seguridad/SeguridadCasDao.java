package co.edu.eafit.coglovi.dao.seguridad;

import java.util.List;

import co.edu.eafit.coglovi.model.security.UsuarioSistema;

public interface SeguridadCasDao {
	
	/**
	 * Obtiene el usuario para la autenticacion<br>
	 * Creado el 19/06/2014 a las  13:19:45 <br>
	 * @param login
	 * @param clave
	 * @return
	 * @throws Exception
	 */
	UsuarioSistema findQuipuxUsuarioCAS(String login,String clave) throws Exception;

	/**
	 * Obtiene el usuario para la autenticacion <br>
	 * Creado el 19/06/2014 a las  13:19:55 <br>
	 * @param login
	 * @return
	 * @throws Exception
	 */
	UsuarioSistema findQuipuxUsuarioCAS(String login) throws Exception;
 
	/**
	 *  Obtiene los recursos asignados<br>
	 * Creado el 19/06/2014 a las  13:20:10 <br>
	 * @param login
	 * @return
	 * @throws Exception
	 */
	List<String> findGrantedAuthority(String login) throws Exception;

}
