package no.netcompany.sommerjobb2017.config;

import org.junit.Rule;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class DaoTest implements DaoTestInterface {
    @Rule
    @Autowired
    public RebuildDatabaseRule rebuildDatabaseRule;
}
