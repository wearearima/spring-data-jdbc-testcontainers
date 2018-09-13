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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Arrays;
import java.util.Date;

@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = AccountRepositoryTest.Initializer.class)
@JdbcTest
public class AccountRepositoryTest {

    @ClassRule
    public static PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer();

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Test
    public void findAll() {
        Account account = this.accountRepository.findById(1).get();

        Assert.assertEquals("user1", account.getUsername());
        Assert.assertEquals("user1@company.com", account.getEmail());
        System.out.println(account.getCreated());
    }

    @Test
    public void save() {
        int newId = 100;

        Account account = new Account();
        account.setId(newId);
        account.setName("new");
        account.setUsername("newusername");
        account.setEmail("newemail@company.com");
        account.setCreated(new Date());

        this.accountRepository.save(account);

        Assert.assertEquals(1, JdbcTestUtils.countRowsInTableWhere(
                (JdbcTemplate) template.getJdbcOperations(), "account", "id = " + account.getId()
        ));
    }

    @Test
    public void pageable() {
        Pageable pageable = PageRequest.of(0, 20);
        Page<Account> accounts = this.accountRepository.findAll(pageable);
        Assert.assertEquals(2, accounts.getSize());
    }

    @Test
    public void length() {
        int length = this.accountRepository.accountsLength();

        Assert.assertEquals(3, length);
    }

    @Test
    public void updateName() {
        boolean updated = this.accountRepository.updateName(3, "updatedname");
        Assert.assertTrue(updated);
    }

    @Test
    public void deleteList() {
        this.accountRepository.deleteIdList(Arrays.asList(1, 2));

        int length = this.accountRepository.accountsLength();

        Assert.assertEquals(1, length);
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
