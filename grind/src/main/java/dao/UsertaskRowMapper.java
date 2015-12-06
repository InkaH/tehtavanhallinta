package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import bean.Task;

public class UsertaskRowMapper implements RowMapper<Task> {

	public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
		Task task = new Task();
		task.setId(rs.getInt("t_id"));
		task.setTask(rs.getString("t_task"));
		task.setDone(rs.getInt("ut_done"));
		task.setDatetime(rs.getTimestamp("t_expire").toLocalDateTime());
		task.setGroup(rs.getString("t_group"));
		task.setShared(rs.getString("t_shared").equals("0") ? false : true);
		task.setUser(rs.getString("t_user")); // original maker of the task
		task.setActiveUser(rs.getString("ut_user"));
		task.setCreatedDatetime(rs.getTimestamp("t_created").toLocalDateTime());
		return task;
	}
}