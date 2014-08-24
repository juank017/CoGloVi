package co.edu.eafit.coglovi.dao.administrarplacas.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import co.edu.eafit.coglovi.dao.administrarplacas.AdministraPlacaUsuarioDao;
import co.edu.eafit.coglovi.dao.util.DaoTemplate;
import co.edu.eafit.coglovi.exception.QxException;
import co.edu.eafit.coglovi.model.administrarplacas.AdministraPlacaUsuarioVO;
import co.edu.eafit.coglovi.transversal.PropertiesManager;

@Repository
public class AdministraPlacaUsuarioDaoJdbc extends DaoTemplate implements AdministraPlacaUsuarioDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.quipux.gestionapp.dao.administrarplacas.AdministraPlacaUsuarioDao#findByIdentificacion(co.com.quipux.gestionapp.model.administrarplacas
	 * .AdministraPlacaUsuarioVO)
	 */
	@Override
	public List<AdministraPlacaUsuarioVO> findByIdentificacion(AdministraPlacaUsuarioVO administraPlacaUsuarioVO) throws QxException {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "AdministrarPlacas.findByIdentificacion");
		RowMapper<AdministraPlacaUsuarioVO> rm = new RowMapper<AdministraPlacaUsuarioVO>() {
			public AdministraPlacaUsuarioVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				AdministraPlacaUsuarioVO administraPlacaUsuarioVO = new AdministraPlacaUsuarioVO();
				administraPlacaUsuarioVO.getUsuarioAPP().setIdUsuario(rs.getLong("ID_USUARIO"));
				administraPlacaUsuarioVO.getVehiculo().setIdVehiculo(rs.getLong("ID_VEHICULO"));
				administraPlacaUsuarioVO.getVehiculo().setPlaca(rs.getString("PLACA"));
				administraPlacaUsuarioVO.getUsuarioAPP().setNombres(rs.getString("NOMBRES") + " " + rs.getString("APELLIDOS") == null?"":rs.getString("APELLIDOS"));
				return administraPlacaUsuarioVO;
			}
		};
		return jdbcTemplate.query(sql, new Object[] { administraPlacaUsuarioVO.getTipoDocumentoIdentidad().getIdDocumentoIdentidad(),
				administraPlacaUsuarioVO.getUsuarioAPP().getNroDocumento() }, rm);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.quipux.gestionapp.dao.administrarplacas.AdministraPlacaUsuarioDao#findByPlaca(co.com.quipux.gestionapp.model.administrarplacas.
	 * AdministraPlacaUsuarioVO)
	 */
	@Override
	public List<AdministraPlacaUsuarioVO> findByPlaca(AdministraPlacaUsuarioVO administraPlacaUsuarioVO) throws QxException {
		String sql = PropertiesManager.obtenerCadena("gestionappSQL/gestionappSQL", "AdministrarPlacas.findByPlaca");
		RowMapper<AdministraPlacaUsuarioVO> rm = new RowMapper<AdministraPlacaUsuarioVO>() {
			public AdministraPlacaUsuarioVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				AdministraPlacaUsuarioVO administraPlacaUsuarioVO = new AdministraPlacaUsuarioVO();
				administraPlacaUsuarioVO.getUsuarioAPP().setIdUsuario(rs.getLong("ID_USUARIO"));
				administraPlacaUsuarioVO.getVehiculo().setIdVehiculo(rs.getLong("ID_VEHICULO"));
				administraPlacaUsuarioVO.getUsuarioAPP().setNroDocumento(rs.getString("ABREVIATURA") + " " + rs.getString("NRO_DOCUMENTO"));
				administraPlacaUsuarioVO.getUsuarioAPP().setNombres(rs.getString("NOMBRES") + " " + rs.getString("APELLIDOS"));
				return administraPlacaUsuarioVO;
			}
		};
		return jdbcTemplate.query(sql, new Object[] { administraPlacaUsuarioVO.getVehiculo().getPlaca().trim().toUpperCase()}, rm);
	}

}
