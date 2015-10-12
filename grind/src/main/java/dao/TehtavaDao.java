package dao;

import java.util.List;

import bean.Tehtava;

public interface TehtavaDao {
	
	public abstract void lisaaTehtava(Tehtava tehtava);
	
	public abstract  List<Tehtava> haeKaikki(); 
}
