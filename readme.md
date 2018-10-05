# Spring Data JDBC with Testcontainers

[Spring Data JDBC](https://github.com/spring-projects/spring-data-jdbc) showcase with [Testcontainers](https://www.testcontainers.org/).
 
Run tests with: 

```
    git clone https://github.com/wearearima/spring-data-jdbc-testcontainers.git
    cd spring-data-jdbc-testcontainers
    ./mvnw clean test
```

Tests are implemented with [HSQLDB](http://hsqldb.org/) database in `feature/hsqldb` branch to compare how they perform. In our tests we've got similar times with HSQLDB and Testcontainers (Docker engine must be started). 

# Credits

By https://www.arima.eu

![ARIMA](https://arima.eu/arima-claim.png)
