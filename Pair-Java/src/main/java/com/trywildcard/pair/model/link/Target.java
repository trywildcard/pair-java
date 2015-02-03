package com.trywildcard.pair.model.link;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.validation.ValidationTool;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * Created by karthiksenthil on 2/3/15.
 */
public class Target {

    @JsonIgnore
    protected ValidationTool v = new ValidationTool();

    private URL url;

    private String title;
    private String description;
    private Date publicationDate;

    /**
     * Needed for Jackson deserialization *
     */
    private Target() { }

    public Target(String url) throws CardBuilderException {
        url(url);
    }

    protected void url(String url) throws CardBuilderException {
        boolean isValid = v.required(v.notNullOrEmpty(url), "Must specify a url.");
        if (isValid) {
            try {
                this.url = new URL(url);
            } catch (MalformedURLException e) {
                v.required(v.fail(), "Could not parse URL from webUrl string.");
            }
        }
    }

    public URL getUrl() { return url; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws CardBuilderException {
        boolean isValid = v.optional(v.notNullOrEmpty(description), "Description cannot be blank.");
        if (isValid) {
            this.description = description;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws CardBuilderException {
        boolean isValid = v.optional(v.notNullOrEmpty(title), "Title cannot be blank.");
        if (isValid) {
            this.title = title;
        }
    }

    public void setPublicationDate(Date publicationDate) {
        boolean isValid = v.optional(v.notNull(publicationDate), "Link Publication Date cannot be null.");
        if (isValid) {
            this.publicationDate = publicationDate;
        }
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    /**
     * Get a list of validation errors.
     * @return the list of errors.
     */
    @JsonIgnore
    public List<String> getErrors(){
        return v.getErrors();
    }

}
