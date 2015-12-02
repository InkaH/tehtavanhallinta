package dao;

import java.util.List;

import bean.Task;

public interface TaskDAO {
	
	public abstract void addTask(Task task, String user);
	
	public abstract void deleteTask(int id);
	
	public abstract void shareTask(int id, String group);
	
	public abstract List<Task> getAll(String user); 
	
	public void addComment(int id, String text);
}
