package no.netcompany.sommerjobb2017.secret;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class SecretDao {
    private final NamedParameterJdbcTemplate namedTemplate;

    public SecretDao(final NamedParameterJdbcTemplate namedTemplate) {
        Objects.requireNonNull(namedTemplate);
        this.namedTemplate = namedTemplate;
    }

    public void create(final String email) {
        namedTemplate.update(
                "INSERT INTO secret (email) VALUES (:email)",
                new MapSqlParameterSource().addValue("email", email));
    }

    public void edit(final String email, final Secret secret) {
        namedTemplate.update(
                "UPDATE secret SET secret = :secret WHERE email = :email",
                new MapSqlParameterSource()
                        .addValue("secret", secret.getSecret())
                        .addValue("email", email));
    }

    public Secret get(final String email) {
        return DataAccessUtils.requiredSingleResult(
                namedTemplate.query(
                        "SELECT * FROM secret WHERE email = :email",
                        new MapSqlParameterSource()
                                .addValue("email", email),
                        (rs, i) -> ImmutableSecret
                                .builder()
                                .secret(rs.getString("secret"))
                                .build()));
    }
}
