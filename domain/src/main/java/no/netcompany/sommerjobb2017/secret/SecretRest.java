package no.netcompany.sommerjobb2017.secret;

import no.netcompany.sommerjobb2017.user.User;
import no.netcompany.sommerjobb2017.util.UserService;
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
    public User getSecret() {
        return userService.getSignedInUser();
    }
}
