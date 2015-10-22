package bean;

import java.util.Date;

public class TehtavaImpl implements Tehtava {
	
	private int id;
	private String kuvaus;
	private String tiedot;
	private int status; // 0 = Ei tehty, 1 = Tehty
	private Date ajankohta;
	private Date muistutus;
	
	public TehtavaImpl() {
		this.id = 0;
		this.kuvaus = "";
		this.tiedot = "";
		this.status = 0;
		this.ajankohta = null;
		this.muistutus = null;
	}

	public TehtavaImpl(int id, String kuvaus, String tiedot, int status, Date ajankohta, Date muistutus) {
		this.id = id;
		this.kuvaus = kuvaus;
		this.tiedot = tiedot;
		this.status = status;
		this.ajankohta = ajankohta;
		this.muistutus = muistutus;
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

	public Date getAjankohta() {
		return ajankohta;
	}

	public void setAjankohta(Date ajankohta) {
		this.ajankohta = ajankohta;
	}

	public Date getMuistutus() {
		return muistutus;
	}

	public void setMuistutus(Date muistutus) {
		this.muistutus = muistutus;
	}

	@Override
	public String toString() {
		return "TehtavaImpl [id=" + id + ", kuvaus=" + kuvaus + ", tiedot=" + tiedot + ", status=" + status
				+ ", ajankohta=" + ajankohta + ", muistutus=" + muistutus + "]";
	}
}
