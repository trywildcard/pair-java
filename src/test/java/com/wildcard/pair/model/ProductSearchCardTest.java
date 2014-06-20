package com.wildcard.pair.model;

import static org.junit.Assert.assertEquals;

import com.wildcard.pair.model.search.ProductSearchCard;
import com.wildcard.pair.model.search.SearchProduct;
import com.wildcard.pair.model.search.SearchProductBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

/**
 * Created by michaelgarate on 6/16/14.
 */


public class ProductSearchCardTest {

    // card attributes
    private static Integer totalResults;
    private static List<SearchProduct> products;
    private static DummyProduct dummyProduct;
    private static DummyOffer dummyOffer;
    private static CardType cardType = CardType.PRODUCT_SEARCH;

    @BeforeClass
    public static void initialize() throws MalformedURLException, ParseException {
        dummyProduct = new DummyProduct();
        dummyOffer = new DummyOffer();
        products = new ArrayList<SearchProduct>();
    }

    private void testMinimalCardAttributes(ProductSearchCard card){
        assertEquals("TotalResults should match", totalResults, card.getTotalResults());
        assertEquals("CardType should match", cardType, card.getCardType());
    }

    @Test
    public void createEmptySearchCardTest() throws IOException {
        this.totalResults = 0;

        ProductSearchCard card = new ProductSearchCard(products, totalResults);
        assertEquals("Card should have zero products.",0,card.getProducts().size());
        testMinimalCardAttributes(card);
    }

    @Test
    public void createMinimalSearchCardTest() throws IOException {
        this.totalResults = 1;

        SearchProductBuilder builder = new SearchProductBuilder(dummyProduct.name, dummyProduct.url, dummyOffer.price);
        builder.image(dummyProduct.images.get(0));
        products.add(builder.build());
        ProductSearchCard card = new ProductSearchCard(products, totalResults);

        testMinimalCardAttributes(card);

        SearchProduct searchProduct = card.getProducts().get(0);

        assertEquals("Product name should match", searchProduct.getName(), dummyProduct.name);
        assertEquals("Product price should match", searchProduct.getPrice(), dummyOffer.price);
        assertEquals("Product url should match", searchProduct.getUrl(), dummyProduct.url);
        assertEquals("Product image should match", searchProduct.getImage(), dummyProduct.images.get(0));
    }

    @Test
    public void cardWithMultipleProductsTest() throws IOException {
        this.totalResults = 2;

        SearchProductBuilder builder = new SearchProductBuilder(dummyProduct.name, dummyProduct.url, dummyOffer.price);
        builder.image(dummyProduct.images.get(0));
        products.add(builder.build());

        String productName = "My product";
        URL productUrl = new URL("http://etsy.com/123");
        Price productPrice = new Price(9.99f, Currency.getInstance(Locale.US));

        builder = new SearchProductBuilder(productName, productUrl, productPrice);
        builder.image(new URL("http://etsy.com/123.jpg"));
        products.add(builder.build());

        ProductSearchCard card = new ProductSearchCard(products, totalResults);

        testMinimalCardAttributes(card);

        assertEquals("TotalResults should match", totalResults, card.getTotalResults());
        assertEquals("Products should match", products, card.getProducts());
    }
}