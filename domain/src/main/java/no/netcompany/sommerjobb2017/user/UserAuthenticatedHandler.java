package no.netcompany.sommerjobb2017.user;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Knut Esten Melandsø Nekså
 */
@Component
public class UserAuthenticatedHandler implements AuthenticationSuccessHandler {
    private final UserService userService;

    public UserAuthenticatedHandler(final UserService userService) {
        Objects.requireNonNull(userService);
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request,
                                        final HttpServletResponse response,
                                        final Authentication authentication) throws IOException, ServletException {
        try {
            userService.getByEmail(authentication.getName());
        } catch (final EmptyResultDataAccessException ex) {

            userService.create(ImmutableOAuth2User.builder()
                    .email(authentication.getName())
                    .name(getFullNameFromToken(authentication))
                    .build());
        }

        response.sendRedirect("/");
    }

    private String getFullNameFromToken(final Authentication authentication) {
        try {
            final String token = ((OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue();
            final JsonNode claims = new ObjectMapper().readValue(JwtHelper.decode(token).getClaims(), JsonNode.class);
            return claims.get("full_name").asText();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
