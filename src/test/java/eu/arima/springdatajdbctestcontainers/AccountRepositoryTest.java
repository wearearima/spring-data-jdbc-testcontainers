package eu.arima.springdatajdbctestcontainers;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Date;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Autowired
    DataSource dataSource;

    @Test
    public void findById() {
        Account account = this.accountRepository.findById(1).get();

        Assert.assertEquals("user1", account.getUsername());
        Assert.assertEquals("user1@company.com", account.getEmail());
        System.out.println(account.getCreated());
    }

    @Test
    public void save() {
        Account account = new Account();
        account.setName("new");
        account.setUsername("newusername");
        account.setEmail("newemail@company.com");
        account.setCreated(new Date());

        Account newAccount = this.accountRepository.save(account);

        Assert.assertEquals(1, JdbcTestUtils.countRowsInTableWhere(
                (JdbcTemplate) template.getJdbcOperations(), "account", "id = " + newAccount.getId()
        ));
    }

    @Ignore("Paging repositories not supported https://jira.spring.io/browse/DATAJDBC-101")
    @Test
    public void pageable() {
        Pageable pageable = PageRequest.of(0, 20);
        Page<Account> accounts = this.accountRepository.findAll(pageable);
        Assert.assertEquals(3, accounts.getSize());
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

}
