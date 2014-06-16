package com.wildcard.pair.model;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wildcard.pair.model.product.Offer;
import com.wildcard.pair.model.product.OfferBuilder;
import com.wildcard.pair.model.product.ProductCard;
import com.wildcard.pair.model.product.ProductCardBuilder;
import com.wildcard.pair.util.CardMapper;
import com.wildcard.pair.util.TestUtil;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductCardBuilderTest {
    
    ObjectMapper mapper = new CardMapper().getObjectMapper();
    private static DummyOffer dummyOffer;
    private static DummyProduct dummyProduct;
    
    @BeforeClass
    public static void prepare() throws MalformedURLException, ParseException  {
        dummyOffer = new DummyOffer();
        dummyProduct = new DummyProduct();
    }
    
    private void testMinimalCardAttributes(ProductCard card){
        assertEquals("Name should match", dummyProduct.name, card.getName());
        assertEquals("Url should match", dummyProduct.url, card.getUrl());
        
        assertEquals("Price should match", dummyOffer.price.getPrice(), card.getOffers().get(0).getPrice().getPrice());
    }
    
    private ProductCard buildMinimalProductCard(){
        List<Offer> offers = new ArrayList<Offer>();
        Offer offer = new OfferBuilder(dummyOffer.price).build();
        offers.add(offer);
        
        ProductCardBuilder cardBuilder = new ProductCardBuilder(dummyProduct.name, offers, dummyProduct.url); 

        return cardBuilder.build();
    }
    
    @Test
    public void testMinimalProductCard() throws MalformedURLException, JsonProcessingException{
        ProductCard card = buildMinimalProductCard();
        testMinimalCardAttributes(card);
    }
    

    @Test
    public void testMinimalProductWithMinimalConstructor(){
        ProductCard card = new ProductCardBuilder(dummyProduct.name, dummyOffer.price.getPrice(), dummyProduct.url).build();
        testMinimalCardAttributes(card);
    }
    
    private void testExtensiveCardAttributes(ProductCard card){
        testMinimalCardAttributes(card);

        assertEquals("Image url should match", dummyProduct.imgUrl, card.getImages().get(0));
        assertEquals("Description should match", dummyProduct.description, card.getDescription());
        assertEquals("Brand name should match", dummyProduct.brand, card.getBrand());
        
        assertEquals("Merchant should match", dummyProduct.merchant, card.getMerchant());
        assertEquals("Colors should match", dummyProduct.colors, card.getColors());
        
        List<URL> combinedImages = new ArrayList<URL>();
        combinedImages.add(dummyProduct.imgUrl);
        for (URL img : dummyProduct.images){
            combinedImages.add(img);
        }
        assertEquals("Images should match", combinedImages, card.getImages());
        
        assertEquals("Rating should match", dummyProduct.rating, card.getRating(), TestUtil.FLOAT_EXACT_COMPARISON_EPSILON);
        assertEquals("Rating scale should match", dummyProduct.ratingScale, card.getRatingScale(), TestUtil.FLOAT_EXACT_COMPARISON_EPSILON);
        assertEquals("Rating count should match", dummyProduct.ratingCount, card.getRatingCount());
        assertEquals("Related Items should match", dummyProduct.relatedItems, card.getRelatedItems());
        assertEquals("Sizes should match", dummyProduct.sizes, card.getSizes());
        assertEquals("Options should match", dummyProduct.options, card.getOptions());
        assertEquals("Model should match", dummyProduct.model, card.getModel());
        assertEquals("App link ios should match", dummyProduct.appLinkIos, card.getAppLinkIos());
        assertEquals("App link Android should match", dummyProduct.appLinkAndroid, card.getAppLinkAndroid());
    }
    
    
    private Offer buildExtensiveOffer(){
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
        builder.gender(dummyOffer.gender);
        builder.weight(dummyOffer.weight);
        builder.weightUnits(dummyOffer.weightUnits);
        
        return builder.build();
    }
    
    private ProductCard buildExtensiveProductCard(){
        List<Offer> offers = new ArrayList<Offer>();
        Offer offer = buildExtensiveOffer();
        offers.add(offer);
        
        ProductCardBuilder builder = new ProductCardBuilder(dummyProduct.name, offers, dummyProduct.url);

        builder.description(dummyProduct.description);
        builder.image(dummyProduct.imgUrl);
        builder.brand(dummyProduct.brand);
        builder.merchant(dummyProduct.merchant);
        builder.colors(dummyProduct.colors);
        builder.images(dummyProduct.images);
        builder.rating(dummyProduct.rating);
        builder.ratingScale(dummyProduct.ratingScale);
        builder.ratingCount(dummyProduct.ratingCount);
        builder.relatedItems(dummyProduct.relatedItems);
        builder.sizes(dummyProduct.sizes);
        builder.options(dummyProduct.options);
        builder.model(dummyProduct.model);
        builder.appLinkIos(dummyProduct.appLinkIos);
        builder.appLinkAndroid(dummyProduct.appLinkAndroid);
        
        return builder.build();
    }
    
    @Test
    public void testExtensiveProductCard() throws IOException, URISyntaxException{
        ProductCard card = buildExtensiveProductCard();
        testExtensiveCardAttributes(card);
    }

    @Test
    public void testExtensiveWriteAsJsonMethod() throws JsonParseException, JsonMappingException, IOException{
        String inputString = TestUtil.readResourceAsString("example_product_card.json");
        ProductCard fixtureCard = mapper.readValue(inputString,  ProductCard.class);
        ProductCard generatedCard = buildExtensiveProductCard();
        
        assertEquals(mapper.writeValueAsString(fixtureCard), generatedCard.writeAsJsonString());
    }

    @Test
    public void testMinimalWriteAsJsonMethod() throws JsonParseException, JsonMappingException, IOException{
        String inputString = TestUtil.readResourceAsString("example_minimal_product_card.json");
        ProductCard fixtureCard = mapper.readValue(inputString,  ProductCard.class);
        ProductCard generatedCard = buildMinimalProductCard();
        
        assertEquals(mapper.writeValueAsString(fixtureCard), generatedCard.writeAsJsonString());
    }
    
    
    // TODO: test validation
}
