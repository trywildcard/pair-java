package com.wildcard.model;

import java.net.URL;

import com.wildcard.model.util.ValidationTool;

public class ProductColor {
    private String displayName;
    private URL swatchLink;
    private String value;
    private MappingColor mappingColor;
    
    public ProductColor(String displayName, String value, URL swatchLink, MappingColor mappingColor){
        ValidationTool.notNullOrEmpty(displayName, "Must specify a displayName.");
        
        this.displayName = displayName;
        this.swatchLink = swatchLink;
        this.value = value;
        this.mappingColor = mappingColor;
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
        this.displayName = displayName;
    }
    
    private void setSwatchLink(URL swatchLink){
        this.swatchLink = swatchLink;
    }
    
    private void setValue(String value){
        this.value = value;
    }
    
    private void setMappingColor(MappingColor mappingColor){
        this.mappingColor = mappingColor;
    }
}
