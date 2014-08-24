package co.edu.eafit.coglovi.manager.seguridad.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import co.edu.eafit.coglovi.dao.seguridad.SeguridadCasDao;
import co.edu.eafit.coglovi.manager.seguridad.SeguridadManager;
import co.edu.eafit.coglovi.model.security.UsuarioSistema;

/**
 * Manager para el acceso a datos de usuariosistema
 *
 */
@Service
public class SeguridadManagerImpl implements SeguridadManager{
	
		@Autowired
		private SeguridadCasDao seguridadCasDao;
		
		@Override
		public UsuarioSistema findQuipuxUsuarioCAS(String login) throws Exception {
			try {
				return seguridadCasDao.findQuipuxUsuarioCAS(login);
			} catch (EmptyResultDataAccessException e) {
				return null;
			}
		}
		
		@Override
		public UsuarioSistema findQuipuxUsuarioCAS(String login, String clave) throws Exception {
			UsuarioSistema usuarioSistema = null;
			try {
				usuarioSistema = seguridadCasDao.findQuipuxUsuarioCAS(login,clave);
				if(usuarioSistema != null){
					//se consultan los recursos
					usuarioSistema.setRecursos(seguridadCasDao.findGrantedAuthority(login));
				}
			} catch (EmptyResultDataAccessException e) {
				return null;
			}
			return usuarioSistema;
		}
		
		@Override
		public List<String> findGrantedAuthority(String login) throws Exception {
			return seguridadCasDao.findGrantedAuthority(login);
		}
		
}

