package com.trywildcard.pair.model.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.model.Builder;
import com.trywildcard.pair.model.Price;
import com.trywildcard.pair.validation.ValidationTool;

import java.util.*;

@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
public class OfferBuilder implements Builder<Offer> {


    private ValidationTool v = new ValidationTool();

    // required fields
    protected Price price;
    
    // optional fields
    protected Price originalPrice;
    protected Price shippingCost;
    protected String description;
    protected Availability availability;
    protected Integer quantity;
    protected Date saleStartDate;
    protected Date saleEndDate;
    protected Date expirationDate;
    protected List<String> geographicAvailability = new ArrayList<String>();
    protected Gender gender;
    protected Float weight;
    protected String weightUnits;

    /**
     * Construct an <code>OfferBuilder</code> provided a Price object.
     * @param price
     */
    public OfferBuilder(Price price) throws CardBuilderException {
        price(price);
    }

    /**
     * Construct an <code>OfferBuilder</code> with only a price value, using USD as the default currency.
     * @param price the price value.
     */
    public OfferBuilder(Float price) throws CardBuilderException {
        price(new Price(price, Currency.getInstance(Locale.US)));
    }

    public OfferBuilder weightUnits(String weightUnits){
        boolean isValid = v.optional(v.notEmpty(weightUnits), "Tried to set weightUnits to an empty string.");
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
        boolean isValid = v.optional(v.notNegative(weight), "Weight must be a positive Float.");
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
        boolean isValid = v.optional(v.notNull(geographicAvailability), "geographicAvailability must not be null.");
        if (isValid) {
            for (Locale locale : geographicAvailability){
                this.geographicAvailability.add(locale.getCountry());
            }
        }
        return this;
    }
    
    public OfferBuilder quantity(Integer quantity){
        boolean isValid = v.optional(v.notNegative(quantity), "quantity must be a positive Integer.");
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
        boolean isValid = v.optional(v.notEmpty(description), "Tried to set description to an empty string.");
        if (isValid) {
            this.description = description;
        }
        return this;
    }
    
    public OfferBuilder originalPrice(Price originalPrice){
        this.originalPrice = originalPrice;
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

    private OfferBuilder price(Price price) throws CardBuilderException {
        v.required(v.notNull(price), "Price must not be null.");
        v.required(v.notNegative(price.getPrice()), "Price must be a positive Float.");

        this.price = price;
        return this;
    }

    @JsonProperty("geographic_availability")
    private OfferBuilder setGeographicAvailability(List<String> geographicAvailability){
        boolean isValid = v.optional(v.notNull(geographicAvailability), "geographicAvailability must not be null.");
        this.geographicAvailability = geographicAvailability;
        return this;
    }
}
