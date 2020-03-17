package com.exchange.repository;

import com.exchange.model.Currency;
import com.exchange.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<Rate, Integer> {
    Rate findRateByFromAndTo(Currency from, Currency to);
}
