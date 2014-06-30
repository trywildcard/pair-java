package com.wildcard.pair.model.product;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import com.wildcard.pair.util.DummyOffer;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wildcard.pair.model.product.Offer;
import com.wildcard.pair.model.product.OfferBuilder;
import com.wildcard.pair.util.TestUtil;

/**
 * Test building an offer, using an <code>OfferBuilder</code>, from values in code stubs.
 */
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
        
        Assert.assertEquals("Price should match", dummyOffer.price, offer.getPrice());
    }
    
    @Test
    public void testMinimalOfferWithMinimalConstructor(){
        OfferBuilder builder = new OfferBuilder(dummyOffer.price.getPrice());
        Offer offer = builder.build();
        
        Assert.assertEquals("Price should match", dummyOffer.price.getPrice(), offer.getPrice().getPrice());
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

        Assert.assertEquals("Price should match", dummyOffer.price, offer.getPrice());
        Assert.assertEquals("OriginalPrice should match", dummyOffer.originalPrice, offer.getOriginalPrice());
        Assert.assertEquals("Shipping cost should match", dummyOffer.shippingCost, offer.getShippingCost());
        Assert.assertEquals("Description should match", dummyOffer.description, offer.getDescription());
        Assert.assertEquals("Availability should match", dummyOffer.availability, offer.getAvailability());
        Assert.assertEquals("Quantity should match", dummyOffer.quantity, offer.getQuantity());
        Assert.assertEquals("Sale start date should match", dummyOffer.saleStartDate, offer.getSaleStartDate());
        Assert.assertEquals("Sale end date should match", dummyOffer.saleEndDate, offer.getSaleEndDate());
        Assert.assertEquals("Expiration date should match", dummyOffer.expirationDate, offer.getExpirationDate());
        Assert.assertEquals("Geographic Availability should match", dummyOffer.geographicAvailability, offer.getGeographicAvailability());
        Assert.assertEquals("Gender should match", dummyOffer.gender, offer.getGender());
        Assert.assertEquals("Weight should match", dummyOffer.weight, offer.getWeight(), TestUtil.FLOAT_EXACT_COMPARISON_EPSILON);
        Assert.assertEquals("WeightUnits should match", dummyOffer.weightUnits, offer.getWeightUnits());
    }
    
}
