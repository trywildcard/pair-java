package com.trywildcard.pair.model.product;

import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.extraction.MetaTagModel;
import com.trywildcard.pair.util.DummyOffer;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by michaelgarate on 7/1/14.
 */
public class OfferBuilderValidationTest {
    DummyOffer dummyOffer;

    @Before
    public void setUp() throws ParseException, CardBuilderException {
        dummyOffer = new DummyOffer();
    }

    @Test
    public void isValidWithAttributes() throws CardBuilderException {
        OfferBuilder builder = new OfferBuilder(dummyOffer.price);
        Offer offer = builder.build();

        assertEquals("Price should match", dummyOffer.price, offer.getPrice());
        assertEquals("Number of errors should match", 0, builder.getErrors().size());
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNegativePrice() throws CardBuilderException {
        OfferBuilder builder = new OfferBuilder(-12.99f);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullPrice() throws CardBuilderException {
        Map<Integer, Float> emptyMap = new HashMap<Integer, Float>();
        Float productPrice = emptyMap.get(0);

        OfferBuilder builder = new OfferBuilder(productPrice);
    }

    @Test
    public void hasErrorForEmptyWeightunitsString() throws CardBuilderException {
        OfferBuilder builder = new OfferBuilder(dummyOffer.price);
        builder.weightUnits("");
        assertEquals("Number of errors should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForNegativeWeight() throws CardBuilderException {
        OfferBuilder builder = new OfferBuilder(dummyOffer.price);
        builder.weight(-50f);

        assertEquals("Number of errors should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForNullGeographicAvailability() throws CardBuilderException {
        OfferBuilder builder = new OfferBuilder(dummyOffer.price);
        builder.geographicAvailability(null);
        assertEquals("Number of errors should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForNegativeQuantity() throws CardBuilderException {
        OfferBuilder builder = new OfferBuilder(dummyOffer.price);
        builder.quantity(-5);
        assertEquals("Number of errors should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForEmptyDescriptionString() throws CardBuilderException {
        OfferBuilder builder = new OfferBuilder(dummyOffer.price);
        builder.description("");
        assertEquals("Number of errors should match", 1, builder.getErrors().size());
    }

    @Test(expected = CardBuilderException.class)
    public void nullMetaTagModel() throws CardBuilderException {
        MetaTagModel metaTagModel = null;
        Offer offer = new OfferBuilder(metaTagModel).build();
    }

    @Test(expected = CardBuilderException.class)
    public void inCompleteMetaTagModelNull() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getPrice()).thenReturn(null);

        Offer offer = new OfferBuilder(metaTagModel).build();
    }

    @Test(expected = CardBuilderException.class)
    public void inValidMetaTagPrice() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getPrice()).thenReturn("Not Valid Integer");

        Offer offer = new OfferBuilder(metaTagModel).build();
    }

    @Test
    public void validMetaTagPrice() throws CardBuilderException {
        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getPrice()).thenReturn("15");

        Offer offer = new OfferBuilder(metaTagModel).build();
        assertEquals(offer.getPrice().getPrice(), new Float(15f));
    }

}
