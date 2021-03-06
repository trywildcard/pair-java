package com.trywildcard.pair.util;

import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.model.product.Price;
import com.trywildcard.pair.model.product.Availability;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Offer stub with data supporting Junit tests.
 */
public class DummyOffer {
    
    final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    public final String description = "Primary offer";
    public final Price originalPrice = new Price(17.99f, Currency.getInstance(Locale.US));
    public final Price shippingCost = new Price(1.50f, Currency.getInstance(Locale.US));
    public final Integer quantity = 15;
    public final Date saleStartDate;
    public final Date saleEndDate;
    public final Date expirationDate;
    public final List<Locale> geographicAvailability = Arrays.asList(Locale.US);
    public final Float weight = 17.0f;
    public final String weightUnits = "oz";
    public final Availability availability = Availability.InStock;
    public final Price price = new Price(7.99f, Currency.getInstance(Locale.US));
    public final String offerId = "36226783-3";

    public DummyOffer() throws ParseException, CardBuilderException {
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        saleStartDate = dateFormat.parse("2014-06-01");
        saleEndDate = dateFormat.parse("2014-06-24");
        expirationDate = dateFormat.parse("2014-07-13");
    }
}
