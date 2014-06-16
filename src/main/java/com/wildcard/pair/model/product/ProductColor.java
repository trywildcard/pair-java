package com.wildcard.pair.model.product;

import java.net.URL;

import com.wildcard.pair.util.ValidationTool;

public class ProductColor {
    // required fields
    private String displayName;
    
    // optional fields
    private URL swatchLink;
    private String value;
    private MappingColor mappingColor;
    
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

    /*
     * The following private constructor and private methods are required by Jackson. 
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
