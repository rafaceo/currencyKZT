package com.example.currencyExample.component;

import com.example.currencyExample.services.ExchangeRateService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    private final ExchangeRateService exchangeRateService;

    public ScheduledTasks(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @Scheduled(fixedRate = 86400000) // Обновление каждые 24 часа
    public void updateExchangeRates() {
        exchangeRateService.updateExchangeRates();
    }
}
