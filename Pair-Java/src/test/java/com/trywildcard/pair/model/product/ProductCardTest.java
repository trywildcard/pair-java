package com.trywildcard.pair.model.product;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.model.creator.Creator;
import com.trywildcard.pair.model.creator.CreatorBuilderTest;
import com.trywildcard.pair.util.DummyAbstractCard;
import com.trywildcard.pair.util.DummyOffer;
import com.trywildcard.pair.util.DummyProduct;
import com.trywildcard.pair.util.TestUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ProductCardTest {

    ObjectMapper mapper = TestUtil.getObjectMapper();
    private static DummyOffer dummyOffer;
    private static DummyProduct dummyProduct;
    private static DummyAbstractCard dummyAbstractCard;

    @BeforeClass
    public static void prepare() throws ParseException, CardBuilderException {
        dummyOffer = new DummyOffer();
        dummyProduct = new DummyProduct();
        dummyAbstractCard = new DummyAbstractCard();
    }

    private void testMinimalCardAttributes(ProductCard card){
        Assert.assertEquals("Product Name should match", dummyProduct.name, card.getProduct().getName());
        Assert.assertEquals("Web url should match", dummyProduct.webUrl, card.getWebUrl().toString());

        Assert.assertEquals("Price should match", dummyOffer.price.getPrice(), card.getOffers().get(0).getPrice().getPrice());
    }

    private ProductCard buildMinimalProductCard() throws CardBuilderException {

        Offer offer = new OfferBuilder(dummyOffer.price).build();
        Product product = new ProductBuilder(dummyProduct.name, dummyProduct.description, dummyProduct.images).build();

        ProductCard productCard = new ProductCard(product, offer, dummyProduct.webUrl);

        return productCard;
    }

    @Test
    public void testMinimalProductCard() throws JsonProcessingException, CardBuilderException {
        ProductCard card = buildMinimalProductCard();
        testMinimalCardAttributes(card);
    }


    @Test
    public void testMinimalProductWithMinimalConstructor() throws CardBuilderException {
        Product product = new ProductBuilder(dummyProduct.name, dummyProduct.description, dummyProduct.images).build();
        ProductCard card = new ProductCard(product, dummyOffer.price.getPrice(), dummyProduct.webUrl);
        testMinimalCardAttributes(card);
    }

    private Offer buildExtensiveOffer() throws CardBuilderException {
        OfferBuilder builder = new OfferBuilder(dummyOffer.price);
        builder.originalPrice(dummyOffer.originalPrice);
        builder.description(dummyOffer.description);
        builder.availability(dummyOffer.availability);
        builder.shippingCost(dummyOffer.shippingCost);
        builder.quantity(dummyOffer.quantity);
        builder.saleStartDate(dummyOffer.saleStartDate);
        builder.saleEndDate(dummyOffer.saleEndDate);
        builder.expirationDate(dummyOffer.expirationDate);
        builder.geographicAvailability(dummyOffer.geographicAvailability);
        builder.weight(dummyOffer.weight);
        builder.weightUnits(dummyOffer.weightUnits);

        return builder.build();
    }


    @Test
    public void testExtensiveWriteAsJsonMethod() throws JsonParseException, JsonMappingException, IOException, CardBuilderException, ParseException {
        String inputString = TestUtil.readResourceAsString("example_product_card.json");
        ProductCard fixtureCard = mapper.readValue(inputString,  ProductCard.class);
        Product generatedProduct = buildExtensiveProduct();
        Offer generatedOffer = buildExtensiveOffer();

        ProductCard generatedCard = new ProductCard(generatedProduct, generatedOffer, dummyProduct.webUrl);
        generatedCard.setKeywords(dummyAbstractCard.keywords);
        generatedCard.setAppLinkIos(dummyAbstractCard.appLinkIos);
        generatedCard.setAppLinkAndroid(dummyAbstractCard.appLinkAndroid);

        CreatorBuilderTest creatorTest = new CreatorBuilderTest();
        creatorTest.prepare();
        Creator generatedCreator = creatorTest.buildExtensiveCreator();
        generatedCard.setCreator(generatedCreator);

        Assert.assertEquals(mapper.writeValueAsString(fixtureCard), generatedCard.writeAsJsonString());
    }

    @Test
    public void testMinimalWriteAsJsonMethod() throws JsonParseException, JsonMappingException, IOException, CardBuilderException {

        String inputString = TestUtil.readResourceAsString("example_minimal_product_card.json");
        ProductCard fixtureCard = mapper.readValue(inputString,  ProductCard.class);
        ProductCard generatedCard = buildMinimalProductCard();

        Assert.assertEquals(mapper.writeValueAsString(fixtureCard), generatedCard.writeAsJsonString());
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullName() throws CardBuilderException {
        ProductCard card = new ProductCard(null, dummyOffer.price.getPrice(), dummyProduct.webUrl);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithEmptyOffersList() throws CardBuilderException {
        List<Offer> emptyList = new ArrayList<Offer>();
        Product product = new ProductBuilder(dummyProduct.name, dummyProduct.description, dummyProduct.images).build();
        ProductCard card = new ProductCard(product, emptyList, dummyProduct.webUrl);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullOfferListItems() throws CardBuilderException {

        Product product = new ProductBuilder(dummyProduct.name, dummyProduct.description, dummyProduct.images).build();
        Offer offer = null;

        ProductCard card = new ProductCard(product, offer, dummyProduct.webUrl);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithOnlyNullOffersListItems() throws CardBuilderException {
        List<Offer> offers = new ArrayList<Offer>();
        offers.add(null);
        offers.add(null);

        Product product = new ProductBuilder(dummyProduct.name, dummyProduct.description, dummyProduct.images).build();

        ProductCard card = new ProductCard(product, offers, dummyProduct.webUrl);
    }

    @Test
    public void isValidWithSomeNullOffersListItems() throws CardBuilderException {
        List<Offer> offers = new ArrayList<Offer>();
        offers.add(null);
        offers.add(new OfferBuilder(12.99f).build());
        offers.add(null);

        Product product = new ProductBuilder(dummyProduct.name, dummyProduct.description, dummyProduct.images).build();

        try {
            ProductCard card = new ProductCard(product, offers, dummyProduct.webUrl);
            assertEquals("Offer size should only be 1", 1, card.getOffers().size());
        } catch (CardBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isValidOneOffers() throws CardBuilderException {
        Offer offer = new OfferBuilder(12.99f).build();
        Product product = new ProductBuilder(dummyProduct.name, dummyProduct.description, dummyProduct.images).build();

        try {
            ProductCard card = new ProductCard(product, offer, dummyProduct.webUrl);
            assertEquals("Offer size should only be 1", 1, card.getOffers().size());
        } catch (CardBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidNullKeywords() throws CardBuilderException {
        Offer offer = new OfferBuilder(12.99f).build();
        Product product = new ProductBuilder(dummyProduct.name, dummyProduct.description, dummyProduct.images).build();

        ProductCard card = new ProductCard(product, offer, dummyProduct.webUrl);
        card.setKeywords(null);
    }

    private Product buildExtensiveProduct() throws CardBuilderException {
        ProductBuilder builder = new ProductBuilder(dummyProduct.name, dummyProduct.description, dummyProduct.images);

        builder.brand(dummyProduct.brand);
        builder.merchant(dummyProduct.merchant);
        builder.colors(dummyProduct.colors);
        builder.rating(dummyProduct.rating);
        builder.ratingScale(dummyProduct.ratingScale);
        builder.ratingCount(dummyProduct.ratingCount);
        builder.relatedItems(dummyProduct.relatedItems);
        builder.referencedItems(dummyProduct.referencedItems);
        builder.sizes(dummyProduct.sizes);
        builder.options(dummyProduct.options);
        builder.gender(dummyProduct.gender);
        builder.model(dummyProduct.model);

        return builder.build();
    }

    @Test(expected = CardBuilderException.class)
    public void testBuildProductCardWithWebUrl() throws CardBuilderException {
        //this URL has no price meta tag so expect to fail
        ProductCard productCard = new ProductCard("https://www.etsy.com/listing/128235512/etsy-i-buy-from-real-people-tote-bag");
    }

    @Test
    public void testBuildProductCardWithWebUrlAndPrice() throws CardBuilderException {
        ProductCard productCard = new ProductCard("https://www.etsy.com/listing/128235512/etsy-i-buy-from-real-people-tote-bag", 15f);
        assertEquals(productCard.getOffers().get(0).getPrice().getPrice(), new Float(15f));
        assertEquals(productCard.getProduct().getImages().get(0).toString(), "https://img0.etsystatic.com/011/0/5147325/il_570xN.444675668_1tp8.jpg");
        assertEquals(productCard.getProduct().getName(), "Etsy \"I Buy from Real People\" Tote Bag");
        assertEquals(productCard.getProduct().getDescription(), "Keep it real with an Etsy limited edition I Buy from Real People Tote Bag. Made of sturdy cotton canvas, this bag is a great carry-all for work and");
        assertEquals(productCard.getAppLinkAndroid(), "etsy://listing/128235512?ref=applinks_android");
        assertEquals(productCard.getAppLinkIos(), "etsy://listing/128235512?ref=applinks_ios");
    }
}