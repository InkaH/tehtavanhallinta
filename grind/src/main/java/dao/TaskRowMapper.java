package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import bean.Task;

public class TaskRowMapper implements RowMapper<Task> {

	public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
		Task task = new Task();
		task.setId(rs.getInt("t_id"));
		task.setTask(rs.getString("t_task"));
		task.setInfo(rs.getString("t_info"));
		task.setDone(rs.getInt("t_done"));
		task.setDatetime(rs.getTimestamp("t_expire").toLocalDateTime());
		task.setGroup(rs.getString("t_group"));
		task.setUser(rs.getString("ut_user"));
		return task;
	}
}