package bean;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class User {
	private String knimi;
	private String salasana;
	private String rooli;
	
	public User() {
		super();
	}
	
	public User(String knimi, String salasana) {
		super();
		this.knimi = knimi;
		this.salasana = salasana;
	}

	public String getKnimi() {
		return knimi;
	}

	public void setKnimi(String knimi) {
		this.knimi = knimi;
	}

	public String getSalasana() {
		return salasana;
	}

	public void setSalasana(String salasana) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashattuSalasana = passwordEncoder.encode(salasana);
		this.salasana = hashattuSalasana;
	}
	
	public void setTyhjaSalasana(String salasana) {
		this.salasana = salasana;
	}

	public String getRooli() {
		return rooli;
	}

	public void setRooli(String rooli) {
		this.rooli = rooli;
	}

	@Override
	public String toString() {
		return "Kayttaja [knimi=" + knimi + ", salasana=" + salasana
				+ ", rooli=" + rooli + "]";
	}	
}
