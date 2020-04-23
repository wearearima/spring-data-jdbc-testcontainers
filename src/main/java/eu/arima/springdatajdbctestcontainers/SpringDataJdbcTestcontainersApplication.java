package eu.arima.springdatajdbctestcontainers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jdbc.repository.config.JdbcConfiguration;
import org.springframework.data.relational.core.conversion.RelationalConverter;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;

/**
 * {@link JdbcConfiguration} registers {@link RelationalMappingContext} and {@link RelationalConverter} for Spring Data
 * JDBC to work.
 */
@Import(JdbcConfiguration.class)
@EnableJdbcRepositories
@SpringBootApplication
public class SpringDataJdbcTestcontainersApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJdbcTestcontainersApplication.class, args);
	}

}
