package org.javaturk.spring.currencyConverter.domain.entity;

import java.util.Arrays;
import java.util.List;

public enum Currency {
    TRY("Turkish Lira"), SAR("Saudi Arabian Riyal"), USD("US Dollar"), EUR("EURO");

    private String description;

    private Currency(String description) {
        this.description = description;
    }

    public static List<Currency> getCurrencyList() {
        List<Currency> list = Arrays.asList(Currency.values());
        return list;
    }

    public static Currency switchToCurrency(String currencyString) {
        Currency currency = null;
        switch (currencyString) {
            case "TRY":
                currency = Currency.TRY;
                break;

            case "SAR":
                currency = Currency.SAR;
                break;

            case "USD":
                currency = Currency.USD;
                break;

            case "EUR":
                currency = Currency.EUR;
                break;
        }

        return currency;
    }
}
