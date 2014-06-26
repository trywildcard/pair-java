package com.wildcard.pair.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wildcard.pair.model.product.MappingColor;
import com.wildcard.pair.model.product.ProductColor;

/**
 * Product stub with data supporting Junit tests.
 */
public class DummyProduct {

    // minimal attributes
    protected final String name = "Green leather shoes";
    final String description = "Brilliant, colorful shoes made from recycled materials.";
    final URL webUrl;
    final URL imgUrl;
    final String brand = "Shoemaster";
    
    // extensive attributes
    final String merchant = "Example Store";
    final List<ProductColor> colors = new ArrayList<ProductColor>();
    final List<URL> images = new ArrayList<URL>();
    final Float rating = 8f;
    final Float ratingScale = 10f;
    final Integer ratingCount = 12;
    final List<URL> relatedItems = new ArrayList<URL>();
    final List<URL> referencedItems = new ArrayList<URL>();
    final Map<String, String> sizes = new HashMap<String, String>();
    final List<String> options = Arrays
            .asList("example option a", 
                    "example option b",
                    "example option c");
    final String model = "KICKN-GRL-17";
    final String appLinkIos = "jump://VerticalA/x123";
    final String appLinkAndroid = "market://search?q=pub:examplestore";
    final String productId = "36226783";
    final URL cardUrl;

    public DummyProduct() throws MalformedURLException{

        webUrl = new URL("http://www.examplestore.com/listing/36226783/green-leather-shoes");
        imgUrl = new URL("http://img.examplestore.com/02/wer4554/il_5d9184_iqm9.jpg");
        cardUrl = new URL("http://www.examplestore.com/product_cards/36226783/");


        ProductColor color = new ProductColor("Mint",
                "RGB(62, 180, 137)",
                new URL("http://https://www.examplestore.com/swatches/mint.jpg"),
                MappingColor.Green);

        colors.add(color);
        
        sizes.put("md", "Medium");

        images.add(new URL("http://img.examplestore.com/017/fgh/il_57w9.jpg"));
        images.add(new URL("https://img.examplestore.com/020rjt/24554/il_570d14_3us0.jpg"));
        images.add(new URL("https://img.examplestore.com/0166f54/il_570xg90_87gc.jpg"));
        
        relatedItems.add(new URL("http://www.examplestore.com/listing/36226783/green-leather-shoes"));
        relatedItems.add(new URL("http://www.examplestore.com/listing/16362355/maroon-checkered-shoes"));
        relatedItems.add(new URL("http://www.examplestore.com/listing/13245522/pink-nylon-shoes"));

        referencedItems.add(new URL("http://www.examplestore.com/listing/45637734/blue-leather-shoes"));
        referencedItems.add(new URL("http://www.examplestore.com/listing/88833589/yellow-leather-shoes"));
    }
}
