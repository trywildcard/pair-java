package com.wildcard.model;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.junit.Test;

public class OfferBuilderTest {
    private final int FLOAT_COMPARISON_EPSILON = 0; // should be exact, no error since never computed. 
    
    final Currency currency = Currency.getInstance(Locale.US);
    final Price price = new Price(12.99f, currency);
    final Price originalPrice = new Price(18f, currency);
    final Price shippingCost = new Price(2.99f, currency);
    final String description = "6 pack for $12.99";
    final Availability availability = Availability.InStock;
    final Integer quantity = 58;
    final Date saleStartDate = new GregorianCalendar(2014, 06, 23).getTime();
    final Date saleEndDate = new GregorianCalendar(2014, 06, 29).getTime();
    final Date expirationDate = new GregorianCalendar(2014, 06, 29).getTime();
    final List<Locale> geographicAvailability = Arrays
            .asList(Locale.US, Locale.UK);
    final Gender gender = Gender.FEMALE;
    final Float weight = 1.77f;
    
    @Test
    public void testMinimalOffer(){
        OfferBuilder builder = new OfferBuilder(price);
        Offer offer = builder.build();
        
        assertEquals("Price should match", price, offer.getPrice());
    }
    
    @Test
    public void testExtensiveOffer(){
        OfferBuilder builder = new OfferBuilder(price);
        
        builder.originalPrice(originalPrice);
        builder.shippingCost(shippingCost);
        builder.description(description);
        builder.availability(availability);
        builder.quantity(quantity);
        builder.saleStartDate(saleStartDate);
        builder.saleEndDate(saleEndDate);
        builder.expirationDate(expirationDate);
        builder.geographicAvailability(geographicAvailability);
        builder.gender(gender);
        builder.weight(weight);
        
        Offer offer = builder.build();

        assertEquals("Price should match", price, offer.getPrice());
        assertEquals("OriginalPrice should match", originalPrice, offer.getOriginalPrice());
        assertEquals("Shipping cost should match", shippingCost, offer.getShippingCost());
        assertEquals("Description should match", description, offer.getDescription());
        assertEquals("Availability should match", availability, offer.getAvailability());
        assertEquals("Quantity should match", quantity, offer.getQuantity());
        assertEquals("Sale start date should match", saleStartDate, offer.getSaleStartDate());
        assertEquals("Sale end date should match", saleEndDate, offer.getSaleEndDate());
        assertEquals("Expiration date should match", expirationDate, offer.getExpirationDate());
        assertEquals("Geographic Availability should match", geographicAvailability, offer.getGeographicAvailability());
        assertEquals("Gender should match", gender, offer.getGender());
        assertEquals("Weight should match", weight, offer.getWeight(), FLOAT_COMPARISON_EPSILON);
    }
}
