package org.javaturk.spring.currencyConverter.ex;

public class NoRateDefinedException extends Exception{
	public static final String MESSAGE = "No rate defined yet: ";

	public NoRateDefinedException(String message) {

		super(MESSAGE + message);
	}
}
