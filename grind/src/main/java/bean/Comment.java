package bean;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

public class Comment {

	private int id;
	private String text;
	private String user;
	@DateTimeFormat(pattern = "d.M.yyyy")
	private LocalDate date;
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime time;
	
	public Comment() {
		super();
		this.id = 0;
		this.text = "";
		this.user = "";
		this.date = null;
		this.time = null;
	}

	public Comment(int id, String text, String user, LocalDate date, LocalTime time) {
		super();
		this.id = id;
		this.text = text;
		this.user = user;
		this.date = date;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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
