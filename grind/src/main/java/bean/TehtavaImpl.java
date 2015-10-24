package bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TehtavaImpl implements Tehtava, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String kuvaus;
	private String tiedot;
	private int status; // 0 = Ei tehty, 1 = Tehty
	private LocalDate ajankohtaPvm;
	private LocalTime ajankohtaKlo;
	private LocalDate muistutusPvm;
	private LocalTime muistutusKlo;
	
	public TehtavaImpl() {
		this.id = 0;
		this.kuvaus = "";
		this.tiedot = "";
		this.status = 0;
		LocalDateTime now = LocalDateTime.now();
		this.ajankohtaPvm = now.toLocalDate();
		this.ajankohtaKlo = now.toLocalTime();
		this.muistutusPvm = now.toLocalDate();
		this.muistutusKlo = now.toLocalTime();
	}

	public TehtavaImpl(int id, String kuvaus, String tiedot, int status, LocalDateTime ajankohta, LocalDateTime muistutus) {
		this.id = id;
		this.kuvaus = kuvaus;
		this.tiedot = tiedot;
		this.status = status;
		this.ajankohtaPvm = ajankohta.toLocalDate();
		this.ajankohtaKlo = ajankohta.toLocalTime();
		this.muistutusPvm = muistutus.toLocalDate();
		this.muistutusKlo = muistutus.toLocalTime();
	}
	
	public LocalDateTime getAjankohta() {
		return this.ajankohtaPvm.atTime(this.ajankohtaKlo);
	}

    public void setAjankohta(LocalDateTime ajankohta) {
    	this.ajankohtaPvm = ajankohta.toLocalDate();
    	this.ajankohtaKlo = ajankohta.toLocalTime();
    }
    
    public LocalDateTime getMuistutus() {
    	return this.muistutusPvm.atTime(this.muistutusKlo);
    }

    public void setMuistutus(LocalDateTime muistutus) {
    	this.muistutusPvm = muistutus.toLocalDate();
    	this.muistutusKlo = muistutus.toLocalTime();
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKuvaus() {
		return kuvaus;
	}

	public void setKuvaus(String kuvaus) {
		this.kuvaus = kuvaus;
	}

	public String getTiedot() {
		return tiedot;
	}

	public void setTiedot(String tiedot) {
		this.tiedot = tiedot;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public LocalDate getAjankohtaPvm() {
		return ajankohtaPvm;
	}

	public void setAjankohtaPvm(LocalDate ajankohtaPvm) {
		this.ajankohtaPvm = ajankohtaPvm;
	}

	public LocalTime getAjankohtaKlo() {
		return ajankohtaKlo;
	}

	public void setAjankohtaKlo(LocalTime ajankohtaKlo) {
		this.ajankohtaKlo = ajankohtaKlo;
	}

	public LocalDate getMuistutusPvm() {
		return muistutusPvm;
	}

	public void setMuistutusPvm(LocalDate muistutusPvm) {
		this.muistutusPvm = muistutusPvm;
	}

	public LocalTime getMuistutusKlo() {
		return muistutusKlo;
	}

	public void setMuistutusKlo(LocalTime muistutusKlo) {
		this.muistutusKlo = muistutusKlo;
	}
}
