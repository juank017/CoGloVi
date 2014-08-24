package co.edu.eafit.coglovi.manager.administrarusuarioplaca.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.eafit.coglovi.dao.administrarusuarioplaca.AdministraUsuarioPlacaDao;
import co.edu.eafit.coglovi.dao.alertas.AlertasDao;
import co.edu.eafit.coglovi.dao.autenticarseguridad.AutenticaUsuarioDao;
import co.edu.eafit.coglovi.exception.QxException;
import co.edu.eafit.coglovi.manager.administrarusuarioplaca.AdministraUsuarioPlacaManager;
import co.edu.eafit.coglovi.manager.correo.EnvioCorreoManager;
import co.edu.eafit.coglovi.model.Constantes;
import co.edu.eafit.coglovi.model.Constantes.TipoDocumentoIdentidad;
import co.edu.eafit.coglovi.model.autenticausuario.RestResponse;
import co.edu.eafit.coglovi.model.usuariovehiculo.PlacaOut;
import co.edu.eafit.coglovi.model.usuariovehiculo.PlacasResponse;
import co.edu.eafit.coglovi.model.usuariovehiculo.RegistroUsuarioVehiculo;
import co.edu.eafit.coglovi.model.usuariovehiculo.UsuarioAPP;
import co.edu.eafit.coglovi.model.usuariovehiculo.Vehiculo;
import co.edu.eafit.coglovi.transversal.PropertiesManager;
import co.edu.eafit.coglovi.transversal.Sha;

/**
 * Implementa los metodos necesarios para la administracion de usuarios y placas.
 * 
 * @author daniel.rios
 * 
 */
@Service
public class AdministraUsuarioPlacaManagerImpl implements AdministraUsuarioPlacaManager {

	@Autowired
	AdministraUsuarioPlacaDao administraUsuarioPlacaDao;
	@Autowired
	private AutenticaUsuarioDao auntenticaUsuarioDao;
	@Autowired
	private EnvioCorreoManager envioCorreoManager;
	@Autowired
	private AlertasDao alertasDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.quipux.gestionapp.manager.administrarusuarioplaca.AdministraUsuarioPlacaManager#consultarPlacasByUser(co.com.quipux.gestionapp.model
	 * .usuariovehiculo.UsuarioAPP)
	 */
	@Override
	public PlacasResponse consultarPlacasByUser(UsuarioAPP usuarioAPP) throws QxException, Exception {
		PlacasResponse placasResponse = null;
		List<PlacaOut> listPlacaOut = null;
		if (usuarioAPP == null) {
			throw new Exception();
		} else {
			placasResponse = new PlacasResponse();
			if (auntenticaUsuarioDao.existeIdUsusarioAPP(usuarioAPP.getIdUsuario())) {
				listPlacaOut = administraUsuarioPlacaDao.consultarPlacasByUser(usuarioAPP);
				if (listPlacaOut.isEmpty()) {
					placasResponse.getRestResponse().setCode(Constantes.CodigoEstadoComucacion.EXITOSO_SIN_RESULTADO);
				} else {
					placasResponse.setListaPlacas(listPlacaOut);
					placasResponse.getRestResponse().setCode(Constantes.CodigoEstadoComucacion.RESULTADO_EXITOSO);
				}
			} else {
				placasResponse.getRestResponse().setCode(Constantes.CodigoEstadoComucacion.ERROR_DATOS);
				placasResponse.getRestResponse().setDescription(
						PropertiesManager.getText("qxgestionapp.autenticaSeguridad.Login.iniciarSesion.exitosoSinResultados"));
			}
		}
		return placasResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.quipux.gestionapp.manager.administrarusuarioplaca.AdministraUsuarioPlacaManager#eliminarPlacasByUsuario(co.com.quipux.gestionapp.model
	 * .usuariovehiculo.UsuarioAPP, java.util.List)
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public RestResponse eliminarPlacaByUsuario(UsuarioAPP usuarioAPP, Vehiculo vehiculo) throws Exception {
		RestResponse restResponse = null;
		if ((usuarioAPP == null || vehiculo == null)
				&& (usuarioAPP.getIdUsuario() != null || usuarioAPP.getIdUsuario() != 0 || vehiculo.getPlaca() != null)) {
			throw new QxException();
		} else if (auntenticaUsuarioDao.existeIdUsusarioAPP(usuarioAPP.getIdUsuario()) && (auntenticaUsuarioDao.existePlaca(vehiculo.getPlaca()))) {
			// Consultamos el idVehiculo por placa
			vehiculo.setIdVehiculo(administraUsuarioPlacaDao.findIdVehiculoByPlaca(vehiculo.getPlaca()));
			// Vericiamos si existe registro en envio alerta
			if (alertasDao.existeAlertaByUserVehiculo(usuarioAPP.getIdUsuario(), vehiculo.getIdVehiculo())) {
				administraUsuarioPlacaDao.inactivarUsuarioVehiculo(vehiculo, usuarioAPP, "N");
			} else {
				// Generamos la eliminacion del vehiculo por usuario
				administraUsuarioPlacaDao.eliminarPlacaByUsuario(vehiculo, usuarioAPP);
			}
			restResponse = new RestResponse();
			restResponse.setCode(Constantes.CodigoEstadoComucacion.RESULTADO_EXITOSO);
		} else {
			restResponse = new RestResponse();
			restResponse.setCode(Constantes.CodigoEstadoComucacion.ERROR_DATOS);
			restResponse.setDescription(PropertiesManager.getText("qxgestionapp.autenticaSeguridad.Login.iniciarSesion.exitosoSinResultados"));
		}
		return restResponse;
	}

	/**
	 * Recorre una lista de placas e identifica si ya fueron insertadas o si se deben insertar por primera vez
	 * 
	 * @param listVehiculos
	 * @throws Exception
	 */
	private void getPlacas(List<RegistroUsuarioVehiculo> listRegistroUsuarioVehiculo) throws Exception {
		for (RegistroUsuarioVehiculo registroUsuarioVehiculo : listRegistroUsuarioVehiculo) {
			if (!administraUsuarioPlacaDao.existePlaca(registroUsuarioVehiculo)) {
				administraUsuarioPlacaDao.registrarVehiculo(registroUsuarioVehiculo.getVehiculo());
				registroUsuarioVehiculo.getUsuarioVehiculo().setVehiculo(registroUsuarioVehiculo.getVehiculo());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.manager.administrarusuarioplaca.AdministraUsuarioPlacaManager#modificarUsuario(co.com.quipux.gestionapp.model.
	 * usuariovehiculo.UsuarioAPP)
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public RestResponse modificarUsuario(UsuarioAPP usuarioAPP) throws Exception {
		RestResponse restResponse = null;
		if (usuarioAPP == null) {
			throw new Exception();
		} else {
			if (!validarDatosUsuarioAPPModificarUsuario(usuarioAPP)) {
				restResponse = new RestResponse();
				restResponse.setCode(Constantes.CodigoEstadoComucacion.ERROR_DATOS);
				restResponse.setDescription(PropertiesManager.getText("qxgestionapp.administrarUsuarioPlaca.errorDatos"));
			} else {
				if (auntenticaUsuarioDao.existeIdUsusarioAPP(usuarioAPP.getIdUsuario())) {
					// Modificamos informacion usuario
					administraUsuarioPlacaDao.modificarUsuarioAPP(usuarioAPP);
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
	 * @see co.com.quipux.gestionapp.manager.administrarusuarioplaca.AdministraUsuarioPlacaManager#registrarPlaca(co.com.quipux.gestionapp.model.
	 * usuariovehiculo.UsuarioAPP, java.util.List)
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public RestResponse registrarPlaca(UsuarioAPP usuarioAPP, List<RegistroUsuarioVehiculo> listRegistroUsuarioVehiculo) throws Exception {
		RestResponse restResponse = null;
		if (usuarioAPP == null && listRegistroUsuarioVehiculo == null) {
			throw new Exception();
		} else {
			if (listRegistroUsuarioVehiculo.isEmpty() || !validarDatosUsuarioVehiculo(listRegistroUsuarioVehiculo)
					|| usuarioAPP.getIdUsuario() == null) {
				restResponse = new RestResponse();
				restResponse.setCode(Constantes.CodigoEstadoComucacion.ERROR_DATOS);
				restResponse.setDescription(PropertiesManager.getText("qxgestionapp.administrarUsuarioPlaca.errorDatos"));
			} else {
				if (auntenticaUsuarioDao.existeIdUsusarioAPP(usuarioAPP.getIdUsuario())) {
					updateListPlacasByUser(listRegistroUsuarioVehiculo, usuarioAPP);
					// Generamos la insercion de placas
					if (registrarPlacas(listRegistroUsuarioVehiculo, usuarioAPP)) {
						// Generamos la respuesta
						restResponse = new RestResponse();
						restResponse.setCode(Constantes.CodigoEstadoComucacion.RESULTADO_EXITOSO);
					} else {
						throw new QxException();
					}
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

	/**
	 * Recorre una lista de placas e identifica si ya fueron insertadas o si se deben insertar por primera vez
	 * 
	 * @param listVehiculos
	 * @throws Exception
	 */
	private Boolean registrarPlacas(List<RegistroUsuarioVehiculo> listRegistroUsuarioVehiculo, UsuarioAPP usuarioAPP) throws Exception {
		List<RegistroUsuarioVehiculo> listReturnRegistroUsuarioVehiculo = new ArrayList<RegistroUsuarioVehiculo>();
		Boolean retorno = false;
		for (RegistroUsuarioVehiculo registroUsuarioVehiculo : listRegistroUsuarioVehiculo) {
			// Verificamos si ya existe la placa. Si se cumple la condicion, capturamos el idvehiculo.
			if (administraUsuarioPlacaDao.existePlaca(registroUsuarioVehiculo)) {
				if (administraUsuarioPlacaDao.existePlacaByUsuario(registroUsuarioVehiculo)) {
					administraUsuarioPlacaDao.activarUsuarioVehiculo(registroUsuarioVehiculo.getVehiculo(), usuarioAPP, "S");
					retorno = true;
				} else {
					listReturnRegistroUsuarioVehiculo.add(registroUsuarioVehiculo);
				}
			} else {
				administraUsuarioPlacaDao.registrarVehiculo(registroUsuarioVehiculo.getVehiculo());
				listReturnRegistroUsuarioVehiculo.add(registroUsuarioVehiculo);
			}
		}
		if (listReturnRegistroUsuarioVehiculo != null || !listReturnRegistroUsuarioVehiculo.isEmpty()) {
			registrarUsuarioVehiculo(listReturnRegistroUsuarioVehiculo);
			retorno = true;
		}
		return retorno;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.quipux.gestionapp.manager.administrarusuarioplaca.AdministraUsuarioPlacaManager#registrarse(co.com.quipux.gestionapp.model.usuariovehiculo
	 * .UsuarioAPP, java.util.List)
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public RestResponse registrarse(UsuarioAPP usuarioAPP, List<RegistroUsuarioVehiculo> listRegistroUsuarioVehiculo) throws QxException, Exception {
		RestResponse restResponse = null;
		if (usuarioAPP == null && listRegistroUsuarioVehiculo == null) {
			throw new Exception();
		} else {
			// usuarioAPP.setUsuarioAcceso(QxUtil.DesencryptUserLogin(usuarioAPP.getUsuarioAcceso()));
			// usuarioAPP.setClave(Sha.hash256(QxUtil.DesencryptPasswordLogin(usuarioAPP.getClave())));
			usuarioAPP.setClave(Sha.hash256(usuarioAPP.getClave()));
			if (listRegistroUsuarioVehiculo.isEmpty() || !validarDatosUusarioAPP(usuarioAPP)
					|| !validarDatosUsuarioVehiculo(listRegistroUsuarioVehiculo)) {
				restResponse = new RestResponse();
				restResponse.setCode(Constantes.CodigoEstadoComucacion.ERROR_DATOS);
				restResponse.setDescription(PropertiesManager.getText("qxgestionapp.administrarUsuarioPlaca.errorDatos"));
			} else {
				if (auntenticaUsuarioDao.existeUsuarioAcceso(usuarioAPP.getCorreoElectronico())) {
					throw new QxException();
				} else {
					// Registramos el usuario
					administraUsuarioPlacaDao.registrarUsuarioAPP(usuarioAPP);
					updateListPlacasByUser(listRegistroUsuarioVehiculo, usuarioAPP);
					// Generamos la insercion de placas
					getPlacas(listRegistroUsuarioVehiculo);
					// Generamos la insercion de usuario por vehiculos
					registrarUsuarioVehiculo(listRegistroUsuarioVehiculo);
					envioCorreoManager.enviarCorreoRegistrarse(usuarioAPP);
					// Generamos la respuesta
					restResponse = new RestResponse();
					restResponse.setCode(Constantes.CodigoEstadoComucacion.RESULTADO_EXITOSO);
				}
			}
		}

		return restResponse;
	}

	/**
	 * Se encarga de recorrer la lista con objetos de tipo RegistroUsuarioVehiculo, el cual contiene el objeto UsuarioVehiculo a ser insertado
	 * 
	 * @param listRegistroUsuarioVehiculo
	 * @throws Exception
	 */
	private void registrarUsuarioVehiculo(List<RegistroUsuarioVehiculo> listRegistroUsuarioVehiculo) throws Exception {
		for (RegistroUsuarioVehiculo registroUsuarioVehiculo : listRegistroUsuarioVehiculo) {
			registroUsuarioVehiculo.getUsuarioVehiculo().setVehiculo(registroUsuarioVehiculo.getVehiculo());
			administraUsuarioPlacaDao.registrarUsuarioVehiculo(registroUsuarioVehiculo.getUsuarioVehiculo());
		}
	}

	/**
	 * Setea la lista de usuarios por vehiculo.
	 * 
	 * @param listRegistroUsuarioVehiculo
	 * @param usuarioAPP
	 * @throws Exception
	 */
	private void updateListPlacasByUser(List<RegistroUsuarioVehiculo> listRegistroUsuarioVehiculo, UsuarioAPP usuarioAPP) throws Exception {
		for (RegistroUsuarioVehiculo registroUsuarioVehiculo : listRegistroUsuarioVehiculo) {
			registroUsuarioVehiculo.getUsuarioVehiculo().setUsuarioAPP(usuarioAPP);
		}
	}

	/**
	 * Valida los dfatos obligatorios de la funcionalidad modificar usuario
	 * 
	 * @param usuarioAPP
	 * @return
	 */
	private boolean validarDatosUsuarioAPPModificarUsuario(UsuarioAPP usuarioAPP) {
		Boolean retorno = true;

		if (usuarioAPP.getNroDocumento() == null || usuarioAPP.getNroDocumento().isEmpty()) {
			retorno = false;
		}

		if (usuarioAPP.getNombres() == null || usuarioAPP.getNombres().isEmpty()) {
			retorno = false;
		}

		if (usuarioAPP.getApellidos() == null || usuarioAPP.getApellidos().isEmpty()) {
			retorno = false;
		}

		if (usuarioAPP.getCorreoElectronico() == null || usuarioAPP.getCorreoElectronico().isEmpty()) {
			retorno = false;
		}
		return retorno;
	}

	/**
	 * Valida los datos requeridos del vehiculo y del propietario
	 * 
	 * @param listVehiculo
	 * @return
	 */
	private boolean validarDatosUsuarioVehiculo(List<RegistroUsuarioVehiculo> listRegistroUsuarioVehiculo) {
		Boolean retorno = true;
		for (RegistroUsuarioVehiculo registroUsuarioVehiculo : listRegistroUsuarioVehiculo) {
			if (registroUsuarioVehiculo.getVehiculo().getPlaca() == null || registroUsuarioVehiculo.getVehiculo().getPlaca().isEmpty()
					|| registroUsuarioVehiculo.getUsuarioVehiculo().getPropietario() == null
					|| "".equals(registroUsuarioVehiculo.getUsuarioVehiculo().getPropietario())) {
				retorno = false;
				break;
			}
		}
		return retorno;
	}

	/**
	 * Se validan las propiedades obligatorias del usuarioAPP
	 * 
	 * @param usuarioAPP
	 * @return
	 */
	private boolean validarDatosUusarioAPP(UsuarioAPP usuarioAPP) {
		Boolean retorno = true;

		if (usuarioAPP.getTipoDocumentoIdentidad().getIdDocumentoIdentidad() == null
				|| usuarioAPP.getTipoDocumentoIdentidad().getIdDocumentoIdentidad() == null) {
			retorno = false;
		}

		if (usuarioAPP.getNroDocumento() == null || usuarioAPP.getNroDocumento().isEmpty()) {
			retorno = false;
		}

		if (usuarioAPP.getNombres() == null || usuarioAPP.getNombres().isEmpty()) {
			retorno = false;
		}
		
		if(usuarioAPP.getTipoDocumentoIdentidad().getIdDocumentoIdentidad() != TipoDocumentoIdentidad.NIT){
			if (usuarioAPP.getApellidos() == null || usuarioAPP.getApellidos().isEmpty()) {
				retorno = false;
			}
		}

		if (usuarioAPP.getCorreoElectronico() == null || usuarioAPP.getCorreoElectronico().isEmpty()) {
			retorno = false;
		}

		if (usuarioAPP.getClave() == null || usuarioAPP.getClave().isEmpty()) {
			retorno = false;
		}

		if (usuarioAPP.getEnvioInformacion() == null || usuarioAPP.getEnvioInformacion().isEmpty()) {
			retorno = false;
		}

		if (usuarioAPP.getCambioClave() == null || usuarioAPP.getCambioClave().isEmpty()) {
			retorno = false;
		}

		if (usuarioAPP.getActivo() == null || usuarioAPP.getActivo().isEmpty()) {
			retorno = false;
		}

		return retorno;
	}
}
