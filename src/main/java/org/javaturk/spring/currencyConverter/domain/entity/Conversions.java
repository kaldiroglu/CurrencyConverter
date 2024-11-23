package org.javaturk.spring.currencyConverter.domain.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Conversions {
    private List<Conversion> conversions = Collections.synchronizedList(new ArrayList<>());

    public Conversions() {
    }

    public void addConversion(Conversion conversion) {
        conversions.add(conversion);
    }

    public List<Conversion> getConversions() {
        return conversions;
    }

    public void setConversions(List<Conversion> list) {
        conversions = list;
    }
}
