package com.wildcard.model;

public class Color {
    private final String displayName;
    private final String swatchLink;
    private final String value;
    private final MappingColor mappingColor;
    
    public Color(ColorBuilder builder){
        this.displayName = builder.displayName;
        this.swatchLink = builder.swatchLink;
        this.value = builder.value;
        this.mappingColor = builder.mappingColor;
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
