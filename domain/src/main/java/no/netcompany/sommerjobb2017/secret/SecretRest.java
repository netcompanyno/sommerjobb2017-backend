package no.netcompany.sommerjobb2017.secret;

import no.netcompany.sommerjobb2017.user.User;
import no.netcompany.sommerjobb2017.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public @ResponseBody Secret getSecret() {
        final User user = userService.getSignedInUser();
        return secretService.getSecretForUser(user);
    }

    @RequestMapping(path = "/change", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeSecret(@RequestBody final Secret secret) {
        final User user = userService.getSignedInUser();
        secretService.changeSecret(user.getId(), secret);
    }
}
