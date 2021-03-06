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
    String value = "FF00FF";
    String swatchUrl;
    MappingColor mappingColor = MappingColor.Pink;

    @Before
    public void setUp() throws MalformedURLException {
        this.swatchUrl = "http://myproducts.com/swatches/magenta";
    }

    @Test
    public void isValidWithAttributes() throws CardBuilderException {
        ProductColor pc = new ProductColor(displayName, value, swatchUrl, mappingColor);
        Assert.assertEquals(pc.getDisplayName(), displayName);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullDisplayName() throws CardBuilderException {
        ProductColor pc = new ProductColor(null, value, swatchUrl, mappingColor);
    }

    @Test
    public void isValidWithoutOptionalFields() throws CardBuilderException {
        ProductColor pc = new ProductColor(displayName, null, null, null);
        Assert.assertEquals(pc.getDisplayName(), displayName);
        Assert.assertEquals("Expected error count to match", 0, pc.getErrors().size());
    }

    @Test
    public void hasErrorForBlankValueString() throws CardBuilderException {
        ProductColor pc = new ProductColor(displayName, "", null, null);
        Assert.assertEquals("Expected error count to match", 1, pc.getErrors().size());
    }

}
