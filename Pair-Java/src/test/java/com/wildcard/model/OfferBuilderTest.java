package com.wildcard.model;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;

public class OfferBuilderTest {
    private final int FLOAT_COMPARISON_EPSILON = 0; // prices should be exact, no error since never computed. 
    
    final Float price = 12.99f;
    final Float originalPrice = 18f;
    final String currency = "USD";
    final String description = "6 pack for $12.99";
    final String productUniqueId = "AK-91578379523829734";
    final Availability availability = Availability.InStock;
    final Integer quantity = 58;
    final Date saleStartDate = new GregorianCalendar(2014, 06, 23).getTime();
    final Date saleEndDate = new GregorianCalendar(2014, 06, 29).getTime();
    final Date expirationDate = new GregorianCalendar(2014, 06, 29).getTime();
    final List<CountryCode> geographicAvailability = Arrays
            .asList(CountryCode.US,
                    CountryCode.EU);
    
    @Test
    public void testMinimalOffer(){
        OfferBuilder builder = new OfferBuilder();
        builder.price(price);
        Offer offer = builder.build();
        
        assertEquals("Price should match", price, offer.getPrice(), FLOAT_COMPARISON_EPSILON);
    }
    
    @Test
    public void testExtensiveOffer(){
        OfferBuilder builder = new OfferBuilder();
        
        builder.price(price);
        builder.originalPrice(originalPrice);
        builder.currency(currency);
        builder.description(description);
        builder.productUniqueId(productUniqueId);
        builder.availability(availability);
        builder.quantity(quantity);
        builder.saleStartDate(saleStartDate);
        builder.saleEndDate(saleEndDate);
        builder.expirationDate(expirationDate);
        builder.geographicAvailability(geographicAvailability);
        
        Offer offer = builder.build();

        assertEquals("Price should match", price, offer.getPrice(), FLOAT_COMPARISON_EPSILON);
        assertEquals("OriginalPrice should match", originalPrice, offer.getOriginalPrice(), FLOAT_COMPARISON_EPSILON);
        assertEquals("Currency should match", currency, offer.getCurrency());
        assertEquals("Description should match", description, offer.getDescription());
        assertEquals("ProductUniqueId should match", productUniqueId, offer.getProductUniqueId());
        assertEquals("Availability should match", availability, offer.getAvailability());
        assertEquals("Quantity should match", quantity, offer.getQuantity());
        assertEquals("Sale start date should match", saleStartDate, offer.getSaleStartDate());
        assertEquals("Sale end date should match", saleEndDate, offer.getSaleEndDate());
        assertEquals("Expiration date should match", expirationDate, offer.getExpirationDate());
        assertEquals("Geographic Availability should match", geographicAvailability, offer.getGeographicAvailability());
    }
}
