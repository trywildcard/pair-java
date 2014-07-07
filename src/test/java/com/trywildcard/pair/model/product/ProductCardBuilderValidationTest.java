package com.trywildcard.pair.model.product;

import com.trywildcard.pair.model.CardBuilderException;
import com.trywildcard.pair.util.DummyOffer;
import com.trywildcard.pair.util.DummyProduct;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by michaelgarate on 7/1/14.
 */
public class ProductCardBuilderValidationTest {

    DummyProduct dummyProduct;
    DummyOffer dummyOffer;
    List<Offer> offers = new ArrayList<Offer>();
    ProductCardBuilder builder;

    @Before
    public void setUp() throws ParseException {
        dummyProduct = new DummyProduct();
        dummyOffer = new DummyOffer();
        builder = new ProductCardBuilder(dummyProduct.name, dummyOffer.price.getPrice(), dummyProduct.webUrl);
    }

    @Test
    public void isValidWithAttributes(){
        assertEquals(0,builder.getErrors().size());
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullName(){
        ProductCardBuilder builder = new ProductCardBuilder(null, dummyOffer.price.getPrice(), dummyProduct.webUrl);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithEmptyNameString(){
        ProductCardBuilder builder = new ProductCardBuilder("", dummyOffer.price.getPrice(), dummyProduct.webUrl);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithEmptyOffersList(){
        List<Offer> emptyList = new ArrayList<Offer>();
        ProductCardBuilder builder = new ProductCardBuilder(dummyProduct.name, emptyList, dummyProduct.webUrl);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithOnlyNullOffersListItems(){
        List<Offer> offers = new ArrayList<Offer>();
        offers.add(null);
        offers.add(null);

        ProductCardBuilder builder = new ProductCardBuilder(dummyProduct.name, offers, dummyProduct.webUrl);
    }

    @Test
    public void isValidWithSomeNullOffersListItems(){
        List<Offer> offers = new ArrayList<Offer>();
        offers.add(null);
        offers.add(new OfferBuilder(12.99f).build());
        offers.add(null);

        ProductCardBuilder builder = new ProductCardBuilder(dummyProduct.name, offers, dummyProduct.webUrl);

        assertEquals("Errors size should match", 2, builder.getErrors().size());
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
    public void hasErrorForNullImageUrl(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.image(null);
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForNullImagesList(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.images(null);
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }

    @Test
    public void hasErrorForNullImagesListItem(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        List<String> images = new ArrayList<String>();
        images.add(null);
        builder.images(images);
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

    @Test
    public void hasErrorForEmptyProductIdString(){
        assertEquals("Errors size should match", 0, builder.getErrors().size());
        builder.productId("");
        assertEquals("Errors size should match", 1, builder.getErrors().size());
    }


}

