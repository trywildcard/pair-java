package com.wildcard.pair.model.search;

import com.wildcard.pair.model.CardBuilderException;
import com.wildcard.pair.util.DummyOffer;
import com.wildcard.pair.util.DummyProduct;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.text.ParseException;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by michaelgarate on 6/30/14.
 */
public class ProductSearchResultBuilderValidationTest {

    DummyProduct dummyProduct;
    DummyOffer dummyOffer;

    @Before
    public void setUp() throws ParseException, MalformedURLException {
        this.dummyOffer = new DummyOffer();
        this.dummyProduct = new DummyProduct();
    }

    @Test
    public void isValidWithAttributes(){
        ProductSearchResultBuilder builder = new ProductSearchResultBuilder(dummyProduct.name, dummyProduct.webUrl, dummyOffer.price);
        assertEquals(dummyProduct.name, builder.build().getName());
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullName(){
        ProductSearchResultBuilder builder = new ProductSearchResultBuilder(null, dummyProduct.webUrl, dummyOffer.price);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithEmptyNameString(){
        ProductSearchResultBuilder builder = new ProductSearchResultBuilder("", dummyProduct.webUrl, dummyOffer.price);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullUrl(){
        ProductSearchResultBuilder builder = new ProductSearchResultBuilder(dummyProduct.name, null, dummyOffer.price);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullPrice(){
        ProductSearchResultBuilder builder = new ProductSearchResultBuilder(dummyProduct.name, dummyProduct.webUrl, null);
    }
}
