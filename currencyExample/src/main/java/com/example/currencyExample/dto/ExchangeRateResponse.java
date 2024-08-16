package com.example.currencyExample.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class ExchangeRateResponse {
    @JsonProperty("rates")
    private Map<String, Double> rates;



}
