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

    public Secret get(final int userId) {
        return DataAccessUtils.requiredSingleResult(
                namedTemplate.query(
                        "SELECT * FROM secrets WHERE user_id = :user_id",
                        new MapSqlParameterSource()
                                .addValue("user_id", userId),
                        (rs, i) -> ImmutableSecret.builder()
                                .secret(rs.getString("secret"))
                                .build()));
    }

    public void create(final int userId) {
        namedTemplate.update(
                "INSERT INTO secrets (user_id, secret) VALUES (:user_id, '')",
                new MapSqlParameterSource()
                        .addValue("user_id", userId));
    }

    public void change(final int userId, final Secret secret) {
        namedTemplate.update(
                "UPDATE secrets SET secret = :secret WHERE user_id = :user_id",
                new MapSqlParameterSource()
                        .addValue("user_id", userId)
                        .addValue("secret", secret.getSecret()));
    }
}
