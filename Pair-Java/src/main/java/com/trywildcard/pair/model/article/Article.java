package com.trywildcard.pair.model.article;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.trywildcard.pair.model.media.Media;

import java.util.Date;
import java.util.List;

@JsonDeserialize(builder = ArticleBuilder.class)
public class Article {

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
    private Boolean isBreaking;
    private final String appLinkIos;
    private final String appLinkAndroid;
    private final List<String> keywords;

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
        this.author = builder.author;
        this.updatedDate = builder.updatedDate;
        this.media = builder.media;
        this.isBreaking = builder.isBreaking;
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

    public Boolean getIsBreaking() {
        return isBreaking;
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

        Article article = (Article) o;

        if (abstractContent != null ? !abstractContent.equals(article.abstractContent) : article.abstractContent != null)
            return false;
        if (appLinkAndroid != null ? !appLinkAndroid.equals(article.appLinkAndroid) : article.appLinkAndroid != null)
            return false;
        if (appLinkIos != null ? !appLinkIos.equals(article.appLinkIos) : article.appLinkIos != null) return false;
        if (author != null ? !author.equals(article.author) : article.author != null) return false;
        if (htmlContent != null ? !htmlContent.equals(article.htmlContent) : article.htmlContent != null) return false;
        if (isBreaking != null ? !isBreaking.equals(article.isBreaking) : article.isBreaking != null) return false;
        if (media != null ? !media.equals(article.media) : article.media != null) return false;
        if (publicationDate != null ? !publicationDate.equals(article.publicationDate) : article.publicationDate != null)
            return false;
        if (source != null ? !source.equals(article.source) : article.source != null) return false;
        if (title != null ? !title.equals(article.title) : article.title != null) return false;
        if (updatedDate != null ? !updatedDate.equals(article.updatedDate) : article.updatedDate != null) return false;

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
        result = 31 * result + (isBreaking != null ? isBreaking.hashCode() : 0);
        result = 31 * result + (appLinkIos != null ? appLinkIos.hashCode() : 0);
        result = 31 * result + (appLinkAndroid != null ? appLinkAndroid.hashCode() : 0);
        return result;
    }
}
