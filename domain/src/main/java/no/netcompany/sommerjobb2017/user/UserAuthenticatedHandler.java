package no.netcompany.sommerjobb2017.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Knut Esten Melandsø Nekså
 */
@Component
public class UserAuthenticatedHandler implements AuthenticationSuccessHandler {
    // TODO: TASK 1.3
    // When a user that does not exist in our database authenticates:
    //   1. Create the user in the database
    //   2. Create an empty connected to the user

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request,
                                        final HttpServletResponse response,
                                        final Authentication authentication) throws IOException, ServletException {

    }
}
