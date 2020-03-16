package com.exchange.repository;

import com.exchange.model.Commission;
import com.exchange.model.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommissionRepository extends CrudRepository<Commission, Integer> {

    Commission findCommissionByFromAndTo(Currency from, Currency to);

    @Override
    List<Commission> findAll();

}
