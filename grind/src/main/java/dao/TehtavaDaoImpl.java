package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import bean.Tehtava;
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
		final String sql = "INSERT INTO tehtava(t_id, t_kuvaus, t_lisatiedot, t_status, t_deadlinedtm) values(?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE t_kuvaus=?, t_lisatiedot=?, t_status=?, t_deadlinedtm=?";
		final int id = tehtava.getId();
		final String kuvaus = tehtava.getKuvaus();
		final String tiedot = tehtava.getTiedot();
		final int status = tehtava.getStatus();
		final LocalDateTime ajankohta = tehtava.getAjankohta();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql);
				if (id == 0) {
					ps.setString(1, null);
				} else {
					ps.setInt(1, id);
				}
				ps.setString(2, kuvaus);
				ps.setString(3, tiedot);
				ps.setInt(4, status);
				ps.setTimestamp(5, Timestamp.valueOf(ajankohta));
				// replacement values
				ps.setString(6, kuvaus);
				ps.setString(7, tiedot);
				ps.setInt(8, status);
				ps.setTimestamp(9, Timestamp.valueOf(ajankohta));
				return ps;
			}
		});
	}

	public void poistaTehtava(int id) {
		final String sql = "DELETE FROM tehtava where t_id = ?";
		final int index = id;
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setInt(1, index);
				return ps;
			}
		});
	}
	
	public void jaaTehtava(int id) {
		final String sql = "UPDATE tehtava SET t_ryhma = 'ICT1TA001' where t_id = ?";
		final int index = id;
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setInt(1, index);
				return ps;
			}
		});
	}

	public List<Tehtava> haeKaikki() {
		String sql = "SELECT t_id, t_kuvaus, t_lisatiedot, t_status, t_deadlinedtm FROM tehtava ORDER BY t_deadlinedtm";
		RowMapper<Tehtava> mapper = new TehtavaRowMapper();
		List<Tehtava> tehtavat = jdbcTemplate.query(sql, mapper);
		return tehtavat;
	}
}
