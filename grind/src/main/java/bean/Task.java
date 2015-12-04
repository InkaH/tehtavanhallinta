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
	private int done; // 0 = Ei tehty, 1 = Tehty
	@DateTimeFormat(pattern = "d.M.yyyy")
	private LocalDate date;
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime time;
	private String group;
	private boolean shared;
	private String user;

	public Task() {
		this.id = 0;
		this.task = "";
		this.done = 0;
		this.date = null;
		this.time = null;
		this.group = "";
		this.shared = false;
		this.user = "";
	}

	public Task(int id, String task, String info, int done, LocalDateTime datetime, String group, boolean shared, String user) {
		this.id = id;
		this.task = task;
		this.done = done;
		this.date = datetime.toLocalDate();
		this.time = datetime.toLocalTime();
		this.group = group;
		this.shared = shared;
		this.user = user;
	}
	
	public Date getTimestamp(){
		return Timestamp.valueOf(getDatetime());
	}

	public void resetTask() {
		this.id = 0;
		this.task = "";
		this.done = 0;
		this.date = null;
		this.time = null;
		this.shared = false;
		this.group = "";
		this.user = "";
	}
	
	public boolean getShared() {
		return shared;
	}
	
	public void setShared(boolean shared) {
		this.shared = shared;
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
