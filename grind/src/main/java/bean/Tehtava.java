package bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface Tehtava {
	
	public abstract int getId();
	
	public abstract void setId(int id);
	
	public abstract String getKuvaus();
	
	public abstract void setKuvaus(String kuvaus);
	
	public abstract String getLisatiedot();
	
	public abstract void setLisatiedot(String lisatiedot);
	
	public abstract int getStatus();
	
	public abstract void setStatus(int status);
	
	public abstract LocalDateTime getPvmaika();
	
	public abstract void setPvmaika(LocalDateTime pvmaika);
	
	public abstract LocalDate getPvm();
	
	public abstract void setPvm(LocalDate pvm);
	
	public abstract LocalTime getAika();
	
	public abstract void setAika(LocalTime aika);
	
	public abstract LocalDateTime getMuistutusPvmaika();
	
	public abstract void setMuistutusPvmaika(LocalDateTime pvmaika);

}
