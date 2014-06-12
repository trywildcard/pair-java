package com.wildcard.model;

import java.util.Currency;

import com.wildcard.model.util.ValidationTool;

public class Price {
    private Float price;
    private Currency currency;
    
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
    
    public Currency getCurrency(){
        return currency;
    }
    

    /*
     * The following private constructor and private methods are required by Jackson. 
     */
    
    private Price(){}
    
    private void setPrice(Float price){
        this.price = price;
    }
    
    private void setCurrency(Currency currency){
        this.currency = currency;
    }
}
