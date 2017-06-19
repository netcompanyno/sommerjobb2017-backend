package no.netcompany.sommerjobb2017.user;

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
@DatabaseSetup("users.yml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest extends DaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void getByEmail_shouldReturnUser_whenQueryingExistingEmail() {
        final User user = userDao.getByEmail("test@hest.no");

        assertThat(user).isEqualTo(ImmutableUser.builder()
                .id(1)
                .email("test@hest.no")
                .name("Hest Hestesen")
                .build());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getByEmail_shouldThrowException_whenQueryingNonexistentEmail() {
        userDao.getByEmail("vest@hest.no");
    }
}