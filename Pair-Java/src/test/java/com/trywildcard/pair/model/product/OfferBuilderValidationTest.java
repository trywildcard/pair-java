package com.trywildcard.pair.model.product;

import com.trywildcard.pair.model.CardBuilderException;
import com.trywildcard.pair.util.DummyOffer;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by michaelgarate on 7/1/14.
 */
public class OfferBuilderValidationTest {
    DummyOffer dummyOffer;

    @Before
    public void setUp() throws ParseException {
        dummyOffer = new DummyOffer();
    }

    @Test
    public void isValidWithAttributes(){
        OfferBuilder builder = new OfferBuilder(dummyOffer.price);
        Offer offer = builder.build();

        assertEquals("Price should match", dummyOffer.price, offer.getPrice());
        assertEquals("Number of errors should match", 0, builder.getErrors().size());
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNegativePrice(){
        OfferBuilder builder = new OfferBuilder(-12.99f);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullPrice(){
        Map<Integer, Float> emptyMap = new HashMap<Integer, Float>();
        Float productPrice = emptyMap.get(0);

        OfferBuilder builder = new OfferBuilder(productPrice);
    }

    @Test
    public void hasErrorForEmptyWeightunitsString(){
        OfferBuilder builder = new OfferBuilder(dummyOffer.price);
        builder.weightUnits("");
        assertEquals("Number of errors should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForNegativeWeight(){
        OfferBuilder builder = new OfferBuilder(dummyOffer.price);
        builder.weight(-50f);

        assertEquals("Number of errors should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForNullGeographicAvailability(){
        OfferBuilder builder = new OfferBuilder(dummyOffer.price);
        builder.geographicAvailability(null);
        assertEquals("Number of errors should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForNegativeQuantity(){
        OfferBuilder builder = new OfferBuilder(dummyOffer.price);
        builder.quantity(-5);
        assertEquals("Number of errors should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForEmptyDescriptionString(){
        OfferBuilder builder = new OfferBuilder(dummyOffer.price);
        builder.description("");
        assertEquals("Number of errors should match", 1, builder.getErrors().size());
    }


}
