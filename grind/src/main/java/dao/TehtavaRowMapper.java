package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.Tehtava;
import bean.TehtavaImpl;

public class TehtavaRowMapper implements RowMapper<Tehtava> {

	public TehtavaImpl mapRow(ResultSet rs, int rowNum) throws SQLException {
		TehtavaImpl tehtava = new TehtavaImpl();
		tehtava.setId(rs.getInt("t_id"));
		tehtava.setKuvaus(rs.getString("t_kuvaus"));
		tehtava.setTiedot(rs.getString("t_lisatiedot"));
		tehtava.setStatus(rs.getInt("t_status"));
		tehtava.setAjankohta(rs.getTimestamp("t_deadlinedtm"));
		tehtava.setMuistutus(rs.getTimestamp("t_muistutusdtm"));
		return tehtava;
	}
}