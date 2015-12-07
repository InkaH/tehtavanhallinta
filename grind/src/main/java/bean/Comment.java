package bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
	
	public Comment() {
		super();
		this.id = 0;
		this.comment = "";
		this.user = "";
		this.date = null;
		this.time = null;
	}

	public Comment(int id, String text, String user, LocalDate date, LocalTime time, LocalDate createdDate, LocalTime createdTime) {
		super();
		this.id = id;
		this.comment = text;
		this.user = user;
		this.date = date;
		this.time = time;
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
