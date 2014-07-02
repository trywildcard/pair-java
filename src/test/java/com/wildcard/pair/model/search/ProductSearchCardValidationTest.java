package com.wildcard.pair.model.search;

import com.wildcard.pair.model.CardBuilderException;
import com.wildcard.pair.util.DummyOffer;
import com.wildcard.pair.util.DummyProduct;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by michaelgarate on 6/30/14.
 */
public class ProductSearchCardValidationTest {

    DummyProduct dummyProduct;
    DummyOffer dummyOffer;
    ProductSearchResultBuilder builder;
    List<ProductSearchResult> products;

    @Before
    public void setUp() throws ParseException, MalformedURLException {
        dummyProduct = new DummyProduct();
        dummyOffer = new DummyOffer();
        builder = new ProductSearchResultBuilder(dummyProduct.name, dummyProduct.webUrl, dummyOffer.price);
        products = new ArrayList<ProductSearchResult>();
        products.add(builder.build());
    }

    @Test
    public void isValidWithAttributes(){
        ProductSearchCard card = new ProductSearchCard(products, 1);
        assertEquals("Expected name to match", dummyProduct.name, card.getProducts().get(0).getName());
        assertEquals("Expected totalResults to match", 1, card.getTotalResults().intValue());
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullTotalResults(){
        ProductSearchCard card = new ProductSearchCard(products, null);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNegativeTotalResults(){
        ProductSearchCard card = new ProductSearchCard(products, -1);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullProductsList(){
        ProductSearchCard card = new ProductSearchCard(null, 1);
    }

    public void isValidWithEmptyProductsList(){
        List<ProductSearchResult> emptyList = new ArrayList<ProductSearchResult>();
        ProductSearchCard card = new ProductSearchCard(emptyList, 0);
        assertEquals("Expected total results to match", 0, card.getTotalResults().intValue());
    }
}
