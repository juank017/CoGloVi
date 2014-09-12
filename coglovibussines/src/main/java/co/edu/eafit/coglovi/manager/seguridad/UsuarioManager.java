package co.edu.eafit.coglovi.manager.seguridad;

import java.util.List;

import co.edu.eafit.coglovi.model.usuario.GrupoInteres;
import co.edu.eafit.coglovi.model.usuario.UsuarioAPP;

public interface UsuarioManager {
	boolean registroUsuario(UsuarioAPP usuarioAPP, List<GrupoInteres> gruposInteres) throws Exception;
	boolean isValidUsuario(UsuarioAPP usuarioAPP) throws Exception;
}
