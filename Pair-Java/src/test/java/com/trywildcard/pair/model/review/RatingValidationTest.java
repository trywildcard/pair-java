package com.trywildcard.pair.model.review;

import com.trywildcard.pair.exception.CardBuilderException;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by karthiksenthil on 10/5/14.
 */
public class RatingValidationTest {

    Rating rating;

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullAll() throws CardBuilderException {
        rating = new Rating(null, null, null);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullRating() throws CardBuilderException {
        rating = new Rating(null, 0f, 5f);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullMinimum() throws CardBuilderException {
        rating = new Rating(5f, null, 10f);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullMaximum() throws CardBuilderException {
        rating = new Rating(5f, 0f, null);
    }

    @Test
    public void isValidWithValidValues() throws CardBuilderException {
        rating = new Rating(5f, 0f, 10f);
        assertEquals(rating.getRating(), new Float(5));
        assertEquals(rating.getMinimumRating(), new Float(0));
        assertEquals(rating.getMaximumRating(), new Float(10));
    }

    @Test
    public void isValidNullCountConstructor() throws CardBuilderException {
        rating = new Rating(5f, 0f, 10f, null);
        assertEquals(rating.getRating(), new Float(5));
        assertEquals(rating.getMinimumRating(), new Float(0));
        assertEquals(rating.getMaximumRating(), new Float(10));
        assertEquals(rating.getNumberOfRatings(), null);
        assertEquals("Errors size should match", 1, rating.v.getErrors().size());
    }



    @Test
    public void isValidInvalidCount() throws CardBuilderException {
        rating = new Rating(5f, 0f, 10f);
        rating.setNumberOfRatings(-5);
        assertEquals(rating.getRating(), new Float(5));
        assertEquals(rating.getMinimumRating(), new Float(0));
        assertEquals(rating.getMaximumRating(), new Float(10));
        assertEquals(rating.getNumberOfRatings(), null);
        assertEquals("Errors size should match", 1, rating.v.getErrors().size());
    }

    @Test
    public void isValid() throws CardBuilderException {
        rating = new Rating(5f, 0f, 10f);
        rating.setNumberOfRatings(87);
        assertEquals(rating.getRating(), new Float(5));
        assertEquals(rating.getMinimumRating(), new Float(0));
        assertEquals(rating.getMaximumRating(), new Float(10));
        assertEquals(rating.getNumberOfRatings(), new Integer(87));
        assertEquals("Errors size should match", 0, rating.v.getErrors().size());
    }
}
