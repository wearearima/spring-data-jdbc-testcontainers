package eu.arima.springdatajdbctestcontainers.singlentonContainer;

import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import eu.arima.springdatajdbctestcontainers.Account;
import eu.arima.springdatajdbctestcontainers.AccountRepository;

@Transactional
@SpringBootTest
public class AccountRepositoryFindByIdTest extends AbstractContainerBaseTest {

	@Autowired
	private AccountRepository accountRepository;


	@Test
	public void findByIdForExistingIdReturnsAccount() {
		Account account = this.accountRepository.findById(1).get();

        Assert.assertEquals("user1", account.getUsername());
        Assert.assertEquals("user1@company.com", account.getEmail());
	}

	@Test
	public void findByIdForNotExistingIdReturnsNull() {
		Optional<Account> account = this.accountRepository.findById(66);

        Assert.assertFalse(account.isPresent());
	}

}
