package org.javaturk.spring.currencyConverter.validation;

import org.javaturk.spring.currencyConverter.domain.entity.Currency;
import org.springframework.stereotype.Component;

@Component
public class CurrencyValidator {
	
	public boolean validate(String currency) {
        return currency.equals(Currency.TRY.name()) | currency.equals(Currency.USD.name()) | currency.equals(Currency.EUR.name());
	}
}
