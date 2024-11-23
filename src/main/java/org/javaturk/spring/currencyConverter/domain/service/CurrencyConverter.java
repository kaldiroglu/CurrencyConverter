package org.javaturk.spring.currencyConverter.domain.service;

import org.javaturk.spring.currencyConverter.domain.entity.Conversion;
import org.javaturk.spring.currencyConverter.domain.entity.Currency;
import org.javaturk.spring.currencyConverter.ex.NoRateDefinedException;

public interface CurrencyConverter {

    public double convert(Currency sourceCurrency, Currency targetCurrency, double sourceAmount) throws NoRateDefinedException;

    public Conversion convert(Conversion conversion) throws NoRateDefinedException;

    public void setRate(Currency sourceCurrency, Currency targetCurrency, double rate);

    public String getRates();

}
