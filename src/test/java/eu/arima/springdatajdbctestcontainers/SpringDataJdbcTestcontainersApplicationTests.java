package eu.arima.springdatajdbctestcontainers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataJdbcTestcontainersApplicationTests {

	@Autowired
	private DataSource dataSource;

	/**
	 * Although there is not a DataSource defined in {@link SpringDataJdbcTestcontainersApplication},
	 * Spring Boot adds an embedded database automatically because it detects the {@code spring-jdbc} dependency.
	 */
	@Test
	public void contextLoads() {
		Assert.assertNotNull(dataSource);
	}

}
