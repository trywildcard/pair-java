package com.trywildcard.pair.model.review;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.extraction.MetaTagModel;
import com.trywildcard.pair.model.Builder;
import com.trywildcard.pair.model.media.Image;
import com.trywildcard.pair.model.media.Media;
import com.trywildcard.pair.validation.ValidationTool;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;

@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
public class ReviewBuilder implements Builder<Review> {

    @JsonIgnore
    protected ValidationTool v = new ValidationTool();

    //required fields
    protected String title;
    protected String htmlContent;

    //optional fields
    protected Date publicationDate;
    protected String abstractContent;
    protected String source;
    protected String author;
    protected Date updatedDate;
    protected Media media;
    protected String productName;
    protected Rating rating;
    protected String appLinkIos;
    protected String appLinkAndroid;

    /**
     * Construct an <code>ReviewBuilder</code> provided a title and htmlContent.
     */
    public ReviewBuilder(String title, String htmlContent) throws CardBuilderException {
        title(title);
        htmlContent(htmlContent);
    }

    /**
     * Construct an ReviewBuilder provided a meta tag model
     */
    public ReviewBuilder(MetaTagModel metaTagModel) throws CardBuilderException {
        if (metaTagModel == null) {
            throw new CardBuilderException("MetaTagModel is required");
        }

        if (StringUtils.isEmpty(metaTagModel.getTitle()) || StringUtils.isEmpty(metaTagModel.getHtmlContent())) {
            throw new CardBuilderException("Review title is not contained in meta tags and/or review html content was unable to be captured" +
                    " - both of which are required to create a ReviewBuilder");
        }

        title(metaTagModel.getTitle());
        htmlContent(metaTagModel.getHtmlContent());

        /* Trying to set optional fields if found */
        try {
            media(new Image(metaTagModel.getImageUrl()));
        } catch (CardBuilderException cbe) {
            //if exception is thrown, let's ignore since media is optional for an article
        }

        abstractContent(metaTagModel.getDescription());
        appLinkIos(metaTagModel.getAppLinkIos());
        appLinkAndroid(metaTagModel.getAppLinkAndroid());
    }

    /**
     * Construct an <code>ReviewBuilder</code> provided a title, htmlContent, and publicationDate.
     */
    public ReviewBuilder(String title, String htmlContent, Date publicationDate) throws CardBuilderException {
        title(title);
        htmlContent(htmlContent);
        publicationDate(publicationDate);
    }

    private ReviewBuilder title(String title) throws CardBuilderException {
        boolean isValid = v.required(v.notNullOrEmpty(title), "Review Title cannot be blank.");
        if (isValid) {
            this.title = title;
        }
        return this;
    }

    private ReviewBuilder htmlContent(String htmlContent) throws CardBuilderException {
        boolean isValid = v.required(v.notNullOrEmpty(htmlContent), "Review Html Content cannot be blank.");
        if (isValid) {
            this.htmlContent = htmlContent;
        }
        return this;
    }

    public ReviewBuilder publicationDate(Date publicationDate) {
        boolean isValid = v.optional(v.notNull(publicationDate), "Review Publication Date cannot be null.");
        if (isValid) {
            this.publicationDate = publicationDate;
        }
        return this;
    }

    public ReviewBuilder abstractContent(String abstractContent) {
        boolean isValid = v.optional(v.notNullOrEmpty(abstractContent), "Review Abstract cannot be blank.");
        if (isValid) {
            this.abstractContent = abstractContent;
        }
        return this;
    }

    public ReviewBuilder author(String author) {
        boolean isValid = v.optional(v.notNullOrEmpty(author), "Review Author cannot be blank.");
        if (isValid) {
            this.author = author;
        }
        return this;
    }

    public ReviewBuilder source(String source) {
        boolean isValid = v.optional(v.notNullOrEmpty(source), "Review Source cannot be blank.");
        if (isValid) {
            this.source = source;
        }
        return this;
    }

    public ReviewBuilder productName(String productName) {
        boolean isValid = v.optional(v.notNullOrEmpty(productName), "Review Product Name cannot be blank.");
        if (isValid) {
            this.productName = productName;
        }
        return this;
    }

    public ReviewBuilder updatedDate(Date updatedDate) {
        boolean isValid = v.optional(v.notNull(updatedDate), "Review Updated Date cannot be null.");
        if (isValid) {
            this.updatedDate = updatedDate;
        }
        return this;
    }

    public ReviewBuilder media(Media media) {
        boolean isValid = v.optional(v.notNull(media), "Review Media cannot be null.");
        if (isValid) {
            this.media = media;
        }
        return this;
    }

    public ReviewBuilder rating(Rating rating) {
        boolean isValid = v.optional(v.notNull(rating), "Review Rating cannot be null.");
        if (isValid) {
            this.rating = rating;
        }
        return this;
    }

    public ReviewBuilder appLinkIos(String appLinkIos) {
        boolean isValid = v.optional(v.notNullOrEmpty(appLinkIos), "App Link Ios cannot be blank.");
        if (isValid) {
            this.appLinkIos = appLinkIos;
        }
        return this;
    }

    public ReviewBuilder appLinkAndroid(String appLinkAndroid) {
        boolean isValid = v.optional(v.notNullOrEmpty(appLinkAndroid), "App Link Android cannot be blank.");
        if (isValid) {
            this.appLinkAndroid = appLinkAndroid;
        }
        return this;
    }

    /** Private Constructor **/
    private ReviewBuilder() { }

    /**
     * Instantiate a <code>Article</code> with the data in this builder.
     * @return the constructed article
     */
    public Review build() {
        Review review = new Review(this);
        return review;
    }

    /**
     * Get a list of validation errors.
     * @return the list of errors.
     */
    public List<String> getErrors(){
        return v.getErrors();
    }

}
