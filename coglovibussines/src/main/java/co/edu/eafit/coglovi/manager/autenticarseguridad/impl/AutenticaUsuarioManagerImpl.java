package co.edu.eafit.coglovi.manager.autenticarseguridad.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.eafit.coglovi.dao.administrarusuarioplaca.AdministraUsuarioPlacaDao;
import co.edu.eafit.coglovi.dao.autenticarseguridad.AutenticaUsuarioDao;
import co.edu.eafit.coglovi.dao.usuarioapp.UsuarioAPPDao;
import co.edu.eafit.coglovi.exception.QxException;
import co.edu.eafit.coglovi.manager.autenticarseguridad.AutenticaUsuarioManager;
import co.edu.eafit.coglovi.manager.correo.EnvioCorreoManager;
import co.edu.eafit.coglovi.model.Constantes;
import co.edu.eafit.coglovi.model.Constantes.Activo;
import co.edu.eafit.coglovi.model.autenticausuario.CambioClave;
import co.edu.eafit.coglovi.model.autenticausuario.RestResponse;
import co.edu.eafit.coglovi.model.autenticausuario.User;
import co.edu.eafit.coglovi.model.usuariovehiculo.UsuarioAPP;
import co.edu.eafit.coglovi.model.usuariovehiculo.UsuarioVehiculoResponse;
import co.edu.eafit.coglovi.transversal.PasswordGenerator;
import co.edu.eafit.coglovi.transversal.PropertiesManager;
import co.edu.eafit.coglovi.transversal.Sha;

@Service
public class AutenticaUsuarioManagerImpl implements AutenticaUsuarioManager {

	@Autowired
	private AutenticaUsuarioDao auntenticaUsuarioDao;
	@Autowired
	private AdministraUsuarioPlacaDao administraUsuarioPlacaDao;
	private final int LONGITUD_CLAVE = 6;
	@Autowired
	private UsuarioAPPDao usuarioAPPDao;
	@Autowired
	private EnvioCorreoManager envioCorreoManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.manager.autenticarseguridad.AuntenticaUsuarioManager#login(co.com.quipux.gestionapp.model.security.User)
	 */
	@Override
	public UsuarioVehiculoResponse login(User user) throws Exception {
		UsuarioVehiculoResponse vehiculoResponse = null;
		UsuarioAPP usuarioAPP = null;
		Long idUsuario = null;
		if (user == null) {
			throw new Exception();
		} else {
			if ((user.getName() == null || user.getName().isEmpty())
					|| (user.getPassword() == null || user.getPassword().isEmpty() || user.getPassword().length() < LONGITUD_CLAVE)) {
				vehiculoResponse = new UsuarioVehiculoResponse();
				vehiculoResponse.getRestResponse().setCode(Constantes.CodigoEstadoComucacion.ERROR_DATOS);
				vehiculoResponse.getRestResponse().setDescription(PropertiesManager.getText("qxgestionapp.administrarUsuarioPlaca.errorDatos"));
			} else {
				vehiculoResponse = new UsuarioVehiculoResponse();
				// user.setName(QxUtil.DesencryptUserLogin(user.getName()));
				// user.setPassword(Sha.hash256(QxUtil.DesencryptPasswordLogin(user.getPassword())));
				user.setPassword(Sha.hash256(user.getPassword()));
				idUsuario = auntenticaUsuarioDao.validateLogin(user);
				if (idUsuario == null) {
					vehiculoResponse.getRestResponse().setCode(Constantes.CodigoEstadoComucacion.EXITOSO_SIN_RESULTADO);
				} else {
					// Consulto la informacion del usuario logueado
					usuarioAPP = auntenticaUsuarioDao.findUserAppByIdUsuario(idUsuario);
					// Seteo al objeto de respuesta los datos del usuario
					vehiculoResponse.setUsuarioAPP(usuarioAPP);
					vehiculoResponse.getRestResponse().setCode(Constantes.CodigoEstadoComucacion.RESULTADO_EXITOSO);
				}
			}
		}
		return vehiculoResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.manager.autenticarseguridad.AutenticaUsuarioManager#cambiarClave(co.com.quipux.gestionapp.model.autenticausuario.
	 * CambioClave)
	 */
	@Override
	public RestResponse cambiarClave(CambioClave cambioClave) throws Exception {
		RestResponse restResponse = null;
		if (cambioClave == null) {
			throw new Exception();
		} else {
			if (cambioClave.getIdUsuario() == null || cambioClave.getIdUsuario() == 0 || cambioClave.getClave() == null
					|| cambioClave.getClave().isEmpty() || cambioClave.getClave().length() < LONGITUD_CLAVE) {
				restResponse = new RestResponse();
				restResponse.setCode(Constantes.CodigoEstadoComucacion.ERROR_DATOS);
				restResponse.setDescription(PropertiesManager.getText("qxgestionapp.administrarUsuarioPlaca.errorDatos"));
			} else {
				if (auntenticaUsuarioDao.existeIdUsusarioAPP(cambioClave.getIdUsuario())) {
					// Genero la encripcion de la clave, para su correspondiente actualizacion
					// cambioClave.setClave(Sha.hash256(QxUtil.DesencryptPasswordLogin(cambioClave.getClave())));
					cambioClave.setClave(Sha.hash256(cambioClave.getClave()));
					// Generamos la actualizacion de la clave
					auntenticaUsuarioDao.cambiarClave(cambioClave);
					// Generamos la respuesta
					restResponse = new RestResponse();
					restResponse.setCode(Constantes.CodigoEstadoComucacion.RESULTADO_EXITOSO);
				} else {
					restResponse = new RestResponse();
					restResponse.setCode(Constantes.CodigoEstadoComucacion.ERROR_DATOS);
					restResponse
							.setDescription(PropertiesManager.getText("qxgestionapp.autenticaSeguridad.Login.iniciarSesion.exitosoSinResultados"));
				}
			}
		}
		return restResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.quipux.gestionapp.manager.autenticarseguridad.AutenticaUsuarioManager#recuperarClave(co.com.quipux.gestionapp.model.usuariovehiculo.
	 * UsuarioAPP)
	 */
	@Override
	public void recuperarClave(UsuarioAPP user) throws Exception {
		user = usuarioAPPDao.findUsuarioAPPByUserName(user.getCorreoElectronico());
		if (user != null) {
			// se genera una clave aleatoria
			String clave = PasswordGenerator.getPassword(LONGITUD_CLAVE);
			user.setClave(Sha.hash256(clave));
			user.setCambioClave(Activo.SI);
			usuarioAPPDao.registrarRecuperacionClave(user);
			// se envia el correo electronico con el cambio
			user.setClave(clave);
			envioCorreoManager.enviarCorreoRecuperarClave(user);
		} else {
			throw new QxException();
		}
	}
}
