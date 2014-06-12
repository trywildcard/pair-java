package com.wildcard.model;

import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Offer {
    // required fields
    private final Float price;

    // optional fields
    private final Price originalPrice;
    private final Price shippingCost;
    private final String description;
    private final Availability availability;
    private final Integer quantity;
    private final Date saleStartDate;
    private final Date saleEndDate;
    private final Date expirationDate;
    private final List<Locale> geographicAvailability;
    private final Gender gender;
    private final Float weight;
    
    public Offer(OfferBuilder builder){
        this.price = builder.price;
        this.originalPrice = builder.originalPrice;
        this.shippingCost = builder.shippingCost;
        this.description = builder.description;
        this.availability = builder.availability;
        this.quantity = builder.quantity;
        this.saleStartDate = builder.saleStartDate;
        this.saleEndDate = builder.saleEndDate;
        this.expirationDate = builder.expirationDate;
        this.geographicAvailability = builder.geographicAvailability;
        this.gender = builder.gender;
        this.weight = builder.weight;
    }
    

    public Float getWeight() {
        return weight;
    }

    public Gender getGender() {
        return gender;
    }

    public Price getShippingCost() {
        return shippingCost;
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
    
    public String getDescription(){
        return description;
    }
    
    public Price getOriginalPrice(){
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
