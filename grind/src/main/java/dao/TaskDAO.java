package dao;

import java.time.LocalDateTime;
import java.util.List;

import bean.Task;
import bean.Comment;
import bean.User;

public interface TaskDAO {
	
	public abstract void addTask(Task task, String user);
	
	public abstract void deleteTask(int id);
	
	public abstract void deletePrivateTask(int id);
	
	public abstract void shareTask(int id, String group, boolean status);
	
	public abstract List<Task> getTasksOfNextWeek(String user, LocalDateTime startOfNextWeek, LocalDateTime endOfNextWeek);
	
	public abstract List<Task> getAllPrivate(String user); 
	
	public abstract List<Task> getAllShared(); 
	
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
	
	public abstract void setDone(int task, int value);
	
	public abstract void setLink(int task, String user);
	
	public abstract int getNumComments(int task);
	
	public abstract List<Task> getAllTasks();
}
