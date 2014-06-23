package com.wildcard.pair.model.product;

import java.net.URL;

import com.wildcard.pair.util.ValidationTool;

/**
 *
 */
public final class ProductColor {
    // required fields
    private String displayName;
    
    // optional fields
    private URL swatchLink;
    private String value;
    private MappingColor mappingColor;


    /**
     * Construct a product color.
     * @param displayName the name of the color as presented to the user.
     * @param value
     * @param swatchLink link to an image with a sample of the color.
     * @param mappingColor map to a set of predefined colors
     */
    public ProductColor(String displayName, String value, URL swatchLink, MappingColor mappingColor){
        setDisplayName(displayName);
        setSwatchLink(swatchLink);
        setValue(value);
        setMappingColor(mappingColor);
    }

    public String getDisplayName() {
        return displayName;
    }

    public URL getSwatchLink() {
        return swatchLink;
    }

    public String getValue() {
        return value;
    }

    public MappingColor getMappingColor() {
        return mappingColor;
    }


    /**
     * Private constructor to allow for Jackson deserialization.
     */
    private ProductColor(){}
    
    private void setDisplayName(String displayName){
        ValidationTool.notNullOrEmpty(displayName, "Must specify a displayName.");
        this.displayName = displayName;
    }
    
    private void setSwatchLink(URL swatchLink){
        this.swatchLink = swatchLink;
    }
    
    private void setValue(String value){
        ValidationTool.notEmpty(value, "Tried to set value to an empty string.");
        this.value = value;
    }
    
    private void setMappingColor(MappingColor mappingColor){
        this.mappingColor = mappingColor;
    }
}
