package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import bean.Tehtava;

import java.util.Date;
import java.util.List;

@Component
public class TehtavaDaoImpl implements TehtavaDao {
	
	@Inject
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void lisaaTehtava(Tehtava tehtava) {
		final String sql = "INSERT INTO tehtava(t_kuvaus, t_lisatiedot, t_status, t_deadlinedtm, t_muistutusdtm) values(?, ?, ?, ?, ?)";
		final String kuvaus = tehtava.getKuvaus();
		final String tiedot = tehtava.getTiedot();
		final int status = tehtava.getStatus();
		final Date ajankohta = tehtava.getAjankohta();
		final Date muistutus = tehtava.getMuistutus();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1, kuvaus);
				ps.setString(2, tiedot);
				ps.setInt(3, status);
				ps.setTimestamp(4, new Timestamp(ajankohta.getTime()));
				ps.setTimestamp(5, new Timestamp(muistutus.getTime()));
				return ps;
			}
		});
	}
	
	public List<Tehtava> haeKaikki() {
		String sql = "SELECT t_id, t_kuvaus, t_lisatiedot, t_status, t_deadlinedtm, t_muistutusdtm FROM tehtava ORDER BY t_deadlinedtm";
		RowMapper<Tehtava> mapper = new TehtavaRowMapper();
		List<Tehtava> tehtavat = jdbcTemplate.query(sql, mapper);
		return tehtavat;
	}
}
