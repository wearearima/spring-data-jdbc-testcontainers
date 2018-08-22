package eu.arima.springdatajdbctestcontainers;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = AccountRepositoryTest.Initializer.class)
@JdbcTest
public class AccountRepositoryTest {

    @ClassRule
    public static PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer();

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void findAll() {
        Iterable<Account> accounts = this.accountRepository.findAll();
        Assert.assertTrue(accounts.iterator().hasNext());
    }

    /**
     * This class replaces the embedded database with a PostgreSQL Docker Container
     */
    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgresqlContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgresqlContainer.getUsername(),
                    "spring.datasource.password=" + postgresqlContainer.getPassword())
                    .applyTo(configurableApplicationContext.getEnvironment());
        }

    }

}
