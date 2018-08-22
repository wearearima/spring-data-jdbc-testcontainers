package eu.arima.springdatajdbctestcontainers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountRepositoryTest {

    @Autowired AccountRepository accountRepository;

    @Test
    public void findAll() {
        Iterable<Account> accounts = this.accountRepository.findAll();
        Assert.assertTrue(accounts.iterator().hasNext());
    }

}
