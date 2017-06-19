package no.netcompany.sommerjobb2017.secret;

import no.netcompany.sommerjobb2017.user.User;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author Knut Esten Melandsø Nekså
 */
@Service
public class SecretService {
    private final SecretDao secretDao;

    public SecretService(final SecretDao secretDao) {
        Objects.requireNonNull(secretDao);
        this.secretDao = secretDao;
    }

    public Secret getSecretForUser(final User user) {
        return secretDao.get(user.getId());
    }
}
