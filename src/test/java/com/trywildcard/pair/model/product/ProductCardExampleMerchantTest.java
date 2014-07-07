package com.trywildcard.pair.model.product;

import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.model.Price;
import com.trywildcard.pair.util.DummyOffer;
import com.trywildcard.pair.util.DummyProduct;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

/**
 * Created by michaelgarate on 7/7/14.
 */
public class ProductCardExampleMerchantTest {

    private static DummyOffer dummyOffer;
    private static DummyProduct dummyProduct;

    @BeforeClass
    public static void prepare() throws MalformedURLException, ParseException, CardBuilderException {
        dummyOffer = new DummyOffer();
        dummyProduct = new DummyProduct();
    }

    @Test
    public void buildMinimalProduct() throws IOException {
        ProductCardBuilder productCardBuilder;
        try {
            String url = "http://mystore.com/products/9125";
            productCardBuilder = new ProductCardBuilder("Green shoes", 19.99f, url);
        } catch (Exception e){
            // handle failure
            return;
        }

        ProductCard productCard = productCardBuilder.build();
        System.out.println(productCard.writeAsJsonString());
    }

    @Test
    public void buildStandardProduct() throws IOException {
        Price price;
        OfferBuilder offerBuilder;

        try {
            price = new Price(12.99f, Currency.getInstance(Locale.US));
            offerBuilder = new OfferBuilder(price);
        } catch (CardBuilderException e) {
            // handle failure
            return;
        }

        offerBuilder.weight(-99f); // this will log an error but not throw an exception

        offerBuilder.availability(Availability.InStock); // valid field set
        Offer offer = offerBuilder.build();

        List<Offer> offers = new ArrayList<Offer>();
        offers.add(offer);

        ProductCardBuilder productCardBuilder;

        try {
            String url = "http://myproducts.com/23556";
            productCardBuilder = new ProductCardBuilder("Green shoes", offers, url);
        } catch (Exception e){
            // handle failure
            return;
        }

        productCardBuilder.description(""); // this will log an error but not throw an exception

        String url = "http://myproducts.com/images/23556-1.jpg";
        productCardBuilder.image(url);

        ProductCard productCard = productCardBuilder.build();

        System.out.println(productCard.writeAsJsonString());

        System.err.println(offerBuilder.getErrors());
        System.err.println(productCardBuilder.getErrors());

    }
}
