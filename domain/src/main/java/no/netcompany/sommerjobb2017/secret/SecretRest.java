package no.netcompany.sommerjobb2017.secret;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("secret")
public class SecretRest {
    private final SecretService secretService;

    public SecretRest(final SecretService secretDao) {
        Objects.requireNonNull(secretDao);
        this.secretService = secretDao;
    }

    @RequestMapping
//    @Secured("ROLE_READ")
    public String getSecret(OAuth2Authentication auth) {
        final String token = ((OAuth2AuthenticationDetails) auth.getDetails()).getTokenValue();
        System.out.println(token);
        return "jeg er så glad i dyr";
    }
}
