package com.trywildcard.pair.model.product;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trywildcard.pair.Pair;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.util.DummyOffer;
import com.trywildcard.pair.util.DummyProduct;
import com.trywildcard.pair.util.TestUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Test using the productCardBuilder to build a product card from values in code stubs.
 */
public class ProductCardBuilderTest {
    
    ObjectMapper mapper = TestUtil.getObjectMapper();
    private static DummyOffer dummyOffer;
    private static DummyProduct dummyProduct;
    
    @BeforeClass
    public static void prepare() throws ParseException, CardBuilderException {
        dummyOffer = new DummyOffer();
        dummyProduct = new DummyProduct();
    }
    
    private void testMinimalCardAttributes(ProductCard card){
        Assert.assertEquals("Name should match", dummyProduct.name, card.getName());
        Assert.assertEquals("Web url should match", dummyProduct.webUrl, card.getWebUrl().toString());
        
        Assert.assertEquals("Price should match", dummyOffer.price.getPrice(), card.getOffers().get(0).getPrice().getPrice());
    }
    
    private ProductCard buildMinimalProductCard() throws CardBuilderException {
        List<Offer> offers = new ArrayList<Offer>();
        Offer offer = new OfferBuilder(dummyOffer.price).build();
        offers.add(offer);
        
        ProductCardBuilder cardBuilder = new ProductCardBuilder(dummyProduct.name, offers, dummyProduct.webUrl);

        return cardBuilder.build();
    }
    
    @Test
    public void testMinimalProductCard() throws JsonProcessingException, CardBuilderException {
        ProductCard card = buildMinimalProductCard();
        testMinimalCardAttributes(card);
    }
    

    @Test
    public void testMinimalProductWithMinimalConstructor() throws CardBuilderException {
        ProductCard card = new ProductCardBuilder(dummyProduct.name, dummyOffer.price.getPrice(), dummyProduct.webUrl).build();
        testMinimalCardAttributes(card);
    }
    
    private void testExtensiveCardAttributes(ProductCard card) throws MalformedURLException {
        testMinimalCardAttributes(card);

        Assert.assertEquals("Image url should match", dummyProduct.imgUrl, card.getImages().get(0).toString());
        Assert.assertEquals("Description should match", dummyProduct.description, card.getDescription());
        Assert.assertEquals("Brand name should match", dummyProduct.brand, card.getBrand());
        
        Assert.assertEquals("Merchant should match", dummyProduct.merchant, card.getMerchant());
        Assert.assertEquals("Colors should match", dummyProduct.colors, card.getColors());
        
        List<URL> combinedImages = new ArrayList<URL>();
        combinedImages.add(new URL(dummyProduct.imgUrl));
        for (String img : dummyProduct.images){
            combinedImages.add(new URL(img));
        }
        Assert.assertEquals("Images should match", combinedImages, card.getImages());
        
        Assert.assertEquals("Rating should match", dummyProduct.rating, card.getRating(), TestUtil.FLOAT_EXACT_COMPARISON_EPSILON);
        Assert.assertEquals("Rating scale should match", dummyProduct.ratingScale, card.getRatingScale(), TestUtil.FLOAT_EXACT_COMPARISON_EPSILON);
        Assert.assertEquals("Rating count should match", dummyProduct.ratingCount, card.getRatingCount());

        List<URL> relatedItems = new ArrayList<URL>();
        for (String url : dummyProduct.relatedItems){
            relatedItems.add(new URL(url));
        }

        Assert.assertEquals("Related Items should match", relatedItems, card.getRelatedItems());
        Assert.assertEquals("Sizes should match", dummyProduct.sizes, card.getSizes());
        Assert.assertEquals("Options should match", dummyProduct.options, card.getOptions());
        Assert.assertEquals("Model should match", dummyProduct.model, card.getModel());
        Assert.assertEquals("App link ios should match", dummyProduct.appLinkIos, card.getAppLinkIos());
        Assert.assertEquals("App link Android should match", dummyProduct.appLinkAndroid, card.getAppLinkAndroid());
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
        builder.gender(dummyOffer.gender);
        builder.weight(dummyOffer.weight);
        builder.weightUnits(dummyOffer.weightUnits);
        builder.offerId(dummyOffer.offerId);
        
        return builder.build();
    }
    
    private ProductCard buildExtensiveProductCard() throws CardBuilderException {
        List<Offer> offers = new ArrayList<Offer>();
        Offer offer = buildExtensiveOffer();
        offers.add(offer);
        
        ProductCardBuilder builder = new ProductCardBuilder(dummyProduct.name, offers, dummyProduct.webUrl);

        builder.productId(dummyProduct.productId);
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
        builder.referencedItems(dummyProduct.referencedItems);
        builder.sizes(dummyProduct.sizes);
        builder.options(dummyProduct.options);
        builder.model(dummyProduct.model);
        builder.appLinkIos(dummyProduct.appLinkIos);
        builder.appLinkAndroid(dummyProduct.appLinkAndroid);
        
        return builder.build();
    }
    
    @Test
    public void testExtensiveProductCard() throws IOException, URISyntaxException, CardBuilderException {
        ProductCard card = buildExtensiveProductCard();
        testExtensiveCardAttributes(card);
    }

    @Test
    public void testExtensiveWriteAsJsonMethod() throws JsonParseException, JsonMappingException, IOException, CardBuilderException {
        String inputString = TestUtil.readResourceAsString("example_product_card.json");
        ProductCard fixtureCard = mapper.readValue(inputString,  ProductCard.class);
        ProductCard generatedCard = buildExtensiveProductCard();
        
        Assert.assertEquals(mapper.writeValueAsString(fixtureCard), generatedCard.writeAsJsonString());
    }

    @Test
    public void testMinimalWriteAsJsonMethod() throws JsonParseException, JsonMappingException, IOException, CardBuilderException {
        String inputString = TestUtil.readResourceAsString("example_minimal_product_card.json");
        ProductCard fixtureCard = mapper.readValue(inputString,  ProductCard.class);
        ProductCard generatedCard = buildMinimalProductCard();
        
        Assert.assertEquals(mapper.writeValueAsString(fixtureCard), generatedCard.writeAsJsonString());
    }
}