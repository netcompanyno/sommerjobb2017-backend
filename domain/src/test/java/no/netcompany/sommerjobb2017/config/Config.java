package no.netcompany.sommerjobb2017.config;

import com.github.springtestdbunit.dataset.AbstractDataSetLoader;
import com.github.springtestdbunit.dataset.DataSetLoader;
import no.netcompany.sommerjobb2017.App;
import org.dbunit.dataset.IDataSet;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Properties;

import static org.springframework.context.annotation.ComponentScan.Filter;
import static org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE;

@Configuration
@SpringBootApplication
@ComponentScan(
        basePackages = "no.netcompany.sommerjobb2017",
        excludeFilters = {
                @Filter(type = ASSIGNABLE_TYPE, value = OAuth2ClientConfig.class),
                @Filter(type = ASSIGNABLE_TYPE, value = App.class)
        })
public class Config {
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "";
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:mem:testDb;DB_CLOSE_DELAY=-1;MODE=PostgreSQL";

    @Bean
    public DataSetLoader dbUnitDataSetLoader() {
        return new AbstractDataSetLoader() {
            @Override
            protected IDataSet createDataSet(final Resource resource) throws Exception {
                return new YamlDataSet(resource.getFile());
            }
        };
    }

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setDriverClassName(DB_DRIVER);
        datasource.setUsername(DB_USERNAME);
        datasource.setPassword(DB_PASSWORD);
        datasource.setUrl(DB_URL);
        return datasource;
    }

    @Bean
    public FlywayMigrationStrategy flywayMigrationStrategy() {
        return flyway -> {
        };
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        final Properties properties = new Properties();
        properties.setProperty("server.url", "local.host");
        final PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer
                = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setProperties(properties);
        return propertySourcesPlaceholderConfigurer;
    }
}