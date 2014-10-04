package com.trywildcard.pair.model.article;

import com.trywildcard.pair.model.media.Media;

import java.util.Date;

public class Article {

    //required fields
    private String title;
    private String htmlContent;

    //optional fields
    private Date publicationDate;
    private String abstractContent;
    private String source;
    private String byLine;
    private Date updatedDate;
    private Media media;
    private Boolean isBreaking;

    /**
     * Construct a article using a <code>ArticleBuilder</code>, which is responsible for validations.
     * @param builder the builder for this article.
     */
    public Article(ArticleBuilder builder) {
        this.title = builder.title;
        this.htmlContent = builder.htmlContent;
        this.publicationDate = builder.publicationDate;
        this.abstractContent = builder.abstractContent;
        this.source = builder.source;
        this.byLine = builder.byLine;
        this.updatedDate = builder.updatedDate;
        this.media = builder.media;
        this.isBreaking = builder.isBreaking;
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

    public String getByLine() {
        return byLine;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public Media getMedia() {
        return media;
    }

    public Boolean getIsBreaking() {
        return isBreaking;
    }
}
