package com.wildcard.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ProductCardBuilderTest {

    private final int FLOAT_COMPARISON_EPSILON = 0; // prices should be exact, no error since never computed. 
    
    // minimal attributes
    final String name = "Awesome 4th Of July Patriotic Red White Blue And Star Glass Beaded";
    final String description = "Celebrate The 4th With This Unique & Original Beautiful Handcrafted Ankle Bracelet!!!  Just In Time";
    final String url = "http://www.etsy.com/listing/155021118/awesome-4th-of-july-patriotic-red-white?ref=&sref=";
    final String imgUrl = "http://img0.etsystatic.com/017/0/7024554/il_570xN.473259184_iqm9.jpg";
    final Float price = 7.99f;
    final String brand = "ItemsByLisa";
    
    // extensive attributes
    // TODO: list of offers
    final String merchant = "Etsy";
    final String gender = "female";
    final List<ProductColor> colors = new ArrayList<ProductColor>();
    final List<String> images = Arrays
            .asList("http://img0.etsystatic.com/017/0/7024554/il_570xN.473259184_iqm9.jpg",
                    "https://img0.etsystatic.com/020/0/7024554/il_570xN.473259414_3us0.jpg",
                    "https://img0.etsystatic.com/018/0/7024554/il_570xN.473259490_87gc.jpg");
    final Float rating = 8f;
    final Float ratingScale = 10f;
    final Integer ratingCount = 12;
    final List<String> relatedItems = Arrays
            .asList("https://www.etsy.com/listing/108648389/glass-beaded-colorful-flower-beads-white?ref=related-0",
                    "https://www.etsy.com/listing/108816901/ooak-glass-and-metal-beaded-anklet-navy?ref=related-2",
                    "https://www.etsy.com/listing/157474664/beach-anklet-beautiful-green-blue?ref=related-4");
    final List<Size> sizes = new ArrayList<Size>();
    final List<String> options = Arrays
            .asList("example option a", 
                    "example option b",
                    "example option c");
    final Float weight = 1.77f;
    final String pattern = "alternating beads with stars";
    final String condition = "new, handmade";
    final String model = "Bead Ankle Bracelet - 10 inch anklet";
    final String material = "Glass Beads, Metal Clasp";
    final Float shippingCost = 2.99f;
    final String appLinkIos = "jump://VerticalA/x123";
    final String appLinkAndroid = "market://search?q=pub:etsy";
    
    @Before
    public void prepare(){
        ProductColor color = new ProductColor("Magenta", "Magenta color value",
                "http://https://www.etsy.com/swatches/magenta.jpg",
                MappingColor.Red);
        
        colors.add(color);
        
        sizes.add(new Size("Medium", "md")); 
    }
    
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
        
        List<Offer> offers = new ArrayList<Offer>();
        Offer offer = new OfferBuilder(price).build();
        offers.add(offer);
        
        ProductCardBuilder cardBuilder = new ProductCardBuilder(name, offers); 
        
        cardBuilder.description(description);
        cardBuilder.url(url);
        cardBuilder.image(imgUrl);
        cardBuilder.brand(brand);
        
        ProductCard card = cardBuilder.build();
        
        testMinimalCardAttributes(card);
    }
    
    private void testExtensiveCardAttributes(ProductCard card){
        testMinimalCardAttributes(card);
        
        assertEquals("Merchant should match", merchant, card.getMerchant());
        assertEquals("Gender should match", gender, card.getGender().toString().toLowerCase());
        assertEquals("Colors should match", colors, card.getColors());
        
        List<String> combinedImages = new ArrayList<String>();
        combinedImages.add(imgUrl);
        for (String img : images){
            combinedImages.add(img);
        }
        assertEquals("Images should match", combinedImages, card.getImages());
        
        assertEquals("Rating should match", rating, card.getRating(), FLOAT_COMPARISON_EPSILON);
        assertEquals("Rating scale should match", ratingScale, card.getRatingScale(), FLOAT_COMPARISON_EPSILON);
        assertEquals("Rating count should match", ratingCount, card.getRatingCount());
        assertEquals("Related Items should match", relatedItems, card.getRelatedItems());
        assertEquals("Sizes should match", sizes, card.getSizes());
        assertEquals("Options should match", options, card.getOptions());
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
        List<Offer> offers = new ArrayList<Offer>();
        Offer offer = new OfferBuilder(price).build();
        offers.add(offer);
        
        ProductCardBuilder builder = new ProductCardBuilder(name, offers);

        builder.description(description);
        builder.url(url);
        builder.image(imgUrl);
        builder.brand(brand);
        builder.merchant(merchant);
        builder.gender(gender);
        builder.colors(colors);
        builder.images(images);
        builder.rating(rating);
        builder.ratingScale(ratingScale);
        builder.ratingCount(ratingCount);
        builder.relatedItems(relatedItems);
        builder.sizes(sizes);
        builder.options(options);
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
    
    // TODO: test missing fields are invalid
}
