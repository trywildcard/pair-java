package com.trywildcard.pair.model.review;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.trywildcard.pair.model.media.Media;

import java.util.Date;
import java.util.List;

@JsonDeserialize(builder = ReviewBuilder.class)
public class Review {

    //required fields
    private String title;
    private String htmlContent;

    //optional fields
    private Date publicationDate;
    private String abstractContent;
    private String source;
    private String author;
    private Date updatedDate;
    private Media media;
    private String productName;
    //todo: should rating be required?
    private Rating rating;
    private String appLinkIos;
    private String appLinkAndroid;
    private List<String> keywords;

    public Review(ReviewBuilder builder) {
        this.title = builder.title;
        this.htmlContent = builder.htmlContent;
        this.publicationDate = builder.publicationDate;
        this.abstractContent = builder.abstractContent;
        this.source = builder.source;
        this.author = builder.author;
        this.updatedDate = builder.updatedDate;
        this.media = builder.media;
        this.productName = builder.productName;
        this.rating = builder.rating;
        this.appLinkAndroid = builder.appLinkAndroid;
        this.appLinkIos = builder.appLinkIos;
        this.keywords = builder.keywords;
    }

    public String getTitle() {
        return title;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public String getAbstractContent() {
        return abstractContent;
    }

    public String getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public Media getMedia() {
        return media;
    }

    public String getProductName() {
        return productName;
    }

    public Rating getRating() {
        return rating;
    }

    public String getAppLinkIos() {
        return appLinkIos;
    }

    public String getAppLinkAndroid() {
        return appLinkAndroid;
    }

    public List<String> getKeywords() { return keywords; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (abstractContent != null ? !abstractContent.equals(review.abstractContent) : review.abstractContent != null)
            return false;
        if (appLinkAndroid != null ? !appLinkAndroid.equals(review.appLinkAndroid) : review.appLinkAndroid != null)
            return false;
        if (appLinkIos != null ? !appLinkIos.equals(review.appLinkIos) : review.appLinkIos != null) return false;
        if (author != null ? !author.equals(review.author) : review.author != null) return false;
        if (htmlContent != null ? !htmlContent.equals(review.htmlContent) : review.htmlContent != null) return false;
        if (media != null ? !media.equals(review.media) : review.media != null) return false;
        if (productName != null ? !productName.equals(review.productName) : review.productName != null) return false;
        if (publicationDate != null ? !publicationDate.equals(review.publicationDate) : review.publicationDate != null)
            return false;
        if (rating != null ? !rating.equals(review.rating) : review.rating != null) return false;
        if (source != null ? !source.equals(review.source) : review.source != null) return false;
        if (title != null ? !title.equals(review.title) : review.title != null) return false;
        if (updatedDate != null ? !updatedDate.equals(review.updatedDate) : review.updatedDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (htmlContent != null ? htmlContent.hashCode() : 0);
        result = 31 * result + (publicationDate != null ? publicationDate.hashCode() : 0);
        result = 31 * result + (abstractContent != null ? abstractContent.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (updatedDate != null ? updatedDate.hashCode() : 0);
        result = 31 * result + (media != null ? media.hashCode() : 0);
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (appLinkIos != null ? appLinkIos.hashCode() : 0);
        result = 31 * result + (appLinkAndroid != null ? appLinkAndroid.hashCode() : 0);
        return result;
    }
}
