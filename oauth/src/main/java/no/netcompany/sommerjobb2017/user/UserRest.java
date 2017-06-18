package no.netcompany.sommerjobb2017.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Knut Esten Melandsø Nekså
 */
@RestController
@RequestMapping("/user")
public class UserRest {
    @RequestMapping
    public Principal user(Principal user) {
        return user;
    }
}
