package com.trywildcard.pair.model.product;

import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.util.DummyOffer;
import com.trywildcard.pair.util.DummyProduct;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by michaelgarate on 7/1/14.
 */
public class ProductBuilderValidationTest {

    DummyProduct dummyProduct;
    DummyOffer dummyOffer;
    List<Offer> offers = new ArrayList<Offer>();
    ProductBuilder builder;

    @Before
    public void setUp() throws ParseException, CardBuilderException {
        dummyProduct = new DummyProduct();
        dummyOffer = new DummyOffer();
        builder = new ProductBuilder(dummyProduct.name, dummyProduct.images);
    }

    @Test
    public void isValidWithAttributes(){
        assertEquals(0,builder.getErrors().size());
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithEmptyNameString() throws CardBuilderException {
        Product product = new ProductBuilder("", dummyProduct.images).build();
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullImages() throws CardBuilderException {
        List<String> images = null;
        Product product = new ProductBuilder(dummyProduct.name, images).build();
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithEmptyImages() throws CardBuilderException {
        Product product = new ProductBuilder(dummyProduct.name, Collections.EMPTY_LIST).build();
    }

    @Test
    public void isValidWithOneImage() throws CardBuilderException {
        Product product = new ProductBuilder(dummyProduct.name, dummyProduct.imgUrl).build();
        assertEquals(product.getName(), dummyProduct.name);
        assertEquals(product.getImages().size(), 1);
        assertEquals(product.getImages().get(0).toString(), dummyProduct.imgUrl);
    }

    @Test
    public void isValidWithMultipleImages() throws CardBuilderException {
        Product product = new ProductBuilder(dummyProduct.name, dummyProduct.images).build();
        assertEquals(product.getName(), dummyProduct.name);
        assertEquals(product.getImages().size(), 4);
    }

    @Test
    public void hasErrorForEmptyAppLinkAndroidString(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.appLinkAndroid("");
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForEmptyAppLinkIosString(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.appLinkIos("");
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForEmptyModelString(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.model("");
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForNullSizesList(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.sizes(null);
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }


    @Test
    public void hasErrorForNullSizesMapValue(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());

        Map<String, String> sizes = new HashMap<String, String>();
        sizes.put("key", null);
        builder.sizes(sizes);

        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForBlankOptionString(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.option("");
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForNullOptionsList(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.sizes(null);

        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForNullOptionsListItem(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());

        List<String> options = new ArrayList<String>();
        options.add(null);
        builder.options(options);

        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForNullRelatedItem(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.relatedItem(null);
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForNullRelatedItemsList(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.relatedItems(null);
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForNullRelatedItemsListItem(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        List<String> relatedItems = new ArrayList<String>();
        relatedItems.add(null);
        builder.relatedItems(relatedItems);
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }


    @Test
    public void hasErrorForNullReferencedItem(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.referencedItem(null);
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForNullReferencedItemsList(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.referencedItems(null);
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForNullReferencedItemsListItem(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        List<String> referencedItems = new ArrayList<String>();
        referencedItems.add(null);
        builder.referencedItems(referencedItems);
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForNegativeRatingCount() {
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.ratingCount(-5);
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForNullColor(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.color(null);
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForNullColorList(){

        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.colors(null);
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForNullColorListItem(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        List<ProductColor> colors = new ArrayList<ProductColor>();
        colors.add(null);
        builder.colors(colors);
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }


    @Test
    public void hasErrorForEmptyDescriptionString(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.description("");
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForEmptyBrandString(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.brand("");
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForEmptyMerchantString(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.merchant("");
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }


}

