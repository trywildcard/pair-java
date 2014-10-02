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

    /**
     * Construct a product color.
     * @param displayName the name of the color as presented to the user.
     * @param swatchUrl link to an image with a sample of the color.
     */
    public ProductColor(String displayName, String swatchUrl) throws CardBuilderException {
        setDisplayName(displayName);
        setSwatchUrl(swatchUrl);
    }

    public String getDisplayName() {
        return displayName;
    }

    public URL getSwatchUrl() {
        return swatchUrl;
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


}
