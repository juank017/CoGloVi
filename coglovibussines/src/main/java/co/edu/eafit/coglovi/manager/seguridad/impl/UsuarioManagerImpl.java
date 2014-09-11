package co.edu.eafit.coglovi.manager.seguridad.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.eafit.coglovi.dao.usuarioapp.UsuarioAPPDao;
import co.edu.eafit.coglovi.manager.seguridad.UsuarioManager;
import co.edu.eafit.coglovi.model.usuario.GrupoInteres;
import co.edu.eafit.coglovi.model.usuario.UsuarioAPP;

/**
 * Manager para el acceso a datos de usuariosistema
 *
 */
@Service
public class UsuarioManagerImpl implements UsuarioManager{
	
		@Autowired
		private UsuarioAPPDao usuarioAppDao;
		
		@Override
		public boolean registroUsuario(UsuarioAPP usuarioAPP,List<GrupoInteres> listInteres) throws Exception {
				UsuarioAPP usuarioAppResponse=usuarioAppDao.findUsuario(usuarioAPP);
				if(usuarioAppResponse==null){
					usuarioAppDao.insertUsuario(usuarioAPP);
					for (GrupoInteres gruposInteres : listInteres) {
						
					}
				}
				return true;
		}

		
		
		
}

