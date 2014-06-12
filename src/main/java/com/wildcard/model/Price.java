package com.wildcard.model;

import java.util.Currency;

public class Price {
    private final Float price;
    private final Currency currency;
    
    public Price(Float price, Currency currency){
        this.price = price;
        this.currency = currency;
    }
    
    public Float getPrice(){
        return price;
    }
    
    public Currency getCurrenct(){
        return currency;
    }
}
