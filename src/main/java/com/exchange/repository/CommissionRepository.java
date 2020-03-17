package com.exchange.repository;

import com.exchange.model.Commission;
import com.exchange.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommissionRepository extends JpaRepository<Commission, Integer> {

    Commission findCommissionByFromAndTo(Currency from, Currency to);
}
