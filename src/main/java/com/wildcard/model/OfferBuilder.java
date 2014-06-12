package com.wildcard.model;

import java.util.Date;
import java.util.List;

public class OfferBuilder {
    Float price;
    Float originalPrice;
    String currency;
    String description;
    String productUniqueId;
    Availability availability;
    Integer quantity;
    Date saleStartDate;
    Date saleEndDate;
    Date expirationDate;
    List<CountryCode> geographicAvailability;
    
    public OfferBuilder(){
        
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
    
    public OfferBuilder geographicAvailability(List<CountryCode> geographicAvailability){
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
    
    public OfferBuilder currency(String currency){
        this.currency = currency;
        return this;
    }
    
    public OfferBuilder originalPrice(Float originalPrice){
        this.originalPrice = originalPrice;
        return this;
    }
    
    public OfferBuilder price(Float price){
        this.price = price;
        return this;
    }
    
    public Offer build(){
        return new Offer(this);
    }
}
