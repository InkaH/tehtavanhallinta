package dao;

import java.util.List;

import bean.Task;
import bean.Comment;
import bean.User;

public interface TaskDAO {
	
	public abstract void addTask(Task task, String user);
	
	public abstract void deleteTask(int id);
	
	public abstract void shareTask(int id, String group);
	
	public abstract List<Task> getAll(String user); 
	
	public abstract void addComment(Comment c);
	
	public abstract void deleteComment(int id);
	
	public abstract List<Comment> getComments(int task);
	
	public void saveUser(User user);
		
	public boolean searchUser(String username);
	
	public int getTheme(String user);
	
	public void saveTheme(String user, int theme);
	
}
