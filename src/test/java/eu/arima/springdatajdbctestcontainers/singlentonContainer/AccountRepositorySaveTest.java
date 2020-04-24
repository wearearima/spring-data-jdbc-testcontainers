package eu.arima.springdatajdbctestcontainers.singlentonContainer;

import java.util.Date;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import eu.arima.springdatajdbctestcontainers.Account;
import eu.arima.springdatajdbctestcontainers.AccountRepository;

@Transactional
@SpringBootTest
public class AccountRepositorySaveTest extends AbstractContainerBaseTest {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private NamedParameterJdbcTemplate template;

	@Test
	public void saveNewAccountWithNewUsernameGeneratesNewRow() {
		Account account = new Account();
		account.setName("new");
		account.setUsername("newusername");
		account.setEmail("newemail@company.com");
		account.setCreated(new Date());

		Account newAccount = this.accountRepository.save(account);

		Assert.assertEquals(1, JdbcTestUtils.countRowsInTableWhere((JdbcTemplate) template.getJdbcOperations(),
				"account", "id = " + newAccount.getId()));
	}

	@Test
	public void saveNewAccountWithExistingUserNameThrowsException() {
		Account account = new Account();
		account.setName("new");
		account.setUsername("user1");
		account.setEmail("newemail@company.com");
		account.setCreated(new Date());

		Assertions.assertThrows(DbActionExecutionException.class, () -> {
			this.accountRepository.save(account);
		});

	}

}
