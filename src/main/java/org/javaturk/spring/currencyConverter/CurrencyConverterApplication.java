package org.javaturk.spring.currencyConverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CurrencyConverterApplication {

    public static void main(String[] args) {
//        System.setProperty("server.servlet.context-path", "/converter");
        SpringApplication.run(CurrencyConverterApplication.class, args);
    }
}
