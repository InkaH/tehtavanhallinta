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

import bean.Task;

import java.util.List;

@Component
public class TaskDaoImpl implements TaskDAO {

	@Inject
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void addTask(Task task, String user) {
		final String sql_1 = "INSERT INTO Task(t_id, t_task, t_info, t_done, t_expire, t_group, t_user) values(?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE t_task=?, t_info=?, t_done=?, t_expire=?, t_group=?";
		
		final int idDB = task.getId();
		final String taskDB = task.getTask();
		final String infoDB = task.getInfo();
		final int statusDB = task.getDone();
		final LocalDateTime datetimeDB = task.getDatetime();
		final String groupDB = task.getGroup();
		final String userDB = user;
		KeyHolder idHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql_1);
				if (idDB == 0) {
					ps.setString(1, null);
				} else {
					ps.setInt(1, idDB);
				}
				ps.setString(2, taskDB);
				ps.setString(3, infoDB);
				ps.setInt(4, statusDB);
				ps.setTimestamp(5, Timestamp.valueOf(datetimeDB));
				ps.setString(6, groupDB);
				ps.setString(7, userDB);
				// replacement values
				ps.setString(8, taskDB);
				ps.setString(9, infoDB);
				ps.setInt(10, statusDB);
				ps.setTimestamp(11, Timestamp.valueOf(datetimeDB));
				ps.setString(12, groupDB);
				
				return ps;
			}
		}, idHolder);
		int task_id = (idHolder.getKey().intValue());
		if(idDB == 0){
			final String sql_2 = "INSERT INTO kayttajan_tehtava(kt_t_id, kt_k_nimi) VALUES (?, ?)";
			Object[] parameters = new Object[] {task_id, user};
			getJdbcTemplate().update(sql_2, parameters);
		}	
	}

	public void deleteTask(int id) {
		final String sql = "DELETE FROM Task where t_id=?";
		final int index = id;
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setInt(1, index);
				return ps;
			}
		});
	}
	
	public void shareTask(int id, String groupID) {
		final String sql = "UPDATE Task SET t_shared=1, t_group=? where t_id=?";
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
	
	public void addComment(int id, String text) {
		final String sql = "UPDATE Task SET t_info=? where t_id=?";
		final int idDB = id;
		final String textDB = text;
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1, textDB);
				ps.setInt(2, idDB);
				return ps;
			}
		});
	}

	public List<Task> getAll(String username) {
		String sql = "SELECT t_id, t_task, t_info, t_done, t_expire, t_group, ut_user FROM Usertask INNER JOIN Task on ut_task=t_id WHERE ut_user=? ORDER BY t_expire";
		RowMapper<Task> mapper = new TaskRowMapper();
		List<Task> tehtavat = jdbcTemplate.query(sql, new Object[] {username}, mapper);
		return tehtavat;
	}
}
