package com.example.currencyExample.services;

import com.example.currencyExample.dto.ExchangeRateResponse;
import com.example.currencyExample.entity.ExchangeRate;
import com.example.currencyExample.repository.ExchangeRateRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Service
@Slf4j
public class ExchangeRateService {

    @Value("${exchange.rate.api.url}")
    private String apiUrl;

    private final ExchangeRateRepository repository;

    private static final Set<String> CURRENCIES_OF_INTEREST = Set.of("USD", "EUR", "RUB");

    public ExchangeRateService(ExchangeRateRepository repository) {
        this.repository = repository;
    }

    public void updateExchangeRates() {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl;

        // Получаем данные о курсах валют
        ExchangeRateResponse response = restTemplate.getForObject(url, ExchangeRateResponse.class);

        if (response != null) {
            Map<String, Double> rates = response.getRates();
            System.out.println("Полученные курсы валют: " + rates);


            List<ExchangeRate> exchangeRates = new ArrayList<>();

            // Фильтрация и подготовка записей для сохранения
            rates.forEach((currency, rateValue) -> {
                if (CURRENCIES_OF_INTEREST.contains(currency)) {
                    ExchangeRate rate = new ExchangeRate();
                    rate.setCurrency(currency);
                    rate.setRate(rateValue);
                    rate.setDate(LocalDate.now());
                    exchangeRates.add(rate);
                }
            });

            System.out.println("Фильтрованные курсы валют: " + exchangeRates);

            // Сохраняем все записи за один раз
            repository.saveAll(exchangeRates);
        }
    }
}
