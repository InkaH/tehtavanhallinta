package bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Task implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String task;
	private String info;
	private int done; // 0 = Ei tehty, 1 = Tehty
	@DateTimeFormat(pattern = "d.M.yyyy")
	private LocalDate date;
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime time;
	private String group;
	private String user;

	public Task() {
		this.id = 0;
		this.task = "";
		this.info = "";
		this.done = 0;
		this.date = null;
		this.time = null;
		this.group = "";
		this.user = "";
	}

	public Task(int id, String task, String info, int status, LocalDateTime datetime, String group, String user) {
		this.id = id;
		this.task = task;
		this.info = info;
		this.done = status;
		this.date = datetime.toLocalDate();
		this.time = datetime.toLocalTime();
		this.group = group;
		this.user = user;
	}
	
	public Date getTimestamp(){
		return Timestamp.valueOf(getDatetime());
	}

	public void resetTask() {
		this.id = 0;
		this.task = "";
		this.info = "";
		this.done = 0;
		this.date = null;
		this.time = null;
		this.group = "";
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}

	public LocalDateTime getDatetime() {
		return this.date.atTime(this.time);
	}

	public void setDatetime(LocalDateTime datetime) {
		this.date = datetime.toLocalDate();
		this.time = datetime.toLocalTime();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getDone() {
		return done;
	}

	public void setDone(int done) {
		this.done = done;
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

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}
}
