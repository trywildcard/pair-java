package com.trywildcard.pair.model.product;

import com.trywildcard.pair.Pair;
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
        String url = "http://mystore.com/products/9125";
        try {
            ProductBuilder productBuilder = new ProductBuilder("Green shoes", "Description", "http://image.com");
            ProductCard productCard = new ProductCard(productBuilder.build(), 19.99f, url);
            System.out.println(productCard.writeAsJsonString());
        } catch (Exception e){
            // handle failure
            return;
        }
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

        ProductBuilder productBuilder;

        String productUrl = "http://myproducts.com/23556";

        try {
            String imageUrl = "http://myproducts.com/images/23556-1.jpg";

            productBuilder = new ProductBuilder("Green shoes", "description", imageUrl);
            productBuilder.merchant(""); // this will log an error but not throw an exception
            ProductCard productCard = new ProductCard(productBuilder.build(), offers, productUrl);

            System.out.println(productCard.writeAsJsonString());

            System.err.println(offerBuilder.getErrors());
            System.err.println(productBuilder.getErrors());

        } catch (Exception e){
            // handle failure
            return;
        }

    }
}
