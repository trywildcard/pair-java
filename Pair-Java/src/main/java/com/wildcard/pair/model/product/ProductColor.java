package com.wildcard.pair.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wildcard.pair.util.ValidationTool;

import java.net.URL;
import java.util.List;

/**
 *
 */
public final class ProductColor {

    @JsonIgnore
    private ValidationTool v = new ValidationTool();

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

    public List<String> getErrors(){
        return v.getErrors();
    }

    /**
     * Private constructor to allow for Jackson deserialization.
     */
    private ProductColor(){}
    
    private void setDisplayName(String displayName){
        v.notNullOrEmpty(displayName, v.REQUIRED, "Must specify a displayName.");
        this.displayName = displayName;
    }
    
    private void setSwatchLink(URL swatchLink){
        this.swatchLink = swatchLink;
    }
    
    private void setValue(String value){
        boolean isValid = v.notEmpty(value, v.OPTIONAL, "Tried to set value to an empty string.");
        if (isValid) {
            this.value = value;
        }
    }
    
    private void setMappingColor(MappingColor mappingColor){
        this.mappingColor = mappingColor;
    }
}
