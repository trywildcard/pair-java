package com.wildcard.pair.model;

import com.wildcard.pair.model.product.MappingColor;
import com.wildcard.pair.model.product.ProductCard;
import com.wildcard.pair.model.product.ProductColor;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

/**
 * Created by michaelgarate on 6/30/14.
 */
public class ProductColorValidationTest {

    String displayName = "magenta";
    String value = "FF00FF";
    URL swatchUrl;
    MappingColor mappingColor = MappingColor.Pink;

    @Before
    public void setUp() throws MalformedURLException {
        this.swatchUrl = new URL("http://myproducts.com/swatches/magenta");
    }

    @Test
    public void isValidWithAttributes(){
        ProductColor pc = new ProductColor(displayName, value, swatchUrl, mappingColor);
        assertEquals(pc.getDisplayName(), displayName);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullDisplayName(){
        ProductColor pc = new ProductColor(null, value, swatchUrl, mappingColor);
    }

    @Test
    public void isValidWithoutOptionalFields(){
        ProductColor pc = new ProductColor(displayName, null, null, null);
        assertEquals(pc.getDisplayName(), displayName);
        assertEquals("Expected error count to match", 0, pc.getErrors().size());
    }

    @Test
    public void hasErrorForBlankValueString(){
        ProductColor pc = new ProductColor(displayName, "", null, null);
        assertEquals("Expected error count to match", 1, pc.getErrors().size());
    }

}