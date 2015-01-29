package com.trywildcard.pair.model.media;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.extraction.MetaTagModel;
import com.trywildcard.pair.validation.ValidationTool;
import org.apache.commons.lang3.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

public class Image extends Media {

    @JsonIgnore
    protected ValidationTool v = new ValidationTool();

    private URL imageUrl;
    private String imageCaption;
    private String title;
    private String author;
    private Integer width;
    private Integer height;
    private Date publicationDate;

    /**
     * Needed for Jackson deserialization *
     */
    private Image() {
        super(MediaType.IMAGE);
    }

    public Image(String imageUrl) throws CardBuilderException {
        super(MediaType.IMAGE);
        setImageUrl(imageUrl);
    }

    public Image(String imageUrl, String imageCaption) throws CardBuilderException {
        super(MediaType.IMAGE);
        setImageUrl(imageUrl);
        setImageCaption(imageCaption);
    }

    public Image(MetaTagModel metaTagModel) throws CardBuilderException {
        super(MediaType.IMAGE);

        if (metaTagModel == null) {
            throw new CardBuilderException("MetaTagModel is required");
        }

        if (StringUtils.isEmpty(metaTagModel.getImageUrl())) {
            throw new CardBuilderException("Image Url is not contained in meta tags" +
                    " - which is required to create an Image");
        }

        setImageUrl(metaTagModel.getImageUrl());

        /* optional fields to attempt to fill in */
        setTitle(metaTagModel.getTitle());
        setImageCaption(metaTagModel.getDescription());

        try {
            setWidth(Integer.parseInt(metaTagModel.getImageWidth()));
            setHeight(Integer.parseInt(metaTagModel.getImageHeight()));
        } catch (NumberFormatException nfe) {
            //do nothing since these are not required
        }
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

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        boolean isValid = v.optional(v.notNull(publicationDate), "Image Publication Date cannot be null.");
        if (isValid) {
            this.publicationDate = publicationDate;
        }
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        boolean isNotNull = v.optional(v.notNull(height), "Image height must not be null");
        boolean isNotNegative = v.optional(v.notNegative(height), "Image height must be a non-negative Integer.");

        if (isNotNull && isNotNegative) {
            this.height = height;
        }
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        boolean isNotNull = v.optional(v.notNull(width), "Image width must not be null");
        boolean isNotNegative = v.optional(v.notNegative(width), "Image width must be a non-negative Integer.");

        if (isNotNull && isNotNegative) {
            this.width = width;
        }
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        boolean isValid = v.optional(v.notNullOrEmpty(author), "Author cannot be blank.");
        if (isValid) {
            this.author = author;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        boolean isValid = v.optional(v.notNullOrEmpty(title), "Title cannot be blank.");
        if (isValid) {
            this.title = title;
        }
    }

    public MediaType getType() {
        return type;
    }

    /**
     * Get a list of validation errors.
     * @return the list of errors.
     */
    public List<String> getErrors(){
        return v.getErrors();
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
