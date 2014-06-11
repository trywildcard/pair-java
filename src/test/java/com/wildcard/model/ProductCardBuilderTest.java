package com.wildcard.model;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ProductCardBuilderTest {

    private final int FLOAT_COMPARISON_EPSILON = 0; // prices should be exact, no error since never computed. 
    
    // minimal attributes
    final String name = "Awesome 4th Of July Patriotic Red White Blue And Star Glass Beaded";
    final String description = "Celebrate The 4th With This Unique & Original Beautiful Handcrafted Ankle Bracelet!!!  Just In Time";
    final String url = "http://www.etsy.com/listing/155021118/awesome-4th-of-july-patriotic-red-white?ref=&sref=";
    final String imgUrl = "http://img0.etsystatic.com/017/0/7024554/il_570xN.473259184_iqm9.jpg";
    final float price = 7.99f;
    final String brand = "ItemsByLisa";
    
    // extensive attributes
    // TODO: list of offers
    final String merchant = "Etsy";
    final String gender = "female";
    // TODO: product.colors
    final List<String> images = Arrays
            .asList("http://img0.etsystatic.com/017/0/7024554/il_570xN.473259184_iqm9.jpg",
                    "https://img0.etsystatic.com/020/0/7024554/il_570xN.473259414_3us0.jpg",
                    "https://img0.etsystatic.com/018/0/7024554/il_570xN.473259490_87gc.jpg");
    final List<String> videos = Arrays
            .asList("https://www.youtube.com/watch?v=UBSN7WOPkQ0",
                    "https://www.youtube.com/watch?v=PX1vwDe9Z94",
                    "https://www.youtube.com/watch?v=oPc-WR2sLi4");
    final float rating = 8;
    final float ratingScale = 10;
    final int ratingCount = 12;
    final List<String> relatedItems = Arrays
            .asList("https://www.etsy.com/listing/108648389/glass-beaded-colorful-flower-beads-white?ref=related-0",
                    "https://www.etsy.com/listing/108816901/ooak-glass-and-metal-beaded-anklet-navy?ref=related-2",
                    "https://www.etsy.com/listing/157474664/beach-anklet-beautiful-green-blue?ref=related-4");
    // TODO: product.sizes
    // TODO: product.options
    final float weight = 1.77f;
    final String pattern = "alternating beads with stars";
    final String condition = "new, handmade";
    final String model = "Bead Ankle Bracelet - 10 inch anklet";
    final String material = "Glass Beads, Metal Clasp";
    final float shippingCost = 2.99f;
    final String appLinkIos = "jump://VerticalA/x123";
    final String appLinkAndroid = "market://search?q=pub:etsy";
    
    private void testMinimalCardAttributes(ProductCard card){
        assertEquals("Name should match", name, card.getName());
        assertEquals("Description should match", description, card.getDescription());
        assertEquals("Url should match", url, card.getUrl());
        assertEquals("Image url should match", imgUrl, card.getImages().get(0));
        
        assertEquals("Price should match", price, card.getOffers().get(0).getPrice(), FLOAT_COMPARISON_EPSILON);
        assertEquals("Brand name should match", brand, card.getBrand());
    }
    
    @Test
    public void testMinimalProductCard(){
        
        ProductCardBuilder builder = new ProductCardBuilder();
        
        builder.name(name);
        builder.description(description);
        builder.url(url);
        builder.image(imgUrl);
        builder.price(price);
        builder.brand(brand);
        
        ProductCard card = builder.build();
        
        testMinimalCardAttributes(card);
    }
    
    private void testExtensiveCardAttributes(ProductCard card){
        testMinimalCardAttributes(card);
        
        assertEquals("Merchant should match", merchant, card.getMerchant());
        assertEquals("Gender should match", gender, card.getGender().toString().toLowerCase());
        assertEquals("Images should match", images, card.getImages());
        assertEquals("Videos should match", videos, card.getVideos());
        assertEquals("Rating should match", rating, card.getRating(), FLOAT_COMPARISON_EPSILON);
        assertEquals("Rating scale should match", ratingScale, card.getRatingScale(), FLOAT_COMPARISON_EPSILON);
        assertEquals("Rating count should match", ratingCount, card.getRatingCount());
        assertEquals("Weight should match", weight, card.getWeight(), FLOAT_COMPARISON_EPSILON);
        assertEquals("Pattern should match", pattern, card.getPattern());
        assertEquals("Condition should match", condition, card.getCondition());
        assertEquals("Model should match", model, card.getModel());
        assertEquals("Material should match", material, card.getMaterial());
        assertEquals("Shipping cost should match", shippingCost, card.getShippingCost(), FLOAT_COMPARISON_EPSILON);
        assertEquals("App link ios should match", appLinkIos, card.getAppLinkIos());
        assertEquals("App link Android should match", appLinkAndroid, card.getAppLinkAndroid());
    }
    
    @Test
    public void testExtensiveProductCard(){
        ProductCardBuilder builder = new ProductCardBuilder();

        builder.name(name);
        builder.description(description);
        builder.url(url);
        builder.image(imgUrl);
        builder.price(price);
        builder.brand(brand);
        builder.merchant(merchant);
        builder.gender(Gender.valueOf(gender.toUpperCase()));
        builder.images(images);
        builder.videos(videos);
        builder.rating(rating);
        builder.ratingScale(ratingScale);
        builder.ratingCount(ratingCount);
        builder.relatedItems(relatedItems);
        builder.weight(weight);
        builder.pattern(pattern);
        builder.condition(condition);
        builder.model(model);
        builder.material(material);
        builder.shippingCost(shippingCost);
        builder.appLinkIos(appLinkIos);
        builder.appLinkAndroid(appLinkAndroid);
        
        ProductCard card = builder.build();
        
        testExtensiveCardAttributes(card);
    }
    
    // TODO: test extensive product card
    
    // TODO: test missing fields are invalid
}
