package com.trywildcard.pair.model.media;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.validation.ValidationTool;

import java.net.MalformedURLException;
import java.net.URL;


public class Image extends Media {

    @JsonIgnore
    protected ValidationTool v = new ValidationTool();

    private URL imageUrl;
    private String imageCaption;

    private final MediaType mediaType = MediaType.IMAGE;

    public Image(String imageUrl) throws CardBuilderException {
        imageUrl(imageUrl);
    }

    public Image(String imageUrl, String imageCaption) throws CardBuilderException  {
        imageUrl(imageUrl);
        imageCaption(imageCaption);
    }

    private void imageUrl(String imageUrl) throws CardBuilderException {
        boolean isValid = v.required(v.notNullOrEmpty(imageUrl), "Must specify a image webUrl.");
        if (isValid) {
            try {
                this.imageUrl = new URL(imageUrl);
            } catch (MalformedURLException e) {
                v.required(v.fail(), "Could not parse URL from imageUrl string.");
            }
        }
    }

    private void imageCaption(String imageCaption) {
        boolean isValid = v.optional(v.notNullOrEmpty(imageCaption), "Must specify a valid image caption.");
        if (isValid) {
            this.imageCaption = imageCaption;
        }
    }

    public String getImageCaption() {
        return imageCaption;
    }

    public URL getImageUrl() {
        return imageUrl;
    }

    public MediaType getMediaType() {
        return mediaType;
    }
}
