package exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

//Luokka autentikointiin liittyvien virheiden käsittelyyn, tulee käyttöön jos tulee (ei käytössä tällä hetkellä)
public class CustomAuthenticationFailureHandler extends
		SimpleUrlAuthenticationFailureHandler {
	private DefaultRedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private String defaultFailureUrl = "/error";

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		this.setDefaultFailureUrl(defaultFailureUrl);
		if (exception.getClass().isAssignableFrom(
				UsernameNotFoundException.class)) {
			this.setDefaultFailureUrl("/login");
			this.setUseForward(true);
			this.saveException(request, exception);
			this.setRedirectStrategy(redirectStrategy);
			redirectStrategy.sendRedirect(request, response,
					this.defaultFailureUrl);
		} else if (exception.getClass().isAssignableFrom(
				DataAccessException.class)) {
			this.setDefaultFailureUrl("/error");
			this.setUseForward(true);
			this.saveException(request, exception);
			this.setRedirectStrategy(redirectStrategy);
			redirectStrategy.sendRedirect(request, response,
					this.defaultFailureUrl);
		}
	}
}