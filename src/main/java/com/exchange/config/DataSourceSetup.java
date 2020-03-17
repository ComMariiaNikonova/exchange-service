package com.exchange.config;

import com.exchange.ApplicationExchangeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DataSourceSetup implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ApplicationExchangeService.class);

    private final JdbcTemplate jdbcTemplate;

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
                ("2 EUR EUR", "20 EUR RUB", "2 EUR USD", "5 EUR UAH",
                        "5 UAH EUR", "10 UAH RUB", "5 UAH USD", "2 UAH UAH",
                        "2 USD EUR", "30 USD RUB", "2 USD USD", "5 USD UAH",
                        "20 RUB EUR", "2 RUB RUB", "30 RUB USD", "10 RUB UAH")
                .stream().map(name -> name.split(" ")).collect(Collectors.toList());

        List<Object[]> rates = Arrays.asList
                ("1 EUR EUR", "0.01 EUR RUB", "2 EUR USD", "30 EUR UAH",
                        "0.33 UAH EUR", "0.1 UAH RUB", "25 UAH USD", "1 UAH UAH",
                        "0.5 USD EUR", "0.01 USD RUB", "1 USD USD", "0.4 USD UAH",
                        "100 RUB EUR", "1 RUB RUB", "100 RUB USD", "10 RUB UAH")
                .stream().map(name -> name.split(" ")).collect(Collectors.toList());

        jdbcTemplate.batchUpdate("INSERT INTO commission (commission, currency_from, currency_to) VALUES (?,?,?)",
                commissions);
        jdbcTemplate.batchUpdate("INSERT INTO rate (rate, currency_from, currency_to) VALUES (?,?,?)", rates);

        log.info("Datasource ready to use...");
    }
}
