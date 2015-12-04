package dao;

import java.util.List;

import bean.Task;
import bean.Comment;
import bean.User;

public interface TaskDAO {
	
	public abstract void addTask(Task task, String user);
	
	public abstract void deleteTask(int id);
	
	public abstract void shareTask(int id, String group, boolean status);
	
	public abstract List<Task> getAllPrivate(String user); 
	
	public abstract List<Task> getAllShared(String user); 
	
	public abstract List<Task> getAllSharedByGroup(String group);
	
	public abstract List<Task> getAllDone(String user);
	
	public abstract void addComment(Comment c);
	
	public abstract void deleteComment(int id);
	
	public abstract List<Comment> getComments(int task);
	
	public abstract void saveUser(User user);
		
	public abstract boolean searchUser(String username);
	
	public abstract int getTheme(String user);
	
	public abstract void saveTheme(String user, int theme);
	
	public abstract List<String> getGroupList();
	
	public abstract void setDone(int id, int value);
	
}
