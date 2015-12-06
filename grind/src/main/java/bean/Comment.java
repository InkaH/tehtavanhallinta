package bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Comment implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;
	private String comment;
	@DateTimeFormat(pattern = "d.M.yyyy")
	private LocalDate date;
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime time;
	private int task;
	private String user;
	@DateTimeFormat(pattern = "d.M.yyyy HH:mm")
	private LocalDateTime created;
	
	public Comment() {
		super();
		this.id = 0;
		this.comment = "";
		this.user = "";
		this.date = null;
		this.time = null;
		this.created = null;
	}

	public Comment(int id, String text, String user, LocalDate date, LocalTime time, LocalDateTime created) {
		super();
		this.id = id;
		this.comment = text;
		this.user = user;
		this.date = date;
		this.time = time;
		this.created = created;
	}
	
	public Date getTimestamp(){
		return Timestamp.valueOf(getDatetime());
	}
	
	public LocalDateTime getCreated() {
		return created;
	}
	
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	
	public LocalDateTime getDatetime() {
		return this.date.atTime(this.time);
	}

	public void setDatetime(LocalDateTime datetime) {
		this.date = datetime.toLocalDate();
		this.time = datetime.toLocalTime();
	}

	public int getTask() {
		return task;
	}
	
	public void setTask(int task) {
		this.task = task;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}
}
