package com.trywildcard.pair.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.validation.ValidationTool;

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
    private String swatchUrl;
    private String value;
    private MappingColor mappingColor;


    /**
     * Construct a product color.
     * @param displayName the name of the color as presented to the user.
     * @param value
     * @param swatchUrl link to an image with a sample of the color.
     * @param mappingColor map to a set of predefined colors
     */
    public ProductColor(String displayName, String value, String swatchUrl, MappingColor mappingColor) throws CardBuilderException {
        setDisplayName(displayName);
        setSwatchUrl(swatchUrl);
        setValue(value);
        setMappingColor(mappingColor);
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getSwatchUrl() {
        return swatchUrl;
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
    
    private void setDisplayName(String displayName) throws CardBuilderException {
        boolean isValid = v.required(v.notNullOrEmpty(displayName), "Must specify a displayName.");

        if (isValid) {
            this.displayName = displayName;
        }
    }
    
    private void setSwatchUrl(String swatchUrl){
        this.swatchUrl = swatchUrl;
    }
    
    private void setValue(String value){
        boolean isValid = v.optional(v.notEmpty(value), "Tried to set value to an empty string.");
        if (isValid) {
            this.value = value;
        }
    }
    
    private void setMappingColor(MappingColor mappingColor){
        this.mappingColor = mappingColor;
    }
}
