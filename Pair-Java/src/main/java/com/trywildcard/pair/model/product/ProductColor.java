package com.trywildcard.pair.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.validation.ValidationTool;

import java.net.MalformedURLException;
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
    private URL swatchUrl;
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

    public URL getSwatchUrl() {
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
        if (swatchUrl == null){
            return;
        }

        try {
            this.swatchUrl = new URL(swatchUrl);
        } catch (MalformedURLException e) {
            v.optional(v.fail(), "Could not parse URL from swatchUrl string.");
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductColor that = (ProductColor) o;

        if (displayName != null ? !displayName.equals(that.displayName) : that.displayName != null) return false;
        if (mappingColor != that.mappingColor) return false;
        if (swatchUrl != null ? !swatchUrl.equals(that.swatchUrl) : that.swatchUrl != null) return false;
        if (v != null ? !v.equals(that.v) : that.v != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = v != null ? v.hashCode() : 0;
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        result = 31 * result + (swatchUrl != null ? swatchUrl.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (mappingColor != null ? mappingColor.hashCode() : 0);
        return result;
    }
}
