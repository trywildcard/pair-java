package com.wildcard.pair.model;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.wildcard.pair.model.product.Offer;
import com.wildcard.pair.model.product.OfferBuilder;
import com.wildcard.pair.testUtil.TestUtil;

public class OfferBuilderTest {
    
    private static DummyOffer dummyOffer;
    
    @BeforeClass
    public static void initialize() throws ParseException{
        dummyOffer = new DummyOffer();
    }
    
    @Test
    public void testMinimalOffer(){
        OfferBuilder builder = new OfferBuilder(dummyOffer.price);
        Offer offer = builder.build();
        
        assertEquals("Price should match", dummyOffer.price, offer.getPrice());
    }
    
    @Test
    public void testMinimalOfferWithMinimalConstructor(){
        OfferBuilder builder = new OfferBuilder(dummyOffer.price.getPrice());
        Offer offer = builder.build();
        
        assertEquals("Price should match", dummyOffer.price.getPrice(), offer.getPrice().getPrice());
    }
    
    @Test
    public void testExtensiveOffer(){
        OfferBuilder builder = new OfferBuilder(dummyOffer.price);
        
        builder.originalPrice(dummyOffer.originalPrice);
        builder.shippingCost(dummyOffer.shippingCost);
        builder.description(dummyOffer.description);
        builder.availability(dummyOffer.availability);
        builder.quantity(dummyOffer.quantity);
        builder.saleStartDate(dummyOffer.saleStartDate);
        builder.saleEndDate(dummyOffer.saleEndDate);
        builder.expirationDate(dummyOffer.expirationDate);
        builder.geographicAvailability(dummyOffer.geographicAvailability);
        builder.gender(dummyOffer.gender);
        builder.weight(dummyOffer.weight);
        builder.weightUnits(dummyOffer.weightUnits);
        
        Offer offer = builder.build();

        assertEquals("Price should match", dummyOffer.price, offer.getPrice());
        assertEquals("OriginalPrice should match", dummyOffer.originalPrice, offer.getOriginalPrice());
        assertEquals("Shipping cost should match", dummyOffer.shippingCost, offer.getShippingCost());
        assertEquals("Description should match", dummyOffer.description, offer.getDescription());
        assertEquals("Availability should match", dummyOffer.availability, offer.getAvailability());
        assertEquals("Quantity should match", dummyOffer.quantity, offer.getQuantity());
        assertEquals("Sale start date should match", dummyOffer.saleStartDate, offer.getSaleStartDate());
        assertEquals("Sale end date should match", dummyOffer.saleEndDate, offer.getSaleEndDate());
        assertEquals("Expiration date should match", dummyOffer.expirationDate, offer.getExpirationDate());
        assertEquals("Geographic Availability should match", dummyOffer.geographicAvailability, offer.getGeographicAvailability());
        assertEquals("Gender should match", dummyOffer.gender, offer.getGender());
        assertEquals("Weight should match", dummyOffer.weight, offer.getWeight(), TestUtil.FLOAT_EXACT_COMPARISON_EPSILON);
        assertEquals("WeightUnits should match", dummyOffer.weightUnits, offer.getWeightUnits());
    }
    
}
