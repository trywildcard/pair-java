package com.wildcard.model;

import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Offer {
    // required fields
    private final Float price;

    // optional fields
    private final Float originalPrice;
    private final Currency currency;
    private final String description;
    private final String productUniqueId;
    private final Availability availability;
    private final Integer quantity;
    private final Date saleStartDate;
    private final Date saleEndDate;
    private final Date expirationDate;
    private final List<Locale> geographicAvailability;
    
    public Offer(OfferBuilder builder){
        this.price = builder.price;
        this.originalPrice = builder.originalPrice;
        this.currency = builder.currency;
        this.description = builder.description;
        this.productUniqueId = builder.productUniqueId;
        this.availability = builder.availability;
        this.quantity = builder.quantity;
        this.saleStartDate = builder.saleStartDate;
        this.saleEndDate = builder.saleEndDate;
        this.expirationDate = builder.expirationDate;
        this.geographicAvailability = builder.geographicAvailability;
    }
    
    public List<Locale> getGeographicAvailability(){
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
    
    public Currency getCurrency(){
        return currency;
    }
    
    public Float getOriginalPrice(){
        return originalPrice;
    }
    
    public Float getPrice(){
        return price;
    }

    public Date getSaleStartDate() {
        return saleStartDate;
    }

    public Date getSaleEndDate() {
        return saleEndDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }
}
