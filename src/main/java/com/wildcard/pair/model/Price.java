package com.wildcard.pair.model;

import java.util.Currency;

import com.wildcard.pair.util.ValidationTool;

/**
 * Keep currency information with the numerical value of a price.
 */
public final class Price {
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
        ValidationTool.notNull(price, "Price must not be null.");
        ValidationTool.notNegative(price, "Price must be a positive Float.");
        this.price = price;
    }

    /**
     * Setter to allow jackson deserialization.
     * @param currency the currency for this price.
     */
    private void setCurrency(Currency currency){
        ValidationTool.notNull(currency, "currency must not be null.");
        this.currency = currency;
    }
}
