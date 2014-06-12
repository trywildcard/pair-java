package com.wildcard.model;

import java.net.URL;

import com.wildcard.model.util.ValidationTool;

public class ProductColor {
    private final String displayName;
    private final URL swatchLink;
    private final String value;
    private final MappingColor mappingColor;
    
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
    
}
