package com.trywildcard.pair.model.creator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.model.Builder;
import com.trywildcard.pair.validation.ValidationTool;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by cmcewen on 2/2/15.
 */

@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
public class CreatorBuilder implements Builder<Creator> {

    @JsonIgnore
    protected ValidationTool v = new ValidationTool();

    //required fields
    protected String name;
    protected URL favicon;

    //optional fields
    protected String iosAppStoreUrl;
    protected String androidAppStoreUrl;
    protected URL url;

    /** Private Constructor **/
    private CreatorBuilder() { }

    /**
     * Construct an <code>CreatorBuilder</code> provided a name and htmlContent.
     */
    public CreatorBuilder(String name, String favicon) throws CardBuilderException {
        name(name);
        favicon(favicon);
    }

    private CreatorBuilder name(String name) throws CardBuilderException {
        boolean isValid = v.required(v.notNullOrEmpty(name), "creator Name cannot be blank.");
        if (isValid) {
            this.name = name;
        }
        return this;
    }

    private CreatorBuilder favicon(String favicon) throws CardBuilderException {
        boolean isValid = v.required(v.notNullOrEmpty(favicon), "creator favicon cannot be blank.");
        if (isValid) {
            try {
                this.favicon = new URL(favicon);
            } catch (MalformedURLException e) {
                v.required(v.fail(), "Could not parse URL from favicon string.");
            }
        }
        return this;
    }

    public CreatorBuilder iosAppStoreUrl(String iosAppStoreUrl) {
        boolean isValid = v.optional(v.notNullOrEmpty(iosAppStoreUrl), "App store URL cannot be blank.");
        if (isValid) {
            this.iosAppStoreUrl = iosAppStoreUrl;
        }
        return this;
    }

    public CreatorBuilder androidAppStoreUrl(String androidAppStoreUrl) {
        boolean isValid = v.optional(v.notNullOrEmpty(androidAppStoreUrl), "App store URL cannot be blank.");
        if (isValid) {
            this.androidAppStoreUrl = androidAppStoreUrl;
        }
        return this;
    }

    public CreatorBuilder url(String url) throws CardBuilderException {
        boolean isValid = v.optional(v.notNullOrEmpty(url), "Creator URL cannot be blank.");
        if (isValid) {
            try {
                this.url = new URL(url);
            } catch (MalformedURLException e) {
                v.optional(v.fail(), "Could not parse URL from url string.");
            }
        }
        return this;
    }

    /**
     * Instantiate a <code>creator</code> with the data in this builder.
     * @return the constructed creator
     */
    public Creator build() {
        Creator creator = new Creator(this);
        return creator;
    }

    /**
     * Get a list of validation errors.
     * @return the list of errors.
     */
    public List<String> getErrors(){
        return v.getErrors();
    }


}
