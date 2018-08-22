package eu.arima.springdatajdbctestcontainers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.mapping.PersistentEntity;
import org.springframework.data.mapping.PersistentPropertyAccessor;
import org.springframework.data.mapping.PreferredConstructor;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.relational.core.conversion.BasicRelationalConverter;
import org.springframework.data.relational.core.conversion.RelationalConverter;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;
import org.springframework.data.relational.core.mapping.RelationalPersistentEntity;
import org.springframework.data.relational.core.mapping.RelationalPersistentProperty;
import org.springframework.data.util.TypeInformation;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.function.Function;

@EnableJdbcRepositories
@SpringBootApplication
public class SpringDataJdbcTestcontainersApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJdbcTestcontainersApplication.class, args);
	}

	@Bean
	public RelationalMappingContext relationalMappingContext() {
		return new RelationalMappingContext();
	}

	@Bean
	public RelationalConverter relationalConverter() {
		return new BasicRelationalConverter(new RelationalMappingContext());
	}

}
