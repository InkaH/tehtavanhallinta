package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import bean.Comment;

public class CommentRowMapper implements RowMapper<Comment> {

	public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
		Comment c = new Comment();
		c.setId(rs.getInt("c_id"));
		c.setComment(rs.getString("c_comment"));
		c.setDatetime(rs.getTimestamp("c_datetime").toLocalDateTime());		
		c.setTask(rs.getInt("c_task"));
		c.setUser(rs.getString("c_user"));
		c.setCreated(rs.getTimestamp("c_created").toLocalDateTime());
		return c;
	}	
}
