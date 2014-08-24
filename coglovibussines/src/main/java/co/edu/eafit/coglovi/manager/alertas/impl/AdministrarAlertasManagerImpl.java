package co.edu.eafit.coglovi.manager.alertas.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.eafit.coglovi.dao.alertas.AlertasDao;
import co.edu.eafit.coglovi.dao.alertas.EnvioAlertasDao;
import co.edu.eafit.coglovi.dao.autenticarseguridad.AutenticaUsuarioDao;
import co.edu.eafit.coglovi.dao.util.DaoUtil;
import co.edu.eafit.coglovi.exception.QxException;
import co.edu.eafit.coglovi.manager.alertas.AdministrarAlertasManager;
import co.edu.eafit.coglovi.manager.alertas.EstadoAlertaManager;
import co.edu.eafit.coglovi.manager.notificacionmanager.SendDataPush;
import co.edu.eafit.coglovi.model.Constantes.TipoParametro;
import co.edu.eafit.coglovi.model.Constantes.Alertas.TipoDirectorioAlerta;
import co.edu.eafit.coglovi.model.Constantes.Alertas.TipoEstadoAlerta;
import co.edu.eafit.coglovi.model.alerta.Alerta;
import co.edu.eafit.coglovi.model.alerta.EnvioAlertaUsuario;
import co.edu.eafit.coglovi.model.alerta.EstadoEnvio;
import co.edu.eafit.coglovi.model.alerta.UserAlert;
import co.edu.eafit.coglovi.model.usuariovehiculo.UsuarioVehiculo;
import co.edu.eafit.coglovi.transversal.QxUtil;

/**
 * Implementa los métodos necesarios para la administracion de alertas<br>
 * Creado el 20/06/2014 a las 15:03:39 <br>
 * 
 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
 */
@Service(value = "AdministrarAlertasManager")
public class AdministrarAlertasManagerImpl implements AdministrarAlertasManager {

	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private AlertasDao alertasDao;
	@Autowired
	private EnvioAlertasDao envioAlertasDao;
	@Autowired
	private EstadoAlertaManager estadoAlertaManager;
	@Autowired
	private DaoUtil daoUtil;
	@Autowired
	private AutenticaUsuarioDao auntenticaUsuarioDao;
	private Date ultimaEjecucionEnvioParse = new Date();

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.manager.alertas.AdministrarAlertasManager#enviarAlertasParse()
	 */
	@Override
	public void enviarAlertasParse() throws ClientProtocolException, IOException {
		// validacion de ejecucion de proceso automatico
		int param = Integer.valueOf(daoUtil.findValorParametro(TipoParametro.TIEMPO_EJECUCIONES_PROCESO_ENVIO_ALERTAS));
		Date fechaActual = new Date();
		if (QxUtil.getDiferenciaEnMinutos(ultimaEjecucionEnvioParse, fechaActual) >= param) {
			ultimaEjecucionEnvioParse = fechaActual;
			List<EstadoEnvio> listaAlertas = envioAlertasDao.findAlertasEnvioParse();
			List<Alerta> lista = new ArrayList<Alerta>();
			if (!listaAlertas.isEmpty()) {
				SendDataPush push = new SendDataPush();
				// se consultan los parametros
				int diasExpiraAlerta = Integer.valueOf(daoUtil.findValorParametro(TipoParametro.DIAS_EXPIRA_ALERTA));
				push.setApplicationID(daoUtil.findValorParametro(TipoParametro.VALOR_APLICATION_ID_PARSE));
				push.setRestApiKey(daoUtil.findValorParametro(TipoParametro.VALOR_REST_API_KEY_PARSE));
				push.setUrlPush(daoUtil.findValorParametro(TipoParametro.VALOR_PUSH_URL_PARSE));
				push.setFechaExpira(QxUtil.dateToString(QxUtil.addDaysToDate(fechaActual, diasExpiraAlerta), "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));

				for (EstadoEnvio estadoEnvio : listaAlertas) {
					try {
						estadoAlertaManager.enviarAlertaParse(estadoEnvio, push, lista);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}

			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.manager.alertas.AdministrarAlertasManager#existeAlertaIdentificacion(java.lang.String)
	 */
	@Override
	public boolean existeAlertaIdentificacion(String identificadorAlerta) {
		return alertasDao.existeAlertaIdentificacion(identificadorAlerta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.quipux.gestionapp.manager.alertas.AdministrarAlertasManager#findAlertaByUsuarioAlerta(co.com.quipux.gestionapp.model.alerta.UserAlert)
	 */
	@Override
	public EstadoEnvio findAlertaByUsuarioAlerta(UserAlert userAlert) {
		return alertasDao.findAlertaByUsuarioAlerta(userAlert);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.manager.alertas.AdministrarAlertasManager#findAlertasByUsuario(java.lang.Long)
	 */
	@Override
	public List<EstadoEnvio> findAlertasByUsuario(Long idUsuario) {
		return alertasDao.findAlertasByUsuario(idUsuario);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.quipux.gestionapp.manager.alertas.AdministrarAlertasManager#modificarDirectorioAlerta(co.com.quipux.gestionapp.model.alerta.UserAlert)
	 */
	@Override
	public void modificarDirectorioAlerta(UserAlert userAlert) throws Exception {
		EnvioAlertaUsuario envioAlerta = envioAlertasDao.findEnvioByAlertaAndUsuario(userAlert);
		if (envioAlerta != null) {
			envioAlerta.setIdDirectorio(userAlert.getIdDirectorio());
			envioAlertasDao.updateDirectorioAlerta(envioAlerta);
		} else {
			throw new QxException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.quipux.gestionapp.manager.alertas.AdministrarAlertasManager#registrarEstadoEntregado(co.com.quipux.gestionapp.model.alerta.EstadoEnvio)
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void registrarEstadoEntregado(EstadoEnvio estadoEnvio) {
		estadoEnvio.getTipoEstadoAlerta().setIdEstado(TipoEstadoAlerta.ENTREGADA);
		estadoAlertaManager.cambiaEstadoAlerta(estadoEnvio);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.manager.registraralerta.RegistrarAlertaManager#registrarInforAlerta(co.com.quipux.gestionapp.model.alerta.Alerta)
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public void registrarInforAlerta(Alerta alerta) throws QxException {
		EnvioAlertaUsuario envioAlertaUsuario = null;
		EstadoEnvio estadoEnvio = null;
		// obtener UsuarioVehiculo
		List<UsuarioVehiculo> listUsuarioVehiculo = alertasDao.findUsuarioVehiculo(alerta);
		if (!listUsuarioVehiculo.isEmpty()) {
			// se registra en la tabla ppal de la alerta
			alertasDao.registrarAlerta(alerta);
			for (UsuarioVehiculo usuarioVehiculo : listUsuarioVehiculo) {
				// se registra en envio_alerta por cada usuario registrado
				envioAlertaUsuario = new EnvioAlertaUsuario();
				envioAlertaUsuario.setUsuarioVehiculo(usuarioVehiculo);
				envioAlertaUsuario.setAlerta(alerta);
				envioAlertaUsuario.setIdDirectorio(TipoDirectorioAlerta.BANDEJA_ENTRADA);
				envioAlertasDao.registrarEnvioAlerta(envioAlertaUsuario);
				// se registra el estado inicial de la alerta Pendiente Envio
				estadoEnvio = new EstadoEnvio();
				estadoEnvio.setEnvioAlertaUsuario(envioAlertaUsuario);
				estadoAlertaManager.cambiaEstadoAlerta(estadoEnvio);
			}
		} else {
			// si no existen usuarios asociados al canal
			throw new QxException();
		}
	}

}
