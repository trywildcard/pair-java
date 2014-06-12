package com.wildcard.model;

import java.util.Currency;

import com.wildcard.model.util.ValidationTool;

public class Price {
    private final Float price;
    private final Currency currency;
    
    public Price(Float price, Currency currency){
        ValidationTool.notNull(price, "Price must not be null.");
        ValidationTool.notNegative(price, "Price must be a positive Float.");
        ValidationTool.notNull(currency, "currency must not be null.");
        
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
