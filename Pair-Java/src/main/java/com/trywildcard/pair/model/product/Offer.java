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
    private final Float weight;
    private final String weightUnits;

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
        this.weight = builder.weight;
        this.weightUnits = builder.weightUnits;
    }

    public String getWeightUnits(){
        return weightUnits;
    }

    public Float getWeight() {
        return weight;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Offer offer = (Offer) o;

        if (availability != offer.availability) return false;
        if (description != null ? !description.equals(offer.description) : offer.description != null) return false;
        if (expirationDate != null ? !expirationDate.equals(offer.expirationDate) : offer.expirationDate != null)
            return false;
        if (geographicAvailability != null ? !geographicAvailability.equals(offer.geographicAvailability) : offer.geographicAvailability != null)
            return false;
        if (originalPrice != null ? !originalPrice.equals(offer.originalPrice) : offer.originalPrice != null)
            return false;
        if (price != null ? !price.equals(offer.price) : offer.price != null) return false;
        if (quantity != null ? !quantity.equals(offer.quantity) : offer.quantity != null) return false;
        if (saleEndDate != null ? !saleEndDate.equals(offer.saleEndDate) : offer.saleEndDate != null) return false;
        if (saleStartDate != null ? !saleStartDate.equals(offer.saleStartDate) : offer.saleStartDate != null)
            return false;
        if (shippingCost != null ? !shippingCost.equals(offer.shippingCost) : offer.shippingCost != null) return false;
        if (weight != null ? !weight.equals(offer.weight) : offer.weight != null) return false;
        if (weightUnits != null ? !weightUnits.equals(offer.weightUnits) : offer.weightUnits != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = price != null ? price.hashCode() : 0;
        result = 31 * result + (originalPrice != null ? originalPrice.hashCode() : 0);
        result = 31 * result + (shippingCost != null ? shippingCost.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (availability != null ? availability.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (saleStartDate != null ? saleStartDate.hashCode() : 0);
        result = 31 * result + (saleEndDate != null ? saleEndDate.hashCode() : 0);
        result = 31 * result + (expirationDate != null ? expirationDate.hashCode() : 0);
        result = 31 * result + (geographicAvailability != null ? geographicAvailability.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (weightUnits != null ? weightUnits.hashCode() : 0);
        return result;
    }
}
