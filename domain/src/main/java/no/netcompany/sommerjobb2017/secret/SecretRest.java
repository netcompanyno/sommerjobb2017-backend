package no.netcompany.sommerjobb2017.secret;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import no.netcompany.sommerjobb2017.secret.models.Secret;

import java.util.Objects;

@RestController
@RequestMapping("/api/secret")
public class SecretRest {
    private final SecretService secretService;

    public SecretRest(final SecretService secretDao) {
        Objects.requireNonNull(secretDao);
        this.secretService = secretDao;
    }

    @RequestMapping(produces = "application/json")
//    @Secured("ROLE_READ")
    public Secret getSecret(OAuth2Authentication auth) {
        final String token = ((OAuth2AuthenticationDetails) auth.getDetails()).getTokenValue();
        System.out.println(token);
        return new Secret("Jeg er så glad i dyr");
    }
}
