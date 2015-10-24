package bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface Tehtava {
	
	public abstract int getId();
	
	public abstract void setId(int id);
	
	public abstract String getKuvaus();
	
	public abstract void setKuvaus(String kuvaus);
	
	public abstract String getTiedot();
	
	public abstract void setTiedot(String tiedot);
	
	public abstract int getStatus();
	
	public abstract void setStatus(int status);
	
	public abstract LocalDateTime getAjankohta();
	
	public abstract void setAjankohta(LocalDateTime ajankohta);
	
	public abstract LocalDate getAjankohtaPvm();
	
	public abstract void setAjankohtaPvm(LocalDate ajankohtaPvm);
	
	public abstract LocalTime getAjankohtaKlo();
	
	public abstract void setAjankohtaKlo(LocalTime ajankohtaKlo);
	
	public abstract LocalDateTime getMuistutus();
	
	public abstract void setMuistutus(LocalDateTime muistutus);
	
	public abstract LocalDate getMuistutusPvm();
	
	public abstract void setMuistutusPvm(LocalDate muistutusPvm);
	
	public abstract LocalTime getMuistutusKlo();
	
	public abstract void setMuistutusKlo(LocalTime muistutusKlo);

}
