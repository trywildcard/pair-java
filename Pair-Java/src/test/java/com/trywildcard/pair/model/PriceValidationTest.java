package com.trywildcard.pair.model;

import org.junit.Test;

import java.util.Currency;
import java.util.Locale;

/**
 * Created by michaelgarate on 6/30/14.
 */


public class PriceValidationTest {

    Float priceVal = 12.99f;
    Currency currency = Currency.getInstance(Locale.US);

    @Test
    public void isValidWithAttributes(){
        Price price = new Price(priceVal, currency);
    }

    @Test(expected=CardBuilderException.class)
    public void isInvalidWithNullPriceValue(){
        Price price = new Price(null, currency);
    }

    @Test(expected=CardBuilderException.class)
    public void isInvalidWithNegativePriceValue(){
        Price proce = new Price(-7.00f, currency);
    }

    @Test(expected=CardBuilderException.class)
    public void isInvalidWithNullCurrency(){
        Price price = new Price(priceVal,null);
    }

}
