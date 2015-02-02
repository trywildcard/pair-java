package com.trywildcard.pair.model;

import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.model.product.Price;
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
    public void isValidWithAttributes() throws CardBuilderException {
        Price price = new Price(priceVal, currency);
    }

    @Test(expected=CardBuilderException.class)
    public void isInvalidWithNullPriceValue() throws CardBuilderException {
        Price price = new Price(null, currency);
    }

    @Test(expected=CardBuilderException.class)
    public void isInvalidWithNegativePriceValue() throws CardBuilderException {
        Price proce = new Price(-7.00f, currency);
    }

    @Test(expected=CardBuilderException.class)
    public void isInvalidWithNullCurrency() throws CardBuilderException {
        Price price = new Price(priceVal,null);
    }

}
