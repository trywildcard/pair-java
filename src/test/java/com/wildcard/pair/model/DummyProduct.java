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
    protected final String name = "Awesome 4th Of July Patriotic Red White Blue And Star Glass Beaded";
    final String description = "Celebrate The 4th With This Unique & Original Beautiful Handcrafted Ankle Bracelet!!!  Just In Time";
    final URL url;
    final URL imgUrl;
    final String brand = "ItemsByLisa";
    
    // extensive attributes
    final String merchant = "Etsy";
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
    final String model = "Bead Ankle Bracelet - 10 inch anklet";
    final String appLinkIos = "jump://VerticalA/x123";
    final String appLinkAndroid = "market://search?q=pub:etsy";
    final String productId = "155021118";

    public DummyProduct() throws MalformedURLException{
        
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

        referencedItems.add(new URL("https://www.etsy.com/listing/154992204/beautiful-shimmer-gloss-multicolored?ref=related-6"));
        referencedItems.add(new URL("https://www.etsy.com/listing/154992842/stunning-blue-glass-beaded-10-anklet?ref=related-5"));
    }
}
