package dao;

import java.util.List;

import bean.Tehtava;

public interface TehtavaDao {
	
	public abstract void lisaaTehtava(Tehtava tehtava);
	
	public abstract void poistaTehtava(int id);
	
	public abstract void jaaTehtava(int id);
	
	public abstract  List<Tehtava> haeKaikki(); 
}
