package com.exchange.service;

import com.exchange.model.Commission;
import com.exchange.model.Exchange;
import com.exchange.model.Rate;
import com.exchange.repository.CommissionRepository;
import com.exchange.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExchangeService {

    @Autowired
    private CommissionRepository comissionRepository;
    @Autowired
    private RateRepository rateRepository;

    public List<Commission> getCommissionPt() {
        return comissionRepository.findAll();
    }

    public void postCommissionPt(Commission commission) {
        comissionRepository.save(commission);
    }

    public List<Rate> getRate() {
        return rateRepository.findAll();

    }

    public void postRate(Rate rate) {
        rateRepository.save(rate);
    }


//    Обмен валют. Позволяет получать информацию по суммам при прямом и обратном обмене валют с учетом комисии.
//    Пример рямого обмена: обменять 100 USD на EUR, в этом случае запрос должен соержать объект вида:
//    {"amountFrom": 100.00,"currencyFrom": "USD","currencyTo": "EUR","operationType":"GIVE"}.
//    В ответ должен вернуться полностью заполненый объект.
//    Пример обратного обмена: узнать сколько нужно USD для того чтобы получить в результате обмена 100 EUR,
//    в этом случае запрос должен содержать объект вида:
//    {"amountTo": 100.00,"currencyFrom": "USD","currencyTo": "EUR","operationType":"GET"}

    public Exchange handleExchange(Exchange exchange) {
        Commission commission = comissionRepository.
                findCommissionByFromAndTo(exchange.getCurrencyFrom(), exchange.getCurrencyTo());

        Rate rate = rateRepository.
                findRateByFromAndTo(exchange.getCurrencyFrom(), exchange.getCurrencyTo());

        BigDecimal commissionAmmount, ammountByRate;

        switch (exchange.getOperationType()) {
            case GET:
                if (exchange.getAmountTo().compareTo(BigDecimal.ZERO) == 0) {
                    throw new IllegalArgumentException(
                            "GET operationType associated with currencyTo NON Nullable value");
                }
                commissionAmmount = exchange.getAmountTo().
                        divide(BigDecimal.valueOf(100)).
                        multiply(commission.getCommissionPt());
                ammountByRate = exchange.getAmountTo().multiply(rate.getRate());

                exchange.setAmountFrom(ammountByRate.add(commissionAmmount));
                break;
            case GIVE:
                if (exchange.getAmountFrom().compareTo(BigDecimal.ZERO) == 0) {
                    throw new IllegalArgumentException(
                            "GIVE operationType associated with currencyFrom NON Nullable value");
                } else {
                    commissionAmmount = exchange.getAmountFrom().
                            divide(BigDecimal.valueOf(100)).
                            multiply(commission.getCommissionPt());
                    ammountByRate = exchange.getAmountFrom().multiply(rate.getRate());

                    exchange.setAmountTo(ammountByRate.subtract(commissionAmmount));
                    break;
                }
        }
        return exchange;
    }
}
