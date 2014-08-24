package co.edu.eafit.coglovi.manager.correo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.eafit.coglovi.dao.util.DaoUtil;
import co.edu.eafit.coglovi.manager.correo.EnvioCorreoManager;
import co.edu.eafit.coglovi.model.Constantes;
import co.edu.eafit.coglovi.model.usuariovehiculo.UsuarioAPP;
import co.edu.eafit.coglovi.transversal.EncryptClass;
import co.edu.eafit.coglovi.transversal.EnvioCorreoUtil;
import co.edu.eafit.coglovi.transversal.PropertiesManager;

@Service(value = "EnvioCorreoManager")
public class EnvioCorreoManagerImpl implements EnvioCorreoManager {

	@Autowired
	private DaoUtil daoUtil;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.quipux.gestionapp.manager.correo.EnvioCorreoManager#enviarCorreoRecuperarClave(co.com.quipux.gestionapp.model.usuariovehiculo.UsuarioAPP
	 * )
	 */
	@Override
	public void enviarCorreoRecuperarClave(UsuarioAPP usuarioAPP) throws Exception {
		EnvioCorreoUtil correo = new EnvioCorreoUtil();
		setDefaultParams(correo);
		correo.setCorreoDestino(new String[] { usuarioAPP.getCorreoElectronico() });
		correo.setSubject(PropertiesManager.getTextArgs("qxgestionapp.envioCorreo.recuperacionClave.suject",
				new Object[] { daoUtil.findValorParametro(Constantes.TipoParametro.NOMBRE_APLICATIVO_APP_FOTODETECCION) }));
		correo.setMensaje(PropertiesManager.getTextArgs(
				"qxgestionapp.envioCorreo.recuperacionClave.mensaje",
				new Object[] { daoUtil.findValorParametro(Constantes.TipoParametro.MENSAJE_CORREO_ELECTRONICO_RECUPERAR_CLAVE),
						usuarioAPP.getCorreoElectronico(), usuarioAPP.getClave(),
						daoUtil.findValorParametro(Constantes.TipoParametro.IMAGEN_CAMPANHA_CORREO_ELECTRONICO) }));
		correo.enviar();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.quipux.gestionapp.manager.correo.EnvioCorreoManager#enviarCorreoRegistrarse(co.com.quipux.gestionapp.model.usuariovehiculo.UsuarioAPP)
	 */
	@Override
	public void enviarCorreoRegistrarse(UsuarioAPP usuarioAPP) throws Exception {
		EnvioCorreoUtil correo = new EnvioCorreoUtil();
		setDefaultParams(correo);
		correo.setCorreoDestino(new String[] { usuarioAPP.getCorreoElectronico() });
		correo.setSubject(PropertiesManager.getTextArgs("qxgestionapp.envioCorreo.registrarse.suject",
				new Object[] { daoUtil.findValorParametro(Constantes.TipoParametro.NOMBRE_APLICATIVO_APP_FOTODETECCION) }));
		correo.setMensaje(PropertiesManager.getTextArgs(
				"qxgestionapp.envioCorreo.registrarse.mensaje",
				new Object[] { daoUtil.findValorParametro(Constantes.TipoParametro.MENSAJE_CORREO_ELECTRONICO_REGISTRARSE),
						usuarioAPP.getCorreoElectronico(), daoUtil.findValorParametro(Constantes.TipoParametro.IMAGEN_CAMPANHA_CORREO_ELECTRONICO) }));
		correo.enviar();
	}

	private void setDefaultParams(EnvioCorreoUtil correo) {
		correo.setCorreoOrigen(daoUtil.findValorParametro(Constantes.TipoParametro.CUENTA_ORIGEN_CORREO_ELECTRONICO_APP_FOTODETECCION));
		correo.setCorreoPass((new EncryptClass("OSO")).decrypt(daoUtil
				.findValorParametro(Constantes.TipoParametro.CONTRASENA_CUENTA_CORREO_ELECTRONICO_APP_FOTODETECCION)));
		correo.setHost(daoUtil.findValorParametro(Constantes.TipoParametro.CORREO_ELECTRONICO_HOST_APP_FOTODETECCION));
		correo.setPort(daoUtil.findValorParametro(Constantes.TipoParametro.CORREO_ELECTRONICO_PORT_APP_FOTODETECCION));
		correo.setSSL(true);
	}

}
