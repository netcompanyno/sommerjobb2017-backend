package no.netcompany.sommerjobb2017.secret;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import no.netcompany.sommerjobb2017.config.DaoTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Knut Esten Melandsø Nekså
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DatabaseSetup("secrets.yml")
public class SecretDaoTest extends DaoTest {
    @Autowired
    private SecretDao secretDao;

    @Test
    public void get_shouldReturnSecret_whenSecretExistsForUserId() {
        final Secret secret = secretDao.get(1);

        assertThat(secret).isEqualTo(ImmutableSecret.builder()
                .secret("Jeg er så glad i dyr")
                .build());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void get_shouldThrowException_whenSecretDoesNotExistsForUserId() {
        secretDao.get(2);
    }
}