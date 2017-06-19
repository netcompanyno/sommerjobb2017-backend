package no.netcompany.sommerjobb2017.config;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * @author Knut Nekså
 *
 * Samler config for database-testing her
 */
@ContextConfiguration(classes = Config.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class})
public interface DaoTestInterface {
}
