package no.netcompany.sommerjobb2017.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.netcompany.sommerjobb2017.user.ImmutableUser;
import no.netcompany.sommerjobb2017.user.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Convenience service for getting user object for the currently signed in user.
 *
 * @author Knut Esten Melandsø Nekså
 */
@Service
public class UserService {

    public User getSignedInUser() {
        final OAuth2Authentication auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        final String token = ((OAuth2AuthenticationDetails) auth.getDetails()).getTokenValue();

        try {
            final JsonNode claims = new ObjectMapper().readValue(JwtHelper.decode(token).getClaims(), JsonNode.class);
            final String fullName = claims.get("full_name").asText();

            return ImmutableUser.builder()
                    .name(fullName)
                    .email(auth.getName())
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
