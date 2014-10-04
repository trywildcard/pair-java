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
public class ProductBuilderTest {
    
    ObjectMapper mapper = TestUtil.getObjectMapper();
    private static DummyProduct dummyProduct;
    
    @BeforeClass
    public static void prepare() throws ParseException, CardBuilderException {
        dummyProduct = new DummyProduct();
    }

    private void testMinimalProductAttributes(Product product){
        Assert.assertEquals("Name should match", dummyProduct.name, product.getName());
    }

    private Product buildMinimalProduct() throws CardBuilderException {

        ProductBuilder cardBuilder = new ProductBuilder(dummyProduct.name);

        return cardBuilder.build();
    }

    @Test
    public void testMinimalProduct() throws JsonProcessingException, CardBuilderException {
        Product product = buildMinimalProduct();
        testMinimalProductAttributes(product);
    }


    @Test
    public void testMinimalProductWithMinimalConstructor() throws CardBuilderException {
        Product product = new ProductBuilder(dummyProduct.name).build();
        testMinimalProductAttributes(product);
    }
    
    private void testExtensiveCardAttributes(Product product) throws MalformedURLException {
        testMinimalProductAttributes(product);

        Assert.assertEquals("Image url should match", dummyProduct.imgUrl, product.getImages().get(0).toString());
        Assert.assertEquals("Description should match", dummyProduct.description, product.getDescription());
        Assert.assertEquals("Brand name should match", dummyProduct.brand, product.getBrand());
        
        Assert.assertEquals("Merchant should match", dummyProduct.merchant, product.getMerchant());
        Assert.assertEquals("Colors should match", dummyProduct.colors, product.getColors());
        
        List<URL> combinedImages = new ArrayList<URL>();
        combinedImages.add(new URL(dummyProduct.imgUrl));
        for (String img : dummyProduct.images){
            combinedImages.add(new URL(img));
        }
        Assert.assertEquals("Images should match", combinedImages, product.getImages());
        
        Assert.assertEquals("Rating should match", dummyProduct.rating, product.getRating(), TestUtil.FLOAT_EXACT_COMPARISON_EPSILON);
        Assert.assertEquals("Rating scale should match", dummyProduct.ratingScale, product.getRatingScale(), TestUtil.FLOAT_EXACT_COMPARISON_EPSILON);
        Assert.assertEquals("Rating count should match", dummyProduct.ratingCount, product.getRatingCount());

        List<URL> relatedItems = new ArrayList<URL>();
        for (String url : dummyProduct.relatedItems){
            relatedItems.add(new URL(url));
        }

        Assert.assertEquals("Related Items should match", relatedItems, product.getRelatedItems());
        Assert.assertEquals("Sizes should match", dummyProduct.sizes, product.getSizes());
        Assert.assertEquals("Options should match", dummyProduct.options, product.getOptions());
        Assert.assertEquals("Model should match", dummyProduct.model, product.getModel());
        Assert.assertEquals("App link ios should match", dummyProduct.appLinkIos, product.getAppLinkIos());
        Assert.assertEquals("App link Android should match", dummyProduct.appLinkAndroid, product.getAppLinkAndroid());
    }


     private Product buildExtensiveProduct() throws CardBuilderException {
        ProductBuilder builder = new ProductBuilder(dummyProduct.name);

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
        Product product = buildExtensiveProduct();
        testExtensiveCardAttributes(product);
    }
}
