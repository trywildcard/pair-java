package com.wildcard.model;

import java.util.List;

public class OfferBuilder {
    protected float price;
    protected float originalPrice;
    protected String currency;
    protected String description;
    protected String productUniqueId;
    protected Availability availability;
    protected int quantity;
    protected List<CountryCode> geographicAvailability;
    
    public OfferBuilder(){
        
    }
    
    public OfferBuilder geographicAvailability(List<CountryCode> geographicAvailability){
        this.geographicAvailability = geographicAvailability;
        return this;
    }
    
    public OfferBuilder quantity(int quantity){
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
    
    public OfferBuilder originalPrice(float originalPrice){
        this.originalPrice = originalPrice;
        return this;
    }
    
    public OfferBuilder price(float price){
        this.price = price;
        return this;
    }
    
    public Offer build(){
        return new Offer(this);
    }
}
