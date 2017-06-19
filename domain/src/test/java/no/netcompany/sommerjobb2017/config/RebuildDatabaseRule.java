package no.netcompany.sommerjobb2017.config;

import org.flywaydb.core.Flyway;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RebuildDatabaseRule implements MethodRule {
    private final Flyway flyway;

    @Autowired
    public RebuildDatabaseRule(final Flyway flyway) {
        Objects.requireNonNull(flyway);
        this.flyway = flyway;
    }

    @Override
    public Statement apply(final Statement statement, final FrameworkMethod frameworkMethod, final Object o) {
        flyway.clean();
        flyway.migrate();
        return statement;
    }
}
