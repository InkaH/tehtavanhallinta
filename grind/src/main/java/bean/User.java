package bean;

import javax.validation.constraints.Size;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class User {
	@Size(min = 1, max = 20, message = "Käyttäjänimen sallittu pituus 1-20 merkkiä.")
	private String username;
	@Size(min = 8, max = 60, message = "Salasanan sallittu pituus 8-60 merkkiä.")
	private String password;
	private String role;
	
	public User() {
		super();
	}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String sername) {
		this.username = sername;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		this.password = hashedPassword;
	}
	
	public void setEmptyPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "<br> Käyttäjänimi: " + username + "<br> Hashattu salasana: " + password
				+ "<br> rooli: " + role;
	}
}
