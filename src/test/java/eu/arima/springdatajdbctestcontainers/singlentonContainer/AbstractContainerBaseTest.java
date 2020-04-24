package eu.arima.springdatajdbctestcontainers.singlentonContainer;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;


abstract class AbstractContainerBaseTest {
	
	public static PostgreSQLContainer<?> postgresqlContainer;
	
	static {
		postgresqlContainer = new PostgreSQLContainer<>();
		postgresqlContainer.start();
    }

	@DynamicPropertySource
	static void postgresqlProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgresqlContainer::getJdbcUrl);
		registry.add("spring.datasource.username", postgresqlContainer::getUsername);
		registry.add("spring.datasource.password", postgresqlContainer::getPassword);
	}
}