package com.wildcard.model;

import java.util.List;

public class Offer {
    // required fields
    private final float price;

    // optional fields
    private final float originalPrice;
    private final String currency;
    private final String description;
    private final String productUniqueId;
    private final Availability availability;
    private final int quantity;
    // TODO: saleStartDate
    // TODO: saleEndDate
    // TODO: expirationDate
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
    
    public int getQuantity(){
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
    
    public float getOriginalPrice(){
        return originalPrice;
    }
    
    public float getPrice(){
        return price;
    }
}
