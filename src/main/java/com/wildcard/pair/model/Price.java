package com.wildcard.pair.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wildcard.pair.util.ValidationTool;

import java.util.Currency;

/**
 * Keep currency information with the numerical value of a price.
 */
public final class Price {
    @JsonIgnore
    private ValidationTool v = new ValidationTool();

    private Float price;
    private Currency currency;


    /**
     * @param price the numerical value of the price.
     * @param currency the currency for this price.
     */
    public Price(Float price, Currency currency){
        setPrice(price);
        setCurrency(currency);
    }

    /**
     * @return price the numerical value of the price.
     */
    public Float getPrice(){
        return price;
    }

    /**
     * @return currency the currency for this price.
     */
    public Currency getCurrency(){
        return currency;
    }
    

    /*
     * The following private constructor and private methods are required by Jackson. 
     */

    /**
     * Private constructor to allow Jackson deserialization
     */
    private Price(){}

    /**
     * Setter to allow Jackson deserialization.
     * @param price the numerical value of the price.
     */
    private void setPrice(Float price){
        v.notNull(price, v.REQUIRED, "Price must not be null.");
        v.notNegative(price, v.REQUIRED, "Price must be a positive Float.");
        this.price = price;
    }

    /**
     * Setter to allow jackson deserialization.
     * @param currency the currency for this price.
     */
    private void setCurrency(Currency currency){
        v.notNull(currency, v.REQUIRED, "currency must not be null.");
        this.currency = currency;
    }
}
