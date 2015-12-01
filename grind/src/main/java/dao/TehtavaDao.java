package dao;

import java.util.List;

import bean.Tehtava;

public interface TehtavaDao {
	
	public abstract void lisaaTehtava(Tehtava tehtava, String username);
	
	public abstract void poistaTehtava(int id);
	
	public abstract void jaaTehtava(int id, String group);
	
	public abstract  List<Tehtava> haeKaikki(String username); 
	
	public void lisaaKommentti(int taskID, String concatText);
}
