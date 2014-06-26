package com.wildcard.pair.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import com.wildcard.pair.model.product.Availability;
import com.wildcard.pair.model.product.Gender;

/**
 * Offer stub with data supporting Junit tests.
 */
public class DummyOffer {
    
    final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    final String description = "Primary offer";
    final Price originalPrice = new Price(17.99f, Currency.getInstance(Locale.US));
    final Price shippingCost = new Price(1.50f, Currency.getInstance(Locale.US));
    final Integer quantity = 15;
    final Date saleStartDate;
    final Date saleEndDate;
    final Date expirationDate;
    final List<Locale> geographicAvailability = Arrays.asList(Locale.US);
    final Gender gender = Gender.UNISEX;
    final Float weight = 17.0f;
    final String weightUnits = "oz";
    final Availability availability = Availability.InStock;
    final Price price = new Price(7.99f, Currency.getInstance(Locale.US));
    final String offerId = "36226783-3";

    public DummyOffer() throws ParseException{
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        saleStartDate = dateFormat.parse("2014-06-01");
        saleEndDate = dateFormat.parse("2014-06-24");
        expirationDate = dateFormat.parse("2014-07-13");
    }
}
