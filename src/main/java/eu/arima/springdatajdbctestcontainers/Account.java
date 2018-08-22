package eu.arima.springdatajdbctestcontainers;

import org.springframework.data.annotation.Id;

public class Account {

    @Id
    private int id;

    private String name;

    private String username;



}
