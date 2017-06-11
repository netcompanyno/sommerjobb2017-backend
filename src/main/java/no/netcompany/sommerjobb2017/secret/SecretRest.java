package no.netcompany.sommerjobb2017.secret;

import no.netcompany.sommerjobb2017.SparkService;
import no.netcompany.sommerjobb2017.user.User;
import no.netcompany.sommerjobb2017.util.JsonUtil;
import org.eclipse.jetty.server.Response;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static spark.Spark.*;

@Component
public class SecretRest implements SparkService {
    private final SecretService secretService;
    private final JsonUtil jsonUtil;

    public SecretRest(final SecretService secretDao, final JsonUtil jsonUtil) {
        Objects.requireNonNull(secretDao);
        this.secretService = secretDao;
        Objects.requireNonNull(jsonUtil);
        this.jsonUtil = jsonUtil;
    }

    @Override
    public void init() {
        before("/secret", (req, res) -> {
            final User user = req.session().attribute("user");
            if (user == null) {
                halt(401, "You must sign in to see your secret.");
            }
        });

        get("/api/secret", (req, res) -> {
            final User user = req.session().attribute("user");
            return secretService.getSecretForUser(user);
        }, jsonUtil.toJson());

        put("/api/secret", (req, res) -> {
            final User user = req.session().attribute("user");
            final Secret secret = jsonUtil.fromJson(req.body(), Secret.class);
            secretService.edit(user, secret);
            res.status(Response.SC_NO_CONTENT);
            return res;
        });
    }
}
