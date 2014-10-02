package com.trywildcard.pair.model.product;

import com.trywildcard.pair.exception.CardBuilderException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;

/**
 * Created by michaelgarate on 6/30/14.
 */
public class ProductColorValidationTest {

    String displayName = "magenta";
    String swatchUrl;

    @Before
    public void setUp() throws MalformedURLException {
        this.swatchUrl = "http://myproducts.com/swatches/magenta";
    }

    @Test
    public void isValidWithAttributes() throws CardBuilderException {
        ProductColor pc = new ProductColor(displayName, swatchUrl);
        Assert.assertEquals(pc.getDisplayName(), displayName);
        Assert.assertEquals(pc.getSwatchUrl(), swatchUrl);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullDisplayName() throws CardBuilderException {
        ProductColor pc = new ProductColor(null, swatchUrl);
    }

    @Test
    public void isValidWithoutOptionalFields() throws CardBuilderException {
        ProductColor pc = new ProductColor(displayName, null);
        Assert.assertEquals(pc.getDisplayName(), displayName);
        Assert.assertEquals("Expected error count to match", 0, pc.getErrors().size());
    }

}
