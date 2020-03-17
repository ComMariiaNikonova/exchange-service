package com.exchange.config;

import com.exchange.ApplicationExchangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataSourceSetup implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ApplicationExchangeService.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... strings) {

        log.info("DB instantiation");

        jdbcTemplate.execute("CREATE DATABASE IF NOT EXISTS data_exch");
        jdbcTemplate.execute("USE data_exch");

        jdbcTemplate.execute("DROP TABLE IF EXISTS commission");
        jdbcTemplate.execute(" CREATE TABLE commission\n" +
                "        (\n" +
                "        commission DECIMAL (10, 2),\n" +
                "        currency_from     VARCHAR(255),\n" +
                "        currency_to       VARCHAR(255),\n" +
                "        PRIMARY KEY (currency_from, currency_to)\n" +
                "        );");


        jdbcTemplate.execute("DROP TABLE IF EXISTS rate");
        jdbcTemplate.execute("CREATE TABLE rate\n" +
                "        (\n" +
                "        rate DECIMAL (10, 2),\n" +
                "        currency_from     VARCHAR(255),\n" +
                "        currency_to       VARCHAR(255),\n" +
                "        PRIMARY KEY (currency_from, currency_to)\n" +
                "        );");


        List<Object[]> commissions = Arrays.asList
                ("0.1 EUR EUR", "20.1 EUR RUB", "3 EUR USD", "0.45 EUR UAH",
                        "21.1 UAH EUR", "4.1 UAH RUB", "5.5 UAH USD", "0.68 UAH UAH",
                        "10.0 USD EUR", "11.4 USD RUB", "0.8 USD USD", "0.15 USD UAH",
                        "99.1 RUB EUR", "4.1 RUB RUB", "0.8 RUB USD", "10.1 RUB UAH")
                .stream().map(name -> name.split(" ")).collect(Collectors.toList());

        List<Object[]> rates = Arrays.asList
                ("0.1 EUR EUR", "0.6 EUR RUB", "0.8 EUR USD", "30 EUR UAH",
                        "0.33 UAH EUR", "14 UAH RUB", "5 UAH USD", "67 UAH UAH",
                        "0.89 USD EUR", "11 USD RUB", "4 USD USD", "0.74 USD UAH",
                        "45 RUB EUR", "0.1 RUB RUB", "60 RUB USD", "8 RUB UAH")
                .stream().map(name -> name.split(" ")).collect(Collectors.toList());

        jdbcTemplate.batchUpdate("INSERT INTO commission (commission, currency_from, currency_to) VALUES (?,?,?)",
                commissions);
        jdbcTemplate.batchUpdate("INSERT INTO rate (rate, currency_from, currency_to) VALUES (?,?,?)", rates);

        log.info("App ready to use...");
    }
}
