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

import bean.User;
import bean.Comment;
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
			final String sql_2 = "INSERT INTO Usertask(ut_task, ut_user) VALUES (?, ?)";
			Object[] parameters = new Object[] {task_id, user};
			jdbcTemplate.update(sql_2, parameters);
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


	public List<Task> getAllPrivate(String username) {
		// final String sql = "SELECT t_id, t_task, t_info, t_done, t_expire, t_group, ut_user FROM Usertask INNER JOIN Task on ut_task=t_id WHERE ut_user=? OR (t_group > '' AND t_group IS NOT NULL) ORDER BY t_expire";
		final String sql = "SELECT t_id, t_task, t_info, t_done, t_expire, t_group, ut_user FROM Usertask INNER JOIN Task on ut_task=t_id WHERE ut_user=? ORDER BY t_expire";
		RowMapper<Task> mapper = new TaskRowMapper();
		List<Task> tasks = jdbcTemplate.query(sql, new Object[] {username}, mapper);
		return tasks;
	}
	
	public List<Task> getAllShared(String username) {
		final String sql = "SELECT t_id, t_task, t_info, t_done, t_expire, t_group, ut_user FROM Usertask INNER JOIN Task on ut_task=t_id WHERE t_group > '' AND t_group IS NOT NULL ORDER BY t_expire";
		RowMapper<Task> mapper = new TaskRowMapper();
		List<Task> tasks = jdbcTemplate.query(sql, mapper);
		return tasks;
	}

	public void addComment(Comment c) {
		final String sql = "INSERT INTO Comment(c_comment, c_datetime, c_task, c_user) VALUES (?, ?, ?, ?)";
		Object[] parameters = new Object[] {c.getComment(), Timestamp.valueOf(c.getDatetime()), c.getTask(), c.getUser()};
		jdbcTemplate.update(sql, parameters);
	}

	public void deleteComment(int id) {
		final String sql = "DELETE FROM Comment where c_id=?";
		final int index = id;
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setInt(1, index);
				return ps;
			}
		});
	}

	public List<Comment> getComments(int task) {
		String sql = "SELECT * FROM Comment WHERE c_task=?";
		RowMapper<Comment> mapper = new CommentRowMapper();
		List<Comment> comments = jdbcTemplate.query(sql, new Object[] {task}, mapper);
		return comments;
	}

	public void saveUser(User user) {
		String sql = "INSERT INTO User(u_user, u_password, u_role) VALUES (?, ?, ?)";
		Object[] parameters = new Object[] { user.getUsername(), user.getPassword(), user.getRole() };
		jdbcTemplate.update(sql, parameters);
	}
	
	public int getTheme(String user) {
		String sql = "SELECT u_theme FROM User WHERE u_user=?";
		String th = jdbcTemplate.queryForObject(sql, new Object[] {user}, String.class);
		if(th.isEmpty() || th.equals("") || th.equalsIgnoreCase("null")) {
			return 1;
		}
		return Integer.parseInt(th);
	}
	
	public void saveTheme(String user, int themeID) {
		String sql = "UPDATE User SET u_theme=? WHERE u_user=?";
		Object[] parameters = new Object[] {themeID, user};
		jdbcTemplate.update(sql, parameters);
	}

	public boolean searchUser(String username) {
		String sql = "select COUNT(u_user) FROM User WHERE u_user = ?";
		Object[] parameters = new Object[] { username };
		int count = 0;
		count = jdbcTemplate.queryForObject(sql, parameters, Integer.class);
		if(count == 0){
			return false;
		}
		else return true;

	}

}
