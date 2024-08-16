package com.example.currencyExample.controller;

import com.example.currencyExample.entity.ExchangeRate;
import com.example.currencyExample.repository.ExchangeRateRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/exchange-rates")
public class ExchangeRateController {
    private final ExchangeRateRepository repository;

    public ExchangeRateController(ExchangeRateRepository repository) {
        this.repository = repository;
    }

    @GetMapping(name = "/")
    public List<ExchangeRate> getAllExchangeRates() {
        return repository.findAll();
    }
}
