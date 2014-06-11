package com.wildcard.model;

import java.util.List;

public class Offer {
    // required fields
    private final Float price;

    // optional fields
    private final Float originalPrice;
    // TODO: use java Currency
    private final String currency;
    private final String description;
    private final String productUniqueId;
    private final Availability availability;
    private final Integer quantity;
    // TODO: saleStartDate
    // TODO: saleEndDate
    // TODO: expirationDate
    // TODO: use Java Locale instead of CountryCode
    private final List<CountryCode> geographicAvailability;
    
    public Offer(OfferBuilder builder){
        this.price = builder.price;
        this.originalPrice = builder.originalPrice;
        this.currency = builder.currency;
        this.description = builder.description;
        this.productUniqueId = builder.productUniqueId;
        this.availability = builder.availability;
        this.quantity = builder.quantity;
        this.geographicAvailability = builder.geographicAvailability;
    }
    
    public List<CountryCode> getGeographicAvailability(){
        return geographicAvailability;
    }
    
    public Integer getQuantity(){
        return quantity;
    }
    
    public Availability getAvailability(){
        return availability;
    }
    
    public String getProductUniqueId(){
        return productUniqueId;
    }
    
    public String getDescription(){
        return description;
    }
    
    public String getCurrency(){
        return currency;
    }
    
    public Float getOriginalPrice(){
        return originalPrice;
    }
    
    public Float getPrice(){
        return price;
    }
}
