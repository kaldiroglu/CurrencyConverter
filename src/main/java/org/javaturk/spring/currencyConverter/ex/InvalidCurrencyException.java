package org.javaturk.spring.currencyConverter.ex;

public class InvalidCurrencyException extends Exception{
	public static final String INVALID_SOURCE_CURRENCY_MESSAGE = "Source currency is not supported!";
	public static final String INVALID_TARGET_CURRENCY_MESSAGE = "Target currency is not supported!";
	
	public InvalidCurrencyException(String message) {
		super(message);
	}
}
