package org.javaturk.spring.currencyConverter.resource;

import org.javaturk.spring.currencyConverter.domain.entity.Conversion;
import org.javaturk.spring.currencyConverter.domain.entity.Conversions;
import org.javaturk.spring.currencyConverter.domain.entity.Currency;
import org.javaturk.spring.currencyConverter.ex.NoRateDefinedException;

import java.util.List;

public interface CurrencyConverterAPI {

	Conversions getAllConversions();

	List<Currency> getAllCurrencies();

	void setRate(Currency sourceCurrency, Currency targetCurrency, double rate);

	public String getRate();

	Conversion convert(Conversion conversionParameter) throws NoRateDefinedException;

	Conversion convert(Currency sourceCurrency, Currency targetCurrency, double amount);

	Conversion convertString(String sourceCurrencyString, String targetCurrencyString, double amount) throws NoRateDefinedException;

}