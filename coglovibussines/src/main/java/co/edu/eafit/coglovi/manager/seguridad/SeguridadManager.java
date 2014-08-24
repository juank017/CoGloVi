package co.edu.eafit.coglovi.manager.seguridad;

import java.util.List;

import co.edu.eafit.coglovi.model.security.UsuarioSistema;

public interface SeguridadManager {

	UsuarioSistema findQuipuxUsuarioCAS(String login, String clave)
			throws Exception;
	
	UsuarioSistema findQuipuxUsuarioCAS(String login)
			throws Exception;

	List<String> findGrantedAuthority(String login) throws Exception;

}
