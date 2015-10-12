package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
//import java.util.List;
import javax.inject.Inject;
//import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import bean.Tehtava;
//import org.springframework.stereotype.Repository;
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
	
	// Tehtävän lisäys
	public void lisaaTehtava(Tehtava tehtava) {
		final String sql = "INSERT INTO tehtava(t_kuvaus, t_lisatiedot, t_status, t_deadlinedtm, t_muistutusdtm) values(?, ?, ?, ?, ?)";
		// anonyymi sisäluokka tarvitsee vakioina välitettävät arvot,
	    // jotta roskien keruu onnistuu tämän metodin suorituksen päättyessä.
		final String kuvaus = tehtava.getKuvaus();
		final String lisatiedot = tehtava.getLisatiedot();
		final int status = tehtava.getStatus();
		final LocalDateTime pvmaika = tehtava.getPvmaika();
		final LocalDateTime muistutusPvmaika = tehtava.getMuistutusPvmaika();
		//jdbc tallettaa generoidun id:n tänne
		KeyHolder idHolder = new GeneratedKeyHolder();
		//suoritetaan päivitys itse määritellyllä PreparedStatementCreatorilla
		//ja KeyHolderilla
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, new String[]{"t_id"});
				ps.setString(1, kuvaus);
				ps.setString(2, lisatiedot);
				ps.setInt(3, status);
				
				// MySQL-tietokannassa DATETIME-formaatti on muodossa [yyyy-mm-dd hh:mm:ss].
				// Oikea muoto Javan pvm-oliosta saadaan java.sql.Timestamp:illa ja
				// PreparedStatementin setTimestamp:illa.
				ps.setTimestamp(4, java.sql.Timestamp.valueOf(pvmaika));
				ps.setTimestamp(5, java.sql.Timestamp.valueOf(muistutusPvmaika));
				return ps;
			}
		}, idHolder);
		//tallennetaan id takaisin beaniin, koska kutsujalla pitäisi olla
		//viittaus samaiseen olioon
		tehtava.setId((idHolder.getKey().intValue()));
	}
	
	//Kaikkien teht�vien listaus
	public List<Tehtava> haeKaikki() {

		String sql = "select t_id, t_kuvaus, t_lisatiedot, t_status, t_deadlinedtm from tehtava ORDER BY t_deadlinedtm";
		RowMapper<Tehtava> mapper = new TehtavaRowMapper();
		List<Tehtava> tehtavat = jdbcTemplate.query(sql, mapper);
		return tehtavat;
	}
	
	//Teht�v�n poisto
	
	//Teht�v�n muokkaus
	
}
