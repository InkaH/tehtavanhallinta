package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import bean.Tehtava;

public class TehtavaRowMapper implements RowMapper<Tehtava> {

	public Tehtava mapRow(ResultSet rs, int rowNum) throws SQLException {
		Tehtava tehtava = new Tehtava();
		tehtava.setId(rs.getInt("t_id"));
		tehtava.setKuvaus(rs.getString("t_kuvaus"));
		tehtava.setTiedot(rs.getString("t_lisatiedot"));
		tehtava.setStatus(rs.getInt("t_status"));
		tehtava.setAjankohta(rs.getTimestamp("t_deadlinedtm").toLocalDateTime());
		tehtava.setRyhma(rs.getString("t_ryhma"));
		tehtava.setUser(rs.getString("kt_k_nimi"));
		return tehtava;
	}
}