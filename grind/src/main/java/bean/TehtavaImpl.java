package bean;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TehtavaImpl implements Tehtava {
	private int id;
	private String kuvaus; // Otsikko tapahtumasta, joka näkyy listassa
	private String lisatiedot; // lisätiedot tapahtumasta
	private int status; // 0 = Ei tehty, 1 = Tehty
	private LocalDateTime pvmaika; // Pvm ja aika, jolloin tapahtuma on
	private LocalDate pvm; //pelkkä pvm
	private LocalTime aika; //pelkkä aika
	private LocalDateTime muistutusPvmaika; // Pvm jolloin muistuttaa asiasta
	
	//Konstruktorit
	public TehtavaImpl() {
		this.id = 0;
		this.kuvaus = "";
		this.lisatiedot = "";
		this.status = 0;
		this.pvmaika = null;
		this.muistutusPvmaika = null;
	}

	public TehtavaImpl(int id, String kuvaus, String lisatiedot, int status, LocalDateTime pvm, LocalDateTime muistutusPvm) {
		this.id = id;
		this.kuvaus = kuvaus;
		this.lisatiedot = lisatiedot;
		this.status = status;
		this.pvmaika = pvm;
		this.muistutusPvmaika = muistutusPvm;
	}
	
	//Getit ja Setit
	public int getId() {
		return id;
	}

	public String getKuvaus() {
		return kuvaus;
	}

	public String getLisatiedot() {
		return lisatiedot;
	}

	public int getStatus() {
		return status;
	}

	public LocalDateTime getPvmaika() {
		return pvmaika;
	}
	
	public LocalDate getPvm() {
		return pvm;
	}
	
	public LocalTime getAika() {
		return aika;
	}

	public LocalDateTime getMuistutusPvmaika() {
		return muistutusPvmaika;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setKuvaus(String kuvaus) {
		this.kuvaus = kuvaus;
	}

	public void setLisatiedot(String lisatiedot) {
		this.lisatiedot = lisatiedot;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setPvmaika(LocalDateTime pvmaika) {
		this.pvmaika = pvmaika;
	}
	
	public void setPvm(LocalDate pvm){
		this.pvm = pvm;
	}
	
	public void setAika(LocalTime aika){
		this.aika = aika;
	}

	public void setMuistutusPvmaika(LocalDateTime muistutusPvmaika) {
		this.muistutusPvmaika = muistutusPvmaika;
	}

	//toString
	@Override
	public String toString() {
		return "Tehtava [id=" + id + ", kuvaus=" + kuvaus + ", lisatiedot="
				+ lisatiedot + ", status=" + status + ", pvmaika=" + pvmaika
				+ ", muistutusPvmaika=" + muistutusPvmaika + "]";
	}

}
