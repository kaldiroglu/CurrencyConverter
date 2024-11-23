package org.javaturk.spring.currencyConverter.resource;

import java.util.List;

import org.javaturk.spring.currencyConverter.domain.entity.Conversion;
import org.javaturk.spring.currencyConverter.domain.entity.Conversions;
import org.javaturk.spring.currencyConverter.domain.entity.Currency;
import org.javaturk.spring.currencyConverter.domain.service.CurrencyConverter;
import org.javaturk.spring.currencyConverter.ex.InvalidCurrencyException;
import org.javaturk.spring.currencyConverter.ex.NoRateDefinedException;
import org.javaturk.spring.currencyConverter.validation.CurrencyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/conversion")
public class CurrencyConverterResource implements CurrencyConverterAPI {

    private static Conversions conversions = new Conversions();

    @Autowired
    private CurrencyConverter converter;

    @Autowired
    private CurrencyValidator currencyValidator;

    @Override
    @GetMapping
    public Conversions getAllConversions() {
        return conversions;
    }

    @Override
    @GetMapping("/currency")
    public List<Currency> getAllCurrencies() {
        return Currency.getCurrencyList();
    }

    @Override
    @PostMapping
    public Conversion convert(@RequestBody Conversion conversionParameter)  {
        Conversion conversion = null;
        try {
            conversion = converter.convert(conversionParameter);
        } catch (NoRateDefinedException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rate undefined!");
        }
        conversions.addConversion(conversion);
        return conversion;
    }

    @Override
    @PostMapping("{from}/{to}/{amount}")
    public Conversion convert(@PathVariable("from") Currency sourceCurrency, @PathVariable("to") Currency targetCurrency, @PathVariable("amount") double amount) {
        double targetAmount = 0;
        try {
            targetAmount = converter.convert(sourceCurrency, targetCurrency, amount);
        } catch (NoRateDefinedException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rate undefined!");
        }
        Conversion conversion = new Conversion(sourceCurrency, targetCurrency, amount, targetAmount);
        conversions.addConversion(conversion);
        return conversion;
    }

    @Override
    @PostMapping("rate/{from}/{to}/{rate}")
    public void setRate(@PathVariable("from") Currency sourceCurrency, @PathVariable("to") Currency targetCurrency, @PathVariable("rate") double rate) {
        converter.setRate(sourceCurrency, targetCurrency, rate);
    }

    @Override
    @GetMapping("rate")
    public String getRate() {
        return converter.getRates();
    }

    @Override
    @PostMapping("string/{from}/{to}/{amount}")
    public Conversion convertString(@PathVariable("from") String sourceCurrencyString, @PathVariable("to") String targetCurrencyString, @PathVariable("amount") double amount) {
        if (!currencyValidator.validate(sourceCurrencyString)) {
            System.out.println("Bad Request: " + InvalidCurrencyException.INVALID_SOURCE_CURRENCY_MESSAGE);
//			throw new InvalidCurrencyException(InvalidCurrencyException.INVALID_SOURCE_CURRENCY_MESSAGE, Response.Status.BAD_REQUEST);
        }
        if (!currencyValidator.validate(targetCurrencyString)) {
            System.out.println("Bad Request: " + InvalidCurrencyException.INVALID_TARGET_CURRENCY_MESSAGE);
//			throw new InvalidCurrencyException(InvalidCurrencyException.INVALID_TARGET_CURRENCY_MESSAGE, Response.Status.BAD_REQUEST);
        }

        Currency sourceCurrency = Currency.switchToCurrency(sourceCurrencyString);
        Currency targetCurrency = Currency.switchToCurrency(targetCurrencyString);
        Conversion conversion = null;
        double targetAmount = 0;
        try {
            targetAmount = converter.convert(sourceCurrency, targetCurrency, amount);
        } catch (NoRateDefinedException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rate undefined!");
        }
        conversion = new Conversion(sourceCurrency, targetCurrency, amount, targetAmount);
        conversions.addConversion(conversion);
        return conversion;
    }

//    @ResponseStatus(value = HttpStatus.BAD_REQUEST,
//            reason = "Conversion rate undefined!")
//    @ExceptionHandler(NoRateDefinedException.class)
//	private String returnError(){
//		return "Conversion rate undefined!";
//    }
}
