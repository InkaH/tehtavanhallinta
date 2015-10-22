package bean;

import java.util.Date;

public interface Tehtava {
	
	public abstract int getId();
	
	public abstract void setId(int id);
	
	public abstract String getKuvaus();
	
	public abstract void setKuvaus(String kuvaus);
	
	public abstract String getTiedot();
	
	public abstract void setTiedot(String tiedot);
	
	public abstract int getStatus();
	
	public abstract void setStatus(int status);
	
	public abstract Date getAjankohta();
	
	public abstract void setAjankohta(Date ajankohta);
	
	public abstract Date getMuistutus();
	
	public abstract void setMuistutus(Date muistutus);

}
