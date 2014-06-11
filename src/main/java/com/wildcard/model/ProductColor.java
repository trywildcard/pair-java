package com.wildcard.model;

public class ProductColor {
    private final String displayName;
    private final String swatchLink;
    private final String value;
    private final MappingColor mappingColor;
    
    public ProductColor(String displayName, String swatchLink, String value, MappingColor mappingColor){
        this.displayName = displayName;
        this.swatchLink = swatchLink;
        this.value = value;
        this.mappingColor = mappingColor;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getSwatchLink() {
        return swatchLink;
    }

    public String getValue() {
        return value;
    }

    public MappingColor getMappingColor() {
        return mappingColor;
    }
    
}
