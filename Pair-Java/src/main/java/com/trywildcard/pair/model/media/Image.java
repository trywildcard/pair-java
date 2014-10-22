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

    /** Needed for Jackson deserialization **/
    private Image() {
        super(MediaType.IMAGE);
    }

    public Image(String imageUrl) throws CardBuilderException {
        super(MediaType.IMAGE);
        setImageUrl(imageUrl);
    }

    public Image(String imageUrl, String imageCaption) throws CardBuilderException  {
        super(MediaType.IMAGE);
        setImageUrl(imageUrl);
        setImageCaption(imageCaption);
    }

    private void setImageUrl(String imageUrl) throws CardBuilderException {
        boolean isValid = v.required(v.notNullOrEmpty(imageUrl), "Must specify a image webUrl.");
        if (isValid) {
            try {
                this.imageUrl = new URL(imageUrl);
            } catch (MalformedURLException e) {
                v.required(v.fail(), "Could not parse URL from imageUrl string.");
            }
        }
    }

    public void setImageCaption(String imageCaption) {
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

    public MediaType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        if (imageCaption != null ? !imageCaption.equals(image.imageCaption) : image.imageCaption != null) return false;
        if (imageUrl != null ? !imageUrl.equals(image.imageUrl) : image.imageUrl != null) return false;
        if (type != image.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = v != null ? v.hashCode() : 0;
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (imageCaption != null ? imageCaption.hashCode() : 0);
        return result;
    }
}
