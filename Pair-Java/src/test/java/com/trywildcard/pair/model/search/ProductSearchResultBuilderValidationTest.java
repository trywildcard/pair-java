package com.trywildcard.pair.model.search;

import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.util.DummyOffer;
import com.trywildcard.pair.util.DummyProduct;
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
    public void setUp() throws ParseException, MalformedURLException, CardBuilderException {
        this.dummyOffer = new DummyOffer();
        this.dummyProduct = new DummyProduct();
    }

    @Test
    public void isValidWithAttributes() throws CardBuilderException {
        ProductSearchResultBuilder builder = new ProductSearchResultBuilder(dummyProduct.name, dummyProduct.webUrl, dummyOffer.price, dummyProduct.imgUrl);
        assertEquals(dummyProduct.name, builder.build().getName());
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullName() throws CardBuilderException {
        ProductSearchResultBuilder builder = new ProductSearchResultBuilder(null, dummyProduct.webUrl, dummyOffer.price, dummyProduct.imgUrl);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithEmptyNameString() throws CardBuilderException {
        ProductSearchResultBuilder builder = new ProductSearchResultBuilder("", dummyProduct.webUrl, dummyOffer.price, dummyProduct.imgUrl);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullUrl() throws CardBuilderException {
        ProductSearchResultBuilder builder = new ProductSearchResultBuilder(dummyProduct.name, null, dummyOffer.price, dummyProduct.imgUrl);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithEmptyUrl() throws CardBuilderException {
        ProductSearchResultBuilder builder = new ProductSearchResultBuilder(dummyProduct.name, "", dummyOffer.price, dummyProduct.imgUrl);
    }


    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullPrice() throws CardBuilderException {
        ProductSearchResultBuilder builder = new ProductSearchResultBuilder(dummyProduct.name, dummyProduct.webUrl, null, dummyProduct.imgUrl);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullImageUrl() throws CardBuilderException {
        ProductSearchResultBuilder builder = new ProductSearchResultBuilder(dummyProduct.name, dummyProduct.webUrl, dummyOffer.price, null);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithEmptyImageUrl() throws CardBuilderException {
        ProductSearchResultBuilder builder = new ProductSearchResultBuilder(dummyProduct.name, dummyProduct.webUrl, dummyOffer.price, "");
    }
}
