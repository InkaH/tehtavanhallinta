package exception;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
					throws IOException, ServletException {
		super.onAuthenticationFailure(request, response, exception);

		if (exception.getClass().isAssignableFrom(
				UsernameNotFoundException.class)) {
			// TODO Set the redirect
		}
		else if (exception.getClass().isAssignableFrom(
				DataAccessException.class)) {
			// TODO Set the redirect
		}
	}
}


