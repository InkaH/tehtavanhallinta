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
		tehtava.setLisatiedot(rs.getString("t_lisatiedot"));
		tehtava.setStatus(rs.getInt("t_status"));
		tehtava.setPvm(rs.getTimestamp("t_deadlinedtm").toLocalDateTime().toLocalDate());
		tehtava.setAika(rs.getTimestamp("t_deadlinedtm").toLocalDateTime().toLocalTime());
		//tehtava.setMuistutusPvmaika(rs.getTimestamp("t_muistutusdtm").toLocalDateTime());
		return tehtava;
	}
}