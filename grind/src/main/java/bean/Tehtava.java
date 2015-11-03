package bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Tehtava implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String kuvaus;
	private String tiedot;
	private int status; // 0 = Ei tehty, 1 = Tehty
	@DateTimeFormat(pattern = "d.M.yyyy")
	private LocalDate ajankohtaPvm;
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime ajankohtaKlo;

	public Tehtava() {
		this.id = 0;
		this.kuvaus = "";
		this.tiedot = "";
		this.status = 0;
		this.ajankohtaPvm = null;
		this.ajankohtaKlo = null;
	}

	public Tehtava(int id, String kuvaus, String tiedot, int status, LocalDateTime ajankohta) {
		this.id = id;
		this.kuvaus = kuvaus;
		this.tiedot = tiedot;
		this.status = status;
		this.ajankohtaPvm = ajankohta.toLocalDate();
		this.ajankohtaKlo = ajankohta.toLocalTime();
	}
	
	public Date getDate(){
		return Timestamp.valueOf(getAjankohta());
	}

	public void nollaaTehtava() {
		this.id = 0;
		this.kuvaus = "";
		this.tiedot = "";
		this.status = 0;
		this.ajankohtaPvm = null;
		this.ajankohtaKlo = null;
	}

	public LocalDateTime getAjankohta() {
		return this.ajankohtaPvm.atTime(this.ajankohtaKlo);
	}

	public void setAjankohta(LocalDateTime ajankohta) {
		this.ajankohtaPvm = ajankohta.toLocalDate();
		this.ajankohtaKlo = ajankohta.toLocalTime();
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

	@Override
	public String toString() {
		return "Tehtava [id=" + id + ", kuvaus=" + kuvaus + ", tiedot=" + tiedot + ", status=" + status
				+ ", ajankohtaPvm=" + ajankohtaPvm + ", ajankohtaKlo=" + ajankohtaKlo;
	}
}
