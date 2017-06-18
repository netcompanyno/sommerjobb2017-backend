package no.netcompany.sommerjobb2017.secret;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
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
    public String getSecret(Principal principal) {
        System.out.println(principal.getName());
        return "jeg er så glad i dyr";
    }
}
