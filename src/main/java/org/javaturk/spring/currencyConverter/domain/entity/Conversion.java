package org.javaturk.spring.currencyConverter.domain.entity;

import java.time.LocalDateTime;

public class Conversion {
	private static int count;
	private int id;

	{
		id = ++count;
	}

	private Currency sourceCurrency;
	private Currency targetCurrency;

	private double sourceAmount;
	private double targetAmount;

	/**
	 * jackson-datatype-jsr310-2.12.0.jar is needed for this otherwise the server
	 * gives error: com.fasterxml.jackson.databind.exc.InvalidDefinitionException:
	 * Cannot construct instance of `java.time.LocalDateTime` (no Creators, like
	 * default constructor, exist): no String-argument constructor/factory method to
	 * deserialize from String value ('2020-12-22T18:50:20.294321')
	 * 
	 * Serializer is needed too otherwise the server give error: 
	 * Exception in thread
	 * "main" javax.ws.rs.client.ResponseProcessingException:
	 * javax.json.bind.JsonbException: Can't deserialize JSON array into: class
	 * java.time.LocalDateTime
	 * 
	 */
	private LocalDateTime date;

	public Conversion() {
		date = LocalDateTime.now();
	}

	public Conversion(Currency sourceCurrency, Currency targetCurrency, double sourceAmount) {
		this.sourceCurrency = sourceCurrency;
		this.targetCurrency = targetCurrency;
		this.sourceAmount = sourceAmount;
		date = LocalDateTime.now();
	}

	public Conversion(Currency sourceCurrency, Currency targetCurrency, double sourceAmount, double targetAmount) {
		this.sourceCurrency = sourceCurrency;
		this.targetCurrency = targetCurrency;
		this.sourceAmount = sourceAmount;
		this.targetAmount = targetAmount;
		date = LocalDateTime.now();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Currency getSourceCurrency() {
		return sourceCurrency;
	}

	public void setSourceCurrency(Currency sourceCurrency) {
		this.sourceCurrency = sourceCurrency;
	}

	public Currency getTargetCurrency() {
		return targetCurrency;
	}

	public void setTargetCurrency(Currency targetCurrency) {
		this.targetCurrency = targetCurrency;
	}

	public double getSourceAmount() {
		return sourceAmount;
	}

	public void setSourceAmount(double sourceAmount) {
		this.sourceAmount = sourceAmount;
	}

	public double getTargetAmount() {
		return targetAmount;
	}

	public void setTargetAmount(double targetAmount) {
		this.targetAmount = targetAmount;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Conversion [Source currency=" + sourceCurrency + ", Target currency=" + targetCurrency
				+ ", sourceAmount=" + sourceAmount + ", Target amount=" + targetAmount + ", Date=" + date + "]";
	}
}
