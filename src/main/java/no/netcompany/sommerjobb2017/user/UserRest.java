package no.netcompany.sommerjobb2017.user;

import no.netcompany.sommerjobb2017.SparkService;
import no.netcompany.sommerjobb2017.util.JsonUtil;
import org.eclipse.jetty.server.Response;
import org.springframework.stereotype.Component;
import spark.Session;

import java.util.Objects;

import static spark.Spark.*;

@Component
public class UserRest implements SparkService {
    private final UserService userService;
    private final JsonUtil jsonUtil;

    public UserRest(final UserService userService, final JsonUtil jsonUtil) {
        Objects.requireNonNull(jsonUtil);
        this.jsonUtil = jsonUtil;
        Objects.requireNonNull(userService);
        this.userService = userService;
    }

    @Override
    public void init() {
        post("/api/auth/login", (req, res) -> {
            final Credentials credentials = jsonUtil.fromJson(req.body(), Credentials.class);
            final User user = userService.authenticateAndReturn(credentials);
            if (user == null) {
                halt(Response.SC_UNAUTHORIZED, "Invalid credentials.");
            }

            final Session session = req.session(true);
            session.attribute("user", user);
            res.status(Response.SC_NO_CONTENT);
            return res;
        });

        post("/api/auth/logout", (req, res) -> {
            req.session().invalidate();
            res.status(Response.SC_NO_CONTENT);
            return res;
        });

        get("/api/auth/user", (req, res) -> req.session().attribute("user"), jsonUtil.toJson());
    }
}
