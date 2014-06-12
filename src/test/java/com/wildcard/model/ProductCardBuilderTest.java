package com.wildcard.model;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductCardBuilderTest {

    private final int FLOAT_COMPARISON_EPSILON = 0; // prices should be exact, no error since never computed. 
    ObjectMapper mapper = new ObjectMapper();
    
    // minimal attributes
    final String name = "Awesome 4th Of July Patriotic Red White Blue And Star Glass Beaded";
    final String description = "Celebrate The 4th With This Unique & Original Beautiful Handcrafted Ankle Bracelet!!!  Just In Time";
    URL url;
    URL imgUrl;
    final Price price = new Price(7.99f, Currency.getInstance(Locale.US));
    final String brand = "ItemsByLisa";
    
    // extensive attributes
    final String merchant = "Etsy";
    final List<ProductColor> colors = new ArrayList<ProductColor>();
    final List<URL> images = new ArrayList<URL>();
    final Float rating = 8f;
    final Float ratingScale = 10f;
    final Integer ratingCount = 12;
    final List<URL> relatedItems = new ArrayList<URL>();
    final Map<String, String> sizes = new HashMap<String, String>();
    final List<String> options = Arrays
            .asList("example option a", 
                    "example option b",
                    "example option c");
    final String model = "Bead Ankle Bracelet - 10 inch anklet";
    final String appLinkIos = "jump://VerticalA/x123";
    final String appLinkAndroid = "market://search?q=pub:etsy";
    
    @Before
    public void prepare() throws MalformedURLException{
        
        url = new URL("http://www.etsy.com/listing/155021118/awesome-4th-of-july-patriotic-red-white?ref=&sref=");
        imgUrl = new URL("http://img0.etsystatic.com/017/0/7024554/il_570xN.473259184_iqm9.jpg");
        
        ProductColor color = new ProductColor("Magenta",
                "Magenta color value",
                new URL("http://https://www.etsy.com/swatches/magenta.jpg"),
                MappingColor.Red);
        
        colors.add(color);
        
        sizes.put("md", "Medium");

        images.add(new URL("http://img0.etsystatic.com/017/0/7024554/il_570xN.473259184_iqm9.jpg"));
        images.add(new URL("https://img0.etsystatic.com/020/0/7024554/il_570xN.473259414_3us0.jpg"));
        images.add(new URL("https://img0.etsystatic.com/018/0/7024554/il_570xN.473259490_87gc.jpg"));
        
        relatedItems.add(new URL("https://www.etsy.com/listing/108648389/glass-beaded-colorful-flower-beads-white?ref=related-0"));
        relatedItems.add(new URL("https://www.etsy.com/listing/108816901/ooak-glass-and-metal-beaded-anklet-navy?ref=related-2"));
        relatedItems.add(new URL("https://www.etsy.com/listing/157474664/beach-anklet-beautiful-green-blue?ref=related-4"));
    }
    
    private void testMinimalCardAttributes(ProductCard card){
        assertEquals("Name should match", name, card.getName());
        assertEquals("Description should match", description, card.getDescription());
        assertEquals("Url should match", url, card.getUrl());
        assertEquals("Image url should match", imgUrl, card.getImages().get(0));
        
        assertEquals("Price should match", price, card.getOffers().get(0).getPrice());
        assertEquals("Brand name should match", brand, card.getBrand());
    }
    
    @Test
    public void testMinimalProductCard() throws MalformedURLException, JsonProcessingException{
        
        List<Offer> offers = new ArrayList<Offer>();
        Offer offer = new OfferBuilder(price).build();
        offers.add(offer);
        
        ProductCardBuilder cardBuilder = new ProductCardBuilder(name, offers, url); 
        
        cardBuilder.description(description);
        cardBuilder.image(imgUrl);
        cardBuilder.brand(brand);
        
        ProductCard card = cardBuilder.build();
        
        testMinimalCardAttributes(card);
    }
    
    private void testExtensiveCardAttributes(ProductCard card){
        testMinimalCardAttributes(card);
        
        assertEquals("Merchant should match", merchant, card.getMerchant());
        assertEquals("Colors should match", colors, card.getColors());
        
        List<URL> combinedImages = new ArrayList<URL>();
        combinedImages.add(imgUrl);
        for (URL img : images){
            combinedImages.add(img);
        }
        assertEquals("Images should match", combinedImages, card.getImages());
        
        assertEquals("Rating should match", rating, card.getRating(), FLOAT_COMPARISON_EPSILON);
        assertEquals("Rating scale should match", ratingScale, card.getRatingScale(), FLOAT_COMPARISON_EPSILON);
        assertEquals("Rating count should match", ratingCount, card.getRatingCount());
        assertEquals("Related Items should match", relatedItems, card.getRelatedItems());
        assertEquals("Sizes should match", sizes, card.getSizes());
        assertEquals("Options should match", options, card.getOptions());
        assertEquals("Model should match", model, card.getModel());
        assertEquals("App link ios should match", appLinkIos, card.getAppLinkIos());
        assertEquals("App link Android should match", appLinkAndroid, card.getAppLinkAndroid());
    }
    
    @Test
    public void testExtensiveProductCard() throws IOException, URISyntaxException{
        List<Offer> offers = new ArrayList<Offer>();
        Offer offer = new OfferBuilder(price).build();
        offers.add(offer);
        
        ProductCardBuilder builder = new ProductCardBuilder(name, offers, url);

        builder.description(description);
        builder.image(imgUrl);
        builder.brand(brand);
        builder.merchant(merchant);
        builder.colors(colors);
        builder.images(images);
        builder.rating(rating);
        builder.ratingScale(ratingScale);
        builder.ratingCount(ratingCount);
        builder.relatedItems(relatedItems);
        builder.sizes(sizes);
        builder.options(options);
        builder.model(model);
        builder.appLinkIos(appLinkIos);
        builder.appLinkAndroid(appLinkAndroid);
        
        ProductCard card = builder.build();
        
        testExtensiveCardAttributes(card);

    }
    
    // TODO: test validation
}
