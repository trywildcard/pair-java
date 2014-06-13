package com.wildcard.model.product;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.wildcard.model.util.ValidationTool;

@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
public class OfferBuilder {
    // required fields
    Price price;
    
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
    String weightUnits;
    
    public OfferBuilder(Price price){
        price(price);
    }
    
    public OfferBuilder weightUnits(String weightUnits){
        this.weightUnits = weightUnits;
        return this;
    }
    
    public OfferBuilder gender(Gender gender) {
        this.gender = gender;
        return this;
    }
    
    public OfferBuilder weight(Float weight) {
        ValidationTool.notNegative(weight, "Weight must be a positive Float.");
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
        ValidationTool.notNegative(quantity,  "quantity must be a positive Integer.");
        this.quantity = quantity;
        return this;
    }
    
    public OfferBuilder availability(Availability availability){
        this.availability = availability;
        return this;
    }
    
    public OfferBuilder description(String description){
        ValidationTool.notEmpty(description, "Tried to set description to an empty string.");
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
    

    /*
     * The following private constructor and private methods are required by Jackson. 
     */
    
    private OfferBuilder(){}
    
    private OfferBuilder price(Price price){
        ValidationTool.notNull(price, "Price must not be null.");
        ValidationTool.notNegative(price.getPrice(), "Price must be a positive Float.");
        
        this.price = price;
        return this;
    }
}
