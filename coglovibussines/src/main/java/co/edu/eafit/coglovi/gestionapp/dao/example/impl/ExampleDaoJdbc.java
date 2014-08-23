package co.edu.eafit.coglovi.gestionapp.dao.example.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import co.edu.eafit.coglovi.gestionapp.dao.example.ExampleDao;
import co.edu.eafit.coglovi.gestionapp.dao.util.DaoTemplate;

@Repository
public class ExampleDaoJdbc extends DaoTemplate implements ExampleDao{


    @Override
    public void selectSysdate() {
    	RowMapper<Object> rm=new RowMapper<Object>() {
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("fecha");
            }
        };
        System.out.println(jdbcTemplate.queryForObject("select sysdate as fecha from dual",rm));
    }
}