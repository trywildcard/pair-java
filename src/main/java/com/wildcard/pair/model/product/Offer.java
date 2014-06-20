package com.wildcard.pair.model.product;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wildcard.pair.model.Price;

@JsonDeserialize(builder = OfferBuilder.class)
public final class Offer {
    // required fields
    private final Price price;

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
    private final String weightUnits;
    
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
        this.geographicAvailability = Collections.unmodifiableList(builder.geographicAvailability);
        this.gender = builder.gender;
        this.weight = builder.weight;
        this.weightUnits = builder.weightUnits;
    }
    
    public String getWeightUnits(){
        return weightUnits;
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
    
    public Price getPrice(){
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
