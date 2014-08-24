package co.edu.eafit.coglovi.manager.alertas.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.eafit.coglovi.dao.alertas.EstadoEnvioDao;
import co.edu.eafit.coglovi.exception.QxException;
import co.edu.eafit.coglovi.manager.alertas.EstadoAlertaManager;
import co.edu.eafit.coglovi.manager.notificacionmanager.SendDataPush;
import co.edu.eafit.coglovi.model.Constantes.Alertas;
import co.edu.eafit.coglovi.model.Constantes.Alertas.TipoEstadoAlerta;
import co.edu.eafit.coglovi.model.alerta.Alerta;
import co.edu.eafit.coglovi.model.alerta.EstadoEnvio;

/**
 * Administra los estados de las alertas<br>
 * Creado el 20/06/2014 a las 7:42:41 <br>
 * 
 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
 */
@Service(value = "EstadoAlertaManager")
public class EstadoAlertaManagerImpl implements EstadoAlertaManager {

	@Autowired
	private EstadoEnvioDao estadoEnvioDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.manager.esatadoalerta.EstadoAlertaManager#cambiaEstadoAlerta(co.com.quipux.gestionapp.model.alerta.EstadoEnvio)
	 */
	@Override
	public void cambiaEstadoAlerta(EstadoEnvio estadoEnvio) {
		// se consulta el estado actual
		EstadoEnvio estadoActual = estadoEnvioDao.findEstadoActualAlerta(estadoEnvio.getEnvioAlertaUsuario().getIdEnvioAlerta());
		// si no hay estado actual se crea el inicial
		if (estadoActual == null) {
			estadoEnvio.getTipoEstadoAlerta().setIdEstado(Alertas.TipoEstadoAlerta.PENDIENTE_ENVIO);
			estadoEnvioDao.registrarEstadoEnvio(estadoEnvio);
		} else if (estadoActual.getTipoEstadoAlerta().getIdEstado() != estadoEnvio.getTipoEstadoAlerta().getIdEstado()) {
			// si el estado actual es diferente del nuevo estado
			// se cierra el estado actual y se crea uno nuevo
			estadoEnvioDao.cerrarEstadoEnvio(estadoActual);
			estadoEnvioDao.registrarEstadoEnvio(estadoEnvio);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.manager.alertas.EstadoAlertaManager#enviarAlertaParse(co.com.quipux.gestionapp.model.alerta.EstadoEnvio)
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public void enviarAlertaParse(EstadoEnvio estadoEnvio, SendDataPush push, List<Alerta> lista) throws Exception {
		// como pueden haber varias usuarios por alerta se envia solo una, pero se actualizan todos
		if (!lista.contains(estadoEnvio.getEnvioAlertaUsuario().getAlerta())) {
			// las alertas enviadas se actualiza su estado
			estadoEnvio.getTipoEstadoAlerta().setIdEstado(TipoEstadoAlerta.ENVIADA);
			cambiaEstadoAlerta(estadoEnvio);

			push.setChannel(new String[] { estadoEnvio.getEnvioAlertaUsuario().getAlerta().getCanal() });
			push.setIdAlerta(estadoEnvio.getEnvioAlertaUsuario().getAlerta().getIdAlerta().toString());
			if (!push.enviarPush()) {
				// se lanza exception para no registrar el envio de la alertas (rollback)
				throw new QxException();
			}else{
				lista.add(estadoEnvio.getEnvioAlertaUsuario().getAlerta());
			}
		} else {
			// las alertas enviadas se actualiza su estado
			estadoEnvio.getTipoEstadoAlerta().setIdEstado(TipoEstadoAlerta.ENVIADA);
			cambiaEstadoAlerta(estadoEnvio);
		}
	}

}
