package com.wildcard.model;

import java.util.Date;
import java.util.List;
import java.util.Locale;

public class OfferBuilder {
    // required fields
    Float price;
    
    // optional fields
    Price originalPrice;
    Price shippingCost;
    String description;
    Availability availability;
    Integer quantity;
    Date saleStartDate;
    Date saleEndDate;
    Date expirationDate;
    List<Locale> geographicAvailability;
    Gender gender;
    Float weight;
    
    public OfferBuilder(Float price){
        this.price = price;
    }
    
    public OfferBuilder gender(Gender gender) {
        this.gender = gender;
        return this;
    }
    
    public OfferBuilder weight(Float weight) {
        this.weight = weight;
        return this;
    }
    
    public OfferBuilder saleStartDate(Date saleStartDate){
        this.saleStartDate = saleStartDate;
        return this;
    }
   
    public OfferBuilder shippingCost(Price shippingCost) {
        this.shippingCost = shippingCost;
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
    
    public OfferBuilder description(String description){
        this.description = description;
        return this;
    }
    
    public OfferBuilder originalPrice(Price originalPrice){
        this.originalPrice = originalPrice;
        return this;
    }
    
    public Offer build(){
        return new Offer(this);
    }
}
