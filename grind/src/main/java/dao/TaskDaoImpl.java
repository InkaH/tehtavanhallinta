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
		final String sql_1 = "INSERT INTO Task(t_id, t_task, t_expire, t_group, t_shared, t_user) values(?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE t_task=?,t_expire=?, t_group=?, t_shared=?";

		final int idDB = task.getId();
		final String taskDB = task.getTask();
		final LocalDateTime datetimeDB = task.getDatetime();
		final String groupDB = task.getGroup();
		// Notice! Boolean converted to Int
		final int sharedDB = (task.getShared() ? 1 : 0);
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
				ps.setTimestamp(3, Timestamp.valueOf(datetimeDB));
				ps.setString(4, groupDB);
				ps.setInt(5, sharedDB);
				ps.setString(6, userDB);
				// replacement values
				ps.setString(7, taskDB);
				ps.setTimestamp(8, Timestamp.valueOf(datetimeDB));
				ps.setString(9, groupDB);
				ps.setInt(10, sharedDB);
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

	public void shareTask(int id, String groupID, boolean status) {
		final String sql = "UPDATE Task SET t_shared=?, t_group=? where t_id=?";
		final boolean shareStatus = status;
		final int index = id;
		final String group = groupID;
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setInt(1, (shareStatus ? 1 : 0));
				ps.setString(2, group);
				ps.setInt(3, index);
				return ps;
			}
		});
	}

	public List<Task> getAllTasks() {
		final String sql = "SELECT t_id, t_task, t_expire, t_group, t_shared, t_user, t_created FROM Task ORDER BY t_expire";
		RowMapper<Task> mapper = new TaskRowMapper();
		List<Task> tasks = jdbcTemplate.query(sql, mapper);
		return tasks;
	}
	
	public List<Task> getAllPrivate(String user) {
		final String sql = "SELECT ta.t_id, ta.t_task, ut.ut_done, ta.t_expire, ta.t_group, ta.t_shared, ta.t_user, ut.ut_user, ta.t_created FROM Usertask AS ut "
				+ "INNER JOIN Task AS ta ON ut.ut_task=ta.t_id WHERE ut.ut_user=? AND ut.ut_done='0' ORDER BY ta.t_expire";
		RowMapper<Task> mapper = new UsertaskRowMapper();
		List<Task> tasks = jdbcTemplate.query(sql, new Object[] {user}, mapper);
		return tasks;
	}
	
	public List<Task> getAllShared() {
		final String sql = "SELECT t_id, t_task, t_expire, t_group, t_shared, t_user, t_created FROM Task WHERE (t_expire > NOW() OR t_expire='1970-01-01 23:59:00') AND t_shared='1' ORDER BY t_expire";
		RowMapper<Task> mapper = new TaskRowMapper();
		List<Task> tasks = jdbcTemplate.query(sql, mapper);
		return tasks;
	}
	
	public List<Task> getAllSharedByGroup(String group) {
		final String sql = "SELECT t_id, t_task, t_expire, t_group, t_shared, t_user, t_created FROM Task WHERE (t_expire > NOW() OR t_expire='1970-01-01 23:59:00') AND t_shared='1' AND t_group=? ORDER BY t_expire";
		RowMapper<Task> mapper = new TaskRowMapper();
		List<Task> tasks = jdbcTemplate.query(sql, new Object[] {group}, mapper);
		return tasks;
	}
	
	public List<Task> getAllDone(String user) {
		final String sql = "SELECT t_id, t_task, ut_done, t_expire, t_group, t_shared, t_user, ut_user, t_created FROM Usertask "
				+ "INNER JOIN Task on ut_task=t_id WHERE ut_user=? AND ut_done='1' ORDER BY t_expire";
		RowMapper<Task> mapper = new UsertaskRowMapper();
		List<Task> tasks = jdbcTemplate.query(sql, new Object[] {user}, mapper);
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
	
	public void setDone(int doneID, int doneValue) {
		String sql = "UPDATE Usertask SET ut_done=? WHERE ut_task=?";
		Object[] parameters = new Object[] {doneValue, doneID};
		jdbcTemplate.update(sql, parameters);
	}
	
	public void setLink(int linkedID, String linkedUser) {
		String sql = "INSERT IGNORE INTO Usertask(ut_task, ut_user) VALUES (?, ?)";
		Object[] parameters = new Object[] {linkedID, linkedUser};
		jdbcTemplate.update(sql, parameters);
	}
	
	public List<String> getGroupList() {
		String sql = "SELECT DISTINCT t_group FROM Task WHERE t_shared='1' ORDER BY t_group";
		RowMapper<String> mapper = new GroupRowMapper();
		List<String> grouplist = jdbcTemplate.query(sql, mapper);
		return grouplist;
	}
	
	public int getNumComments(int taskID) {
		String sql = "SELECT COUNT(*) FROM Comment WHERE c_task=?";
		int numComments = jdbcTemplate.queryForObject(sql, new Object[] {taskID}, Integer.class);
		return numComments;
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
