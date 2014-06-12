package com.wildcard.model;

import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class OfferBuilder {
    Float price;
    Float originalPrice;
    Currency currency;
    String description;
    String productUniqueId;
    Availability availability;
    Integer quantity;
    Date saleStartDate;
    Date saleEndDate;
    Date expirationDate;
    List<Locale> geographicAvailability;
    
    public OfferBuilder(Float price){
        this.price = price;
    }
    
    public OfferBuilder saleStartDate(Date saleStartDate){
        this.saleStartDate = saleStartDate;
        return this;
    }
    
    public OfferBuilder saleEndDate(Date saleEndDate){
        this.saleEndDate = saleEndDate;
        return this;
    }
    
    public OfferBuilder expirationDate(Date expirationDate){
        this.expirationDate = expirationDate;
        return this;
    }
    
    public OfferBuilder geographicAvailability(List<Locale> geographicAvailability){
        this.geographicAvailability = geographicAvailability;
        return this;
    }
    
    public OfferBuilder quantity(Integer quantity){
        this.quantity = quantity;
        return this;
    }
    
    public OfferBuilder availability(Availability availability){
        this.availability = availability;
        return this;
    }
    
    public OfferBuilder productUniqueId(String productUniqueId){
        this.productUniqueId = productUniqueId;
        return this;
    }
    
    public OfferBuilder description(String description){
        this.description = description;
        return this;
    }
    
    public OfferBuilder currency(Currency currency){
        this.currency = currency;
        return this;
    }
    
    public OfferBuilder originalPrice(Float originalPrice){
        this.originalPrice = originalPrice;
        return this;
    }
    
    public Offer build(){
        return new Offer(this);
    }
}
