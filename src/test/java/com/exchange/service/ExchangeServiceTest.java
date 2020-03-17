package com.exchange.service;

import com.exchange.model.*;
import com.exchange.repository.CommissionRepository;
import com.exchange.repository.RateRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
public class ExchangeServiceTest {
    @MockBean
    private CommissionRepository commissionRepository;
    @MockBean
    private RateRepository rateRepository;

    private ExchangeService service;

    @Before
    public void setUp() {
        service = new ExchangeService(commissionRepository, rateRepository);

        Commission commission = new Commission(BigDecimal.valueOf(2), Currency.EUR, Currency.RUB);
        Mockito.when(commissionRepository.findCommissionByFromAndTo(Currency.EUR, Currency.RUB))
                .thenReturn(commission);

        Mockito.when(commissionRepository.findCommissionByFromAndTo(Currency.RUB, Currency.EUR))
                .thenReturn(commission);

        Rate rateGive = new Rate(BigDecimal.valueOf(0.5), Currency.EUR, Currency.RUB);
        Rate rateGet = new Rate(BigDecimal.valueOf(2), Currency.EUR, Currency.RUB);

        Mockito.when(rateRepository.findRateByFromAndTo(Currency.EUR, Currency.RUB))
                .thenReturn(rateGive);
        Mockito.when(rateRepository.findRateByFromAndTo(Currency.RUB, Currency.EUR))
                .thenReturn(rateGet);
    }

    @Test
    public void handleExchangeGive() {
        Exchange exchange = service.handleExchange(new Exchange(OperationType.GIVE, BigDecimal.valueOf(100),
                BigDecimal.valueOf(0), Currency.EUR, Currency.RUB));

        assertThat(exchange.getAmountTo(), Matchers.comparesEqualTo(BigDecimal.valueOf(49)));
    }

    @Test
    public void handleExchangeGet() {
        Exchange exchange = service.handleExchange(new Exchange(OperationType.GET, BigDecimal.valueOf(0),
                BigDecimal.valueOf(49), Currency.EUR, Currency.RUB));

        assertThat(exchange.getAmountFrom(), Matchers.comparesEqualTo(BigDecimal.valueOf(99.96)));
    }


    @Test(expected = IllegalArgumentException.class)
    public void handleExceptionHandler() {
        service.handleExchange(new Exchange(OperationType.GET, BigDecimal.valueOf(0),
                BigDecimal.valueOf(0), Currency.EUR, Currency.RUB));
    }
}