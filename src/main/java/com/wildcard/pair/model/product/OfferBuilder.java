package com.wildcard.pair.model.product;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.wildcard.pair.model.Builder;
import com.wildcard.pair.model.Price;
import com.wildcard.pair.util.ValidationTool;

import java.util.*;

@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
public class OfferBuilder implements Builder<Offer> {


    private ValidationTool v = new ValidationTool();

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
    List<Locale> geographicAvailability = new ArrayList<Locale>();
    Gender gender;
    Float weight;
    String weightUnits;
    String offerId;

    /**
     * Construct an <code>OfferBuilder</code> provided a Price object.
     * @param price
     */
    public OfferBuilder(Price price){
        price(price);
    }

    /**
     * Construct an <code>OfferBuilder</code> with only a price value, using USD as the default currency.
     * @param price the price value.
     */
    public OfferBuilder(Float price){
        price(new Price(price, Currency.getInstance(Locale.US)));
    }

    public OfferBuilder weightUnits(String weightUnits){
        boolean isValid = v.notEmpty(weightUnits, v.OPTIONAL, "Tried to set weightUnits to an empty string.");
        if (isValid) {
            this.weightUnits = weightUnits;
        }
        return this;
    }

    public OfferBuilder gender(Gender gender) {
        this.gender = gender;
        return this;
    }
    
    public OfferBuilder weight(Float weight) {
        boolean isValid = v.notNegative(weight, v.OPTIONAL, "Weight must be a positive Float.");
        if (isValid) {
            this.weight = weight;
        }
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
        boolean isValid = v.notNull(geographicAvailability, v.OPTIONAL, "geographicAvailability must not be null.");
        if (isValid) {
            this.geographicAvailability = geographicAvailability;
        }
        return this;
    }
    
    public OfferBuilder quantity(Integer quantity){
        boolean isValid = v.notNegative(quantity, v.OPTIONAL, "quantity must be a positive Integer.");
        if (isValid) {
            this.quantity = quantity;
        }
        return this;
    }
    
    public OfferBuilder availability(Availability availability){
        this.availability = availability;
        return this;
    }
    
    public OfferBuilder description(String description){
        boolean isValid = v.notEmpty(description, v.OPTIONAL, "Tried to set description to an empty string.");
        if (isValid) {
            this.description = description;
        }
        return this;
    }
    
    public OfferBuilder originalPrice(Price originalPrice){
        this.originalPrice = originalPrice;
        return this;
    }
    public OfferBuilder offerId(String offerId){
        this.offerId = offerId;
        return this;
    }

    /**
     * Instantiate an <code>Offer</code> with the data in this builder.
     * @return the constructed offer
     */
    public Offer build(){
        return new Offer(this);
    }

    /**
     * Get a list of validation errors.
     * @return the list of errors.
     */
    public List<String> getErrors(){
        return v.getErrors();
    }

    /**
     * Private constructor to allow for Jackson deserialization.
     */
    private OfferBuilder(){}

    private OfferBuilder price(Price price){
        v.notNull(price, v.REQUIRED, "Price must not be null.");
        v.notNegative(price.getPrice(), v.REQUIRED, "Price must be a positive Float.");

        this.price = price;
        return this;
    }
}
