package co.edu.eafit.coglovi.dao.usuarioapp;

import co.edu.eafit.coglovi.model.usuario.UsuarioAPP;


public interface UsuarioAPPDao {

	UsuarioAPP findUsuario(UsuarioAPP usuarioApp)throws Exception;

	void insertUsuario(UsuarioAPP usuarioAPP);

}
