package bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import controller.TaskController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@Component
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
 
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
    	final Logger logger = LoggerFactory.getLogger(CustomLogoutSuccessHandler.class);
        if (authentication != null && authentication.getDetails() != null) {
        	logger.info("**************************logout CustomLogoutSuccessHandlerilla onnistui************************************");        	

        	TaskController.startup = true;
        }
 
       
        //below does the 'standard' spring logout handling
        super.onLogoutSuccess(request, response, authentication);     
       
        this.setDefaultTargetUrl("/login?logout=true");
        this.setAlwaysUseDefaultTargetUrl(true);
        logger.info("Target url : " + this.getDefaultTargetUrl());
    }
}
