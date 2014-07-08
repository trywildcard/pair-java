package com.trywildcard.pair.model.product;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.trywildcard.pair.model.Price;

import java.util.Collections;
import java.util.Date;
import java.util.List;


@JsonDeserialize(builder = OfferBuilder.class)

/**
 * Structures a product offer. Must be constructed using <code>OfferBuilder</code>
 */
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
    private final List<String> geographicAvailability;
    private final Gender gender;
    private final Float weight;
    private final String weightUnits;
    private final String offerId;

    /**
     * Construct an offer using an <code>OfferBuilder</code>, which is responsible for validations.
     * @param builder the builder for this offer.
     */
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
        this.offerId = builder.offerId;
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
    
    public List<String> getGeographicAvailability(){
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
        if (saleStartDate == null){
            return null;
        }

        return new Date(saleStartDate.getTime());
    }

    public Date getSaleEndDate() {
        if (saleEndDate == null){
            return null;
        }

        return new Date(saleEndDate.getTime());
    }

    public Date getExpirationDate() {
        if (expirationDate == null){
            return null;
        }

        return new Date(expirationDate.getTime());
    }

    public String getOfferId() {
        return offerId;
    }

}
