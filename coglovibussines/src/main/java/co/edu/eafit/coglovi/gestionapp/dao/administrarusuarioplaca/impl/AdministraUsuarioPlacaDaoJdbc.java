package co.edu.eafit.coglovi.gestionapp.dao.administrarusuarioplaca.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import co.edu.eafit.coglovi.gestionapp.dao.administrarusuarioplaca.AdministraUsuarioPlacaDao;
import co.edu.eafit.coglovi.gestionapp.dao.util.DaoTemplate;
import co.edu.eafit.coglovi.gestionapp.dao.util.DaoUtil;
import co.edu.eafit.coglovi.gestionapp.model.usuariovehiculo.PlacaOut;
import co.edu.eafit.coglovi.gestionapp.model.usuariovehiculo.RegistroUsuarioVehiculo;
import co.edu.eafit.coglovi.gestionapp.model.usuariovehiculo.UsuarioAPP;
import co.edu.eafit.coglovi.gestionapp.model.usuariovehiculo.UsuarioVehiculo;
import co.edu.eafit.coglovi.gestionapp.model.usuariovehiculo.Vehiculo;
import co.edu.eafit.coglovi.gestionapp.transversal.PropertiesManager;

/**
 * Implementa los metodos necesarios para la administracion de usuarios y placas.
 * @author daniel.rios
 *
 */
@Repository
public class AdministraUsuarioPlacaDaoJdbc extends DaoTemplate implements AdministraUsuarioPlacaDao {

	@Autowired
	private DaoUtil daoUtil;

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.administrarusuarioplaca.AdministraUsuarioPlacaDao#desactivarUsuarioVehiculo(co.com.quipux.gestionapp.model.
	 * usuariovehiculo.Vehiculo, co.com.quipux.gestionapp.model.usuariovehiculo.UsuarioAPP, java.lang.String)
	 */
	@Override
	public void activarUsuarioVehiculo(Vehiculo vehiculo, UsuarioAPP usuarioAPP, String activo) throws Exception {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "AdministraUsuarioPlacaDaoJdbc.activarUsuarioVehiculo");
		Object[] param = new Object[] { usuarioAPP.getTipoDocumentoIdentidad().getIdDocumentoIdentidad(), usuarioAPP.getNroDocumento(), activo,
				usuarioAPP.getIdUsuario(), vehiculo.getIdVehiculo() };
		jdbcTemplate.update(sql, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.quipux.gestionapp.dao.administrarusuarioplaca.AdministraUsuarioPlacaDao#consultarPlacasByUser(co.com.quipux.gestionapp.model.usuariovehiculo
	 * .UsuarioAPP)
	 */
	@Override
	public List<PlacaOut> consultarPlacasByUser(UsuarioAPP usuarioAPP) throws Exception {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "AdministraUsuarioPlacaDaoJdbc.consultarPlacasByUser");
		RowMapper<PlacaOut> rm = new RowMapper<PlacaOut>() {
			public PlacaOut mapRow(ResultSet rs, int rowNum) throws SQLException {
				PlacaOut placaOut = new PlacaOut();
				placaOut.setPlaca(rs.getString("PLACA"));
				placaOut.setIdDocumento(rs.getInt("id_documento_identidad"));
				placaOut.setNroDocumento(rs.getString("NRO_DOCUMENTO"));
				return placaOut;
			}
		};
		return jdbcTemplate.query(sql, new Object[] { usuarioAPP.getIdUsuario() }, rm);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.administrarusuarioplaca.AdministraUsuarioPlacaDao#eliminarPlacaByUsuario(co.com.quipux.gestionapp.model.
	 * usuariovehiculo.Vehiculo, co.com.quipux.gestionapp.model.usuariovehiculo.UsuarioAPP)
	 */
	@Override
	public void eliminarPlacaByUsuario(Vehiculo vehiculo, UsuarioAPP usuarioAPP) throws Exception {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "AdministraUsuarioPlacaDaoJdbc.eliminarPlacaByUsuario");
		Object[] param = new Object[] { vehiculo.getIdVehiculo(), usuarioAPP.getIdUsuario() };
		jdbcTemplate.update(sql, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.quipux.gestionapp.dao.administrarusuarioplaca.AdministraUsuarioPlacaDao#findVehiculo(co.com.quipux.gestionapp.model.usuariovehiculo.
	 * RegistroUsuarioVehiculo)
	 */
	@Override
	public Boolean existePlaca(RegistroUsuarioVehiculo registroUsuarioVehiculo) throws Exception {
		Boolean retorno = true;
		try {
			String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "AdministraUsuarioPlacaDaoJdbc.consultarVehiculo");
			long idVehiculo = jdbcTemplate.queryForLong(sql, new Object[] { registroUsuarioVehiculo.getVehiculo().getPlaca().toUpperCase().trim() });
			// Asigno a la placa, el identificador primario que le corresponde por BD.
			registroUsuarioVehiculo.getUsuarioVehiculo().getVehiculo().setIdVehiculo(idVehiculo);
			registroUsuarioVehiculo.getVehiculo().setIdVehiculo(idVehiculo);
		} catch (EmptyResultDataAccessException e) {
			retorno = false;
		}
		return retorno;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.quipux.gestionapp.dao.administrarusuarioplaca.AdministraUsuarioPlacaDao#existePlacaByUsuario(co.com.quipux.gestionapp.model.usuariovehiculo
	 * .RegistroUsuarioVehiculo)
	 */
	@Override
	public boolean existePlacaByUsuario(RegistroUsuarioVehiculo registroUsuarioVehiculo) {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "AdministraUsuarioPlacaDaoJdbc.consultarPlacaByUser");
		long cantidad = jdbcTemplate.queryForLong(sql, new Object[] { registroUsuarioVehiculo.getUsuarioVehiculo().getUsuarioAPP().getIdUsuario(),
				registroUsuarioVehiculo.getUsuarioVehiculo().getVehiculo().getIdVehiculo() });
		return cantidad > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.administrarusuarioplaca.AdministraUsuarioPlacaDao#findIdVehiculoByPlaca(java.lang.String)
	 */
	@Override
	public Long findIdVehiculoByPlaca(String placa) throws Exception {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "AdministraUsuarioPlacaDaoJdbc.consultarIdVehiculoByPlaca");
		return jdbcTemplate.queryForLong(sql, new Object[] { placa.toUpperCase().trim() });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.administrarusuarioplaca.AdministraUsuarioPlacaDao#inactivarUsuarioVehiculo(co.com.quipux.gestionapp.model.
	 * usuariovehiculo.Vehiculo, co.com.quipux.gestionapp.model.usuariovehiculo.UsuarioAPP, java.lang.String)
	 */
	@Override
	public void inactivarUsuarioVehiculo(Vehiculo vehiculo, UsuarioAPP usuarioAPP, String activo) throws Exception {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "AdministraUsuarioPlacaDaoJdbc.inactivarUsuarioVehiculo");
		Object[] param = new Object[] { activo, usuarioAPP.getIdUsuario(), vehiculo.getIdVehiculo() };
		jdbcTemplate.update(sql, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.quipux.gestionapp.dao.administrarusuarioplaca.AdministraUsuarioPlacaDao#modificarUsuarioAPP(co.com.quipux.gestionapp.model.usuariovehiculo
	 * .UsuarioAPP)
	 */
	@Override
	public void modificarUsuarioAPP(UsuarioAPP usuarioAPP) throws Exception {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "AdministraUsuarioPlacaDaoJdbc.modificarUsuarioAPP");
		Object[] param = new Object[] { usuarioAPP.getNombres(), usuarioAPP.getApellidos(), usuarioAPP.getCelular(),
				usuarioAPP.getTipoCiudad().getIdCiudad(), usuarioAPP.getDireccion(), usuarioAPP.getIdUsuario() };
		jdbcTemplate.update(sql, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.quipux.gestionapp.dao.administrarusuarioplaca.AdministraUsuarioPlacaDao#registrarUsuarioAPP(co.com.quipux.gestionapp.model.usuariovehiculo
	 * .UsuarioAPP)
	 */
	@Override
	public void registrarUsuarioAPP(UsuarioAPP usuarioAPP) throws Exception {
		String secuencia = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL",
				"AdministraUsuarioPlacaDaoJdbc.secuencia.usuarioAPP.idUsuario");
		usuarioAPP.setIdUsuario(daoUtil.getSecuenciaLong(secuencia));
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "AdministraUsuarioPlacaDaoJdbc.registrarUsuarioAPP");
		Object[] param = new Object[] { usuarioAPP.getIdUsuario(), usuarioAPP.getTipoDocumentoIdentidad().getIdDocumentoIdentidad(),
				usuarioAPP.getNroDocumento(), usuarioAPP.getNombres(), usuarioAPP.getApellidos(), usuarioAPP.getCelular(),
				usuarioAPP.getTipoCiudad().getIdCiudad(), usuarioAPP.getDireccion(), usuarioAPP.getCorreoElectronico(), usuarioAPP.getClave(),
				new Date(), usuarioAPP.getEnvioInformacion(), usuarioAPP.getCambioClave(), usuarioAPP.getActivo() };
		jdbcTemplate.update(sql, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.administrarusuarioplaca.AdministraUsuarioPlacaDao#registrarUsuarioVehiculo(co.com.quipux.gestionapp.model.
	 * usuariovehiculo.UsuarioVehiculo)
	 */
	@Override
	public void registrarUsuarioVehiculo(UsuarioVehiculo usuarioVehiculo) throws Exception {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "AdministraUsuarioPlacaDaoJdbc.registrarUsuarioVehiculo");
		Object[] param = new Object[] { usuarioVehiculo.getVehiculo().getIdVehiculo(), usuarioVehiculo.getUsuarioAPP().getIdUsuario(),
				usuarioVehiculo.getPropietario(), usuarioVehiculo.getTipoDocumentoIdentidad().getIdDocumentoIdentidad(),
				usuarioVehiculo.getNroDocumento() };
		jdbcTemplate.update(sql, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.quipux.gestionapp.dao.administrarusuarioplaca.AdministraUsuarioPlacaDao#registrarVehiculo(co.com.quipux.gestionapp.model.usuariovehiculo
	 * .Vehiculo)
	 */
	@Override
	public void registrarVehiculo(Vehiculo vehiculo) throws Exception {
		String secuencia = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL",
				"AdministraUsuarioPlacaDaoJdbc.secuencia.vehiculo.idVehiculo");
		vehiculo.setIdVehiculo(daoUtil.getSecuenciaLong(secuencia));
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "AdministraUsuarioPlacaDaoJdbc.registrarVehiculo");
		Object[] param = new Object[] { vehiculo.getIdVehiculo(), vehiculo.getPlaca().toUpperCase().trim() };
		jdbcTemplate.update(sql, param);
	}
}
