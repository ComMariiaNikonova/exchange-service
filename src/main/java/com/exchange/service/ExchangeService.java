package com.exchange.service;

import com.exchange.model.Commission;
import com.exchange.model.Exchange;
import com.exchange.model.OperationType;
import com.exchange.model.Rate;
import com.exchange.repository.CommissionRepository;
import com.exchange.repository.RateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final CommissionRepository commissionRepository;

    private final RateRepository rateRepository;

    public final List<Commission> getCommissionPt() {
        return commissionRepository.findAll();
    }

    static Predicate<Exchange> hasNoContractSupport = exchange -> (hasNoContractSupportPredicate(exchange));

    public Commission createCommissionPt(Commission commission) {
        return commissionRepository.save(commission);
    }

    public List<Rate> getRate() {
        return rateRepository.findAll();
    }

    public Rate createRate(Rate rate) {
        return rateRepository.save(rate);
    }

    public Exchange handleExchange(Exchange exchange) {

        if (hasNoContractSupport.test(exchange)) {
            throw new IllegalArgumentException(
                    "GET operationType associated with currencyTo NON Nullable value;" +
                            "GIVE operationType associated with currencyFrom NON Nullable value");
        }

        Commission commission = commissionRepository.
                findCommissionByFromAndTo(exchange.getCurrencyFrom(), exchange.getCurrencyTo());

        Rate rate = rateRepository.
                findRateByFromAndTo(exchange.getCurrencyFrom(), exchange.getCurrencyTo());

        switch (exchange.getOperationType()) {
            case GET:
                setupExchangeGet(exchange, commission, rate);
                break;
            case GIVE:
                setupExchangeGive(exchange, commission, rate);
                break;
        }
        return exchange;
    }

    private void setupExchangeGet(Exchange exchange, Commission commission, Rate rate) {
        BigDecimal commissionAmount;
        commissionAmount = exchange.getAmountTo().
                divide(BigDecimal.valueOf(100)).
                multiply(commission.getCommissionPt());
        exchange.setAmountFrom(exchange.getAmountTo().add(commissionAmount).multiply(rate.getRate()));
    }

    private void setupExchangeGive(Exchange exchange, Commission commission, Rate rate) {
        BigDecimal commissionAmount;
        commissionAmount = exchange.getAmountFrom().
                divide(BigDecimal.valueOf(100)).
                multiply(commission.getCommissionPt());
        exchange.setAmountTo(exchange.getAmountFrom().subtract(commissionAmount).multiply(rate.getRate()));
    }

    private static boolean hasNoContractSupportPredicate(Exchange exchange) {
        return ((Objects.isNull(exchange.getAmountTo()) ||
                exchange.getAmountTo().compareTo(BigDecimal.ZERO) == 0)
                && exchange.getOperationType().equals(OperationType.GET)) ||
                ((Objects.isNull(exchange.getAmountFrom()) || exchange.getAmountFrom().compareTo(BigDecimal.ZERO) == 0)
                        && exchange.getOperationType().equals(OperationType.GIVE));
    }

}
