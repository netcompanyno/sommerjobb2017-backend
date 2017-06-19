package no.netcompany.sommerjobb2017.secret;

import no.netcompany.sommerjobb2017.user.User;
import no.netcompany.sommerjobb2017.user.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/secret")
public class SecretRest {
    private final SecretService secretService;
    private final UserService userService;

    public SecretRest(final SecretService secretDao, final UserService userService) {
        Objects.requireNonNull(userService);
        this.userService = userService;
        Objects.requireNonNull(secretDao);
        this.secretService = secretDao;
    }

    @RequestMapping
    public Secret getSecret() {
        final User user = userService.getSignedInUser();
        return secretService.getSecretForUser(user);
    }
}
