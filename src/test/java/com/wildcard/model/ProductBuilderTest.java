package com.wildcard.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProductBuilderTest {
    
    final String name = "Awesome 4th Of July Patriotic Red White Blue And Star Glass Beaded";
    final String description = "Celebrate The 4th With This Unique & Original Beautiful Handcrafted Ankle Bracelet!!!  Just In Time";
    final String url = "http://www.etsy.com/listing/155021118/awesome-4th-of-july-patriotic-red-white?ref=&sref=";
    final String imgUrl = "http://img0.etsystatic.com/017/0/7024554/il_570xN.473259184_iqm9.jpg";
    final float price = 7.99f;
    final String brandName = "Etsy";
    
    private void testProductCard(ProductCard card){
        assertEquals("Name should match", name, card.getName());
        assertEquals("Description should match", description, card.getDescription());
        assertEquals("Url should match", url, card.getUrl());
        assertEquals("Image url should match", imgUrl, card.getImages().get(0));
        
        int floatComparisonEpsilon = 0; // prices should be exact, never computed. 
        assertEquals("Price should match", price, card.getOffers().get(0).getPrice(), floatComparisonEpsilon);
        
        assertEquals("Brand name should match", brandName, card.getBrandName());
    }
    
    @Test
    public void testMinimalProductCard(){
        
        ProductCardBuilder builder = new ProductCardBuilder();
        
        builder.name(name);
        builder.description(description);
        builder.url(url);
        builder.image(imgUrl);
        builder.price(price);
        builder.brandName(brandName);
        
        ProductCard card = builder.build();
        
        testProductCard(card);
    }
    
    // TODO: test extensive product card
    
    // TODO: test missing fields are invalid
}
