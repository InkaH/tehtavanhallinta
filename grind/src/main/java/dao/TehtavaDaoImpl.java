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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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

	public void lisaaTehtava(Tehtava tehtava, String username) {
		final String sql_1 = "INSERT INTO tehtava(t_id, t_kuvaus, t_lisatiedot, t_status, t_deadlinedtm, t_ryhma, t_tekija) values(?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE t_kuvaus=?, t_lisatiedot=?, t_status=?, t_deadlinedtm=?, t_ryhma=?";
		
		final int id = tehtava.getId();
		final String kuvaus = tehtava.getKuvaus();
		final String tiedot = tehtava.getTiedot();
		final int status = tehtava.getStatus();
		final LocalDateTime ajankohta = tehtava.getAjankohta();
		final String ryhma = tehtava.getRyhma();
		final String tekija = username;
		KeyHolder idHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql_1);
				if (id == 0) {
					ps.setString(1, null);
				} else {
					ps.setInt(1, id);
				}
				ps.setString(2, kuvaus);
				ps.setString(3, tiedot);
				ps.setInt(4, status);
				ps.setTimestamp(5, Timestamp.valueOf(ajankohta));
				ps.setString(6, ryhma);
				ps.setString(7, tekija);
				// replacement values
				ps.setString(8, kuvaus);
				ps.setString(9, tiedot);
				ps.setInt(10, status);
				ps.setTimestamp(11, Timestamp.valueOf(ajankohta));
				ps.setString(12, ryhma);
				
				return ps;
			}
		}, idHolder);
		int teht_id = (idHolder.getKey().intValue());
		//käyttäjän tehtävät -taulu tarvitsee myös tiedon uudesta tehtävästä
		if(id==0){
			final String sql_2 = "INSERT INTO kayttajan_tehtava(kt_t_id, kt_k_nimi) VALUES (?, ?)";
			Object[] parameters = new Object[] { teht_id, username };
			getJdbcTemplate().update(sql_2, parameters);
		}	
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
	
	public void jaaTehtava(int id, String groupID) {
		final String sql = "UPDATE tehtava SET t_jaettu=1, t_ryhma = ? where t_id = ?";
		final int index = id;
		final String group = groupID;
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1, group);
				ps.setInt(2, index);
				return ps;
			}
		});
	}

	public List<Tehtava> haeKaikki(String username) {
		String sql = "SELECT t_id, t_kuvaus, t_lisatiedot, t_status, t_deadlinedtm, t_ryhma FROM kayttajan_tehtava INNER JOIN tehtava on kt_t_id=t_id WHERE kt_k_nimi=? ORDER BY t_deadlinedtm";
		RowMapper<Tehtava> mapper = new TehtavaRowMapper();
		List<Tehtava> tehtavat = jdbcTemplate.query(sql, new Object[] {username}, mapper);
		return tehtavat;
	}
}
