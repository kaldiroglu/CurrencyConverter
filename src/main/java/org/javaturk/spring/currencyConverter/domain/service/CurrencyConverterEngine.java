package org.javaturk.spring.currencyConverter.domain.service;

import org.javaturk.spring.currencyConverter.domain.entity.Conversion;
import org.javaturk.spring.currencyConverter.domain.entity.Currency;
import org.javaturk.spring.currencyConverter.ex.NoRateDefinedException;
import org.springframework.stereotype.Service;

import static org.javaturk.spring.currencyConverter.domain.entity.Currency.*;

@Service
public class CurrencyConverterEngine implements CurrencyConverter {
    private double rateUSDToTRY;
    private double rateTRYToUSD;
    private double rateEURToUSD;
    private double rateUSDToEUR;
    private double rateTRYToEUR;
    private double rateEURToTRY;

    private double rateSARToTRY;
    private double rateTRYToSAR;
    private double rateSARToUSD;
    private double rateUSDToSAR;
    private double rateSARToEUR;
    private double rateEURToSAR;

    private boolean tryAndUSD;
    private boolean usdAndEUR;
    private boolean eurAndTRY;
    private boolean sarAndTRY;
    private boolean sarAndUSD;
    private boolean sarAndEUR;


    public Conversion convert(Conversion conversion) throws NoRateDefinedException {
        double targetAmount = convert(conversion.getSourceCurrency(), conversion.getTargetCurrency(),
                conversion.getSourceAmount());
        conversion.setTargetAmount(targetAmount);
        return conversion;
    }

    @Override
    public void setRate(Currency sourceCurrency, Currency targetCurrency, double rate) {
        if (sourceCurrency.equals(USD) && targetCurrency.equals(TRY)) {
            rateUSDToTRY = rate;
            rateTRYToUSD = 1 / rate;
            tryAndUSD = true;
        } else if (sourceCurrency.equals(TRY) && targetCurrency.equals(USD)) {
            rateTRYToUSD = rate;
            rateUSDToTRY = 1 / rate;
            tryAndUSD = true;
        } else if (sourceCurrency.equals(EUR) && targetCurrency.equals(USD)) {
            rateEURToUSD = rate;
            rateUSDToEUR = 1 / rate;
            usdAndEUR = true;
        } else if (sourceCurrency.equals(USD) && targetCurrency.equals(EUR)) {
            rateUSDToEUR = rate;
            rateEURToUSD = 1 / rate;
            usdAndEUR = true;
        } else if (sourceCurrency.equals(TRY) && targetCurrency.equals(EUR)) {
            rateTRYToEUR = rate;
            rateEURToTRY = 1 / rate;
            eurAndTRY = true;
        } else if (sourceCurrency.equals(EUR) && targetCurrency.equals(TRY)) {
            rateEURToTRY = rate;
            rateTRYToEUR = 1 / rate;
            eurAndTRY = true;
        } else if (sourceCurrency.equals(SAR) && targetCurrency.equals(TRY)) {
            rateSARToTRY = rate;
            rateTRYToSAR = 1 / rate;
            sarAndTRY = true;
        } else if (sourceCurrency.equals(TRY) && targetCurrency.equals(SAR)) {
            rateTRYToSAR = rate;
            rateSARToTRY = 1 / rate;
            sarAndTRY = true;
        } else if (sourceCurrency.equals(SAR) && targetCurrency.equals(USD)) {
            rateSARToUSD = rate;
            rateUSDToSAR = 1 / rate;
            sarAndUSD = true;
        } else if (sourceCurrency.equals(USD) && targetCurrency.equals(SAR)) {
            rateUSDToSAR = rate;
            rateSARToUSD = 1 / rate;
            sarAndUSD = true;
        } else if (sourceCurrency.equals(SAR) && targetCurrency.equals(EUR)) {
            rateSARToEUR = rate;
            rateEURToSAR = 1 / rate;
            sarAndEUR = true;
        } else if (sourceCurrency.equals(EUR) && targetCurrency.equals(SAR)) {
            rateEURToSAR = rate;
            rateSARToEUR = 1 / rate;
            sarAndEUR = true;
        }
    }

    @Override
    public String getRates() {
        return "All rates:" +
                "\nUSD to TRY " + rateUSDToTRY + "\nTRY to USD " + rateTRYToUSD +
                "\nEUR to TRY: " + rateEURToTRY + "\nTRY to EUR: " + rateTRYToEUR +
                "\nSAR to TRY " + rateSARToTRY + "\nTRY to SAD: " + rateTRYToSAR +
                "\nEUR to USD: " + rateEURToUSD + "\nUSD to EUR: " + rateUSDToEUR +
                "\nSAR to USD: " + rateSARToUSD + "\nUSD to SAR " + rateUSDToSAR +
                "\nSAR to EUR: " + rateSARToEUR + "\nEUR to SAR: " + rateEURToSAR;
    }

    public double convert(Currency sourceCurrency, Currency targetCurrency, double sourceAmount) throws NoRateDefinedException {
        double targetAmount = 0;
        // First check if source and target currencies are the same.
        if (sourceCurrency.equals(targetCurrency))
            return sourceAmount;
        // Then convert
        if (sourceCurrency.equals(TRY)) {
            targetAmount = getTargetAmountForTRY(sourceAmount, targetCurrency);
        } else if (sourceCurrency.equals(USD)) {
            targetAmount = getTargetAmountForUSD(sourceAmount, targetCurrency);
        } else if (sourceCurrency.equals(EUR)) {
            targetAmount = getTargetAmountForEUR(sourceAmount, targetCurrency);
        } else if (sourceCurrency.equals(SAR)) {
            targetAmount = getTargetAmountForSAR(sourceAmount, targetCurrency);
        }
        return targetAmount;
    }

    private double getTargetAmountForTRY(double sourceAmount, Currency targetCurrency) throws NoRateDefinedException {
        return switch (targetCurrency) {
            case USD:
                if (tryAndUSD)
                    yield sourceAmount * rateTRYToUSD;
                else throw new NoRateDefinedException("TRY - USD");
            case EUR:
                if (eurAndTRY)
                    yield sourceAmount * rateTRYToEUR;
                else throw new NoRateDefinedException("TRY - EUR");
            case SAR:
                if (sarAndTRY)
                    yield sourceAmount * rateTRYToSAR;
                else throw new NoRateDefinedException("TRY - SAR");
            default:
                throw new IllegalStateException("Unsupported currency: " + targetCurrency);
        };
    }

    private double getTargetAmountForUSD(double sourceAmount, Currency targetCurrency) throws NoRateDefinedException {
        return switch (targetCurrency) {
            case TRY:
                if (tryAndUSD)
                    yield sourceAmount * rateUSDToTRY;
                else throw new NoRateDefinedException("USD - TRY");
            case EUR:
                if (usdAndEUR)
                    yield sourceAmount * rateUSDToEUR;
                else throw new NoRateDefinedException("USD - EUR");
            case SAR:
                if (sarAndUSD)
                    yield sourceAmount * rateUSDToSAR;
                else throw new NoRateDefinedException("USD - SAR");
            default:
                throw new IllegalStateException("Unsupported currency: " + targetCurrency);
        };
    }

    private double getTargetAmountForEUR(double sourceAmount, Currency targetCurrency) throws NoRateDefinedException {
        return switch (targetCurrency) {
            case TRY:
                if (eurAndTRY)
                    yield sourceAmount * rateEURToTRY;
                else throw new NoRateDefinedException("EUR - TRY");
            case USD:
                if (usdAndEUR)
                    yield sourceAmount * rateEURToUSD;
                else throw new NoRateDefinedException("EUR - USD");
            case SAR:
                if (sarAndEUR)
                    yield sourceAmount * rateEURToSAR;
                else throw new NoRateDefinedException("EUR - SAR");
            default:
                throw new IllegalStateException("Unsupported currency: " + targetCurrency);
        };
    }

    private double getTargetAmountForSAR(double sourceAmount, Currency targetCurrency) throws NoRateDefinedException {
        return switch (targetCurrency) {
            case TRY:
                if (sarAndTRY)
                    yield sourceAmount * rateSARToTRY;
                else throw new NoRateDefinedException("SAR - TRY");
            case USD:
                if (sarAndUSD)
                    yield sourceAmount * rateSARToUSD;
                else throw new NoRateDefinedException("SAR - USD");
            case EUR:
                if (sarAndEUR)
                    yield sourceAmount * rateSARToEUR;
                else throw new NoRateDefinedException("SAR - EUR");
            default:
                throw new IllegalStateException("Unsupported currency: " + targetCurrency);
        };
    }
}
