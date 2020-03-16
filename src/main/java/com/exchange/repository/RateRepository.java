package com.exchange.repository;

import com.exchange.model.Currency;
import com.exchange.model.Rate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepository extends CrudRepository<Rate, Integer> {

    Rate findRateByFromAndTo(Currency from, Currency to);

    @Override
    List<Rate> findAll();
}
