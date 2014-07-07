package com.wildcard.pair.model.search;

import static org.junit.Assert.assertEquals;

import com.wildcard.pair.model.CardType;
import com.wildcard.pair.model.Price;
import com.wildcard.pair.util.DummyOffer;
import com.wildcard.pair.util.DummyProduct;
import org.junit.Assert;
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
 * Test building a product search results card from values in code stubs.
 */


public class ProductSearchCardTest {

    // card attributes
    private static Integer totalResults;
    private static List<ProductSearchResult> products;
    private static DummyProduct dummyProduct;
    private static DummyOffer dummyOffer;
    private static CardType cardType = CardType.PRODUCT_SEARCH;

    @BeforeClass
    public static void initialize() throws MalformedURLException, ParseException {
        dummyProduct = new DummyProduct();
        dummyOffer = new DummyOffer();
        products = new ArrayList<ProductSearchResult>();
    }

    private void testMinimalCardAttributes(ProductSearchCard card){
        Assert.assertEquals("TotalResults should match", totalResults, card.getTotalResults());
        Assert.assertEquals("CardType should match", cardType, card.getCardType());
    }

    @Test
    public void createEmptySearchCardTest() throws IOException {
        this.totalResults = 0;

        ProductSearchCard card = new ProductSearchCard(products, totalResults);
        Assert.assertEquals("Card should have zero products.", 0, card.getProducts().size());
        testMinimalCardAttributes(card);
    }

    @Test
    public void createMinimalSearchCardTest() throws IOException {
        this.totalResults = 1;

        ProductSearchResultBuilder builder = new ProductSearchResultBuilder(dummyProduct.name, dummyProduct.cardUrl, dummyOffer.price);
        builder.image(dummyProduct.images.get(0));
        products.add(builder.build());
        ProductSearchCard card = new ProductSearchCard(products, totalResults);

        testMinimalCardAttributes(card);

        ProductSearchResult productSearchResult = card.getProducts().get(0);

        Assert.assertEquals("Product name should match", productSearchResult.getName(), dummyProduct.name);
        Assert.assertEquals("Product price should match", productSearchResult.getPrice(), dummyOffer.price);
        Assert.assertEquals("Product cardUrl should match", productSearchResult.getCardUrl(), dummyProduct.cardUrl);
        Assert.assertEquals("Product image should match", productSearchResult.getImage(), dummyProduct.images.get(0));
    }

    @Test
    public void cardWithMultipleProductsTest() throws IOException {
        this.totalResults = 2;

        ProductSearchResultBuilder builder = new ProductSearchResultBuilder(dummyProduct.name, dummyProduct.cardUrl, dummyOffer.price);
        builder.image(dummyProduct.images.get(0));
        products.add(builder.build());

        String productName = "My product";
        URL productUrl = new URL("http://examplestore.com/123");
        Price productPrice = new Price(9.99f, Currency.getInstance(Locale.US));

        builder = new ProductSearchResultBuilder(productName, productUrl, productPrice);
        builder.image(new URL("http://examplestore.com/123.jpg"));
        products.add(builder.build());

        ProductSearchCard card = new ProductSearchCard(products, totalResults);

        testMinimalCardAttributes(card);

        Assert.assertEquals("TotalResults should match", totalResults, card.getTotalResults());
        Assert.assertEquals("Products should match", products, card.getProducts());
    }
}