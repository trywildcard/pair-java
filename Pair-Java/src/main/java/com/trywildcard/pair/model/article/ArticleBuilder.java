package com.trywildcard.pair.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.model.Builder;
import com.trywildcard.pair.model.media.Media;
import com.trywildcard.pair.validation.ValidationTool;

import java.util.Date;
import java.util.List;

@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
public class ArticleBuilder implements Builder<Article> {

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
    protected Boolean isBreaking;
    protected String appLinkIos;
    protected String appLinkAndroid;

    /** Private Constructor **/
    private ArticleBuilder() { }

    /**
     * Construct an <code>ArticleBuilder</code> provided a title and htmlContent.
     */
    public ArticleBuilder(String title, String htmlContent) throws CardBuilderException {
        title(title);
        htmlContent(htmlContent);
        this.isBreaking = Boolean.FALSE;
    }

    /**
     * Construct an <code>ArticleBuilder</code> provided a title, htmlContent, and publicationDate.
     */
    public ArticleBuilder(String title, String htmlContent, Date publicationDate) throws CardBuilderException {
        title(title);
        htmlContent(htmlContent);
        publicationDate(publicationDate);
        this.isBreaking = Boolean.FALSE;
    }

    private ArticleBuilder title(String title) throws CardBuilderException {
        boolean isValid = v.required(v.notNullOrEmpty(title), "Article Title cannot be blank.");
        if (isValid) {
            this.title = title;
        }
        return this;
    }

    private ArticleBuilder htmlContent(String htmlContent) throws CardBuilderException {
        boolean isValid = v.required(v.notNullOrEmpty(htmlContent), "Article Html Content cannot be blank.");
        if (isValid) {
            this.htmlContent = htmlContent;
        }
        return this;
    }

    public ArticleBuilder publicationDate(Date publicationDate) {
        boolean isValid = v.optional(v.notNull(publicationDate), "Article Publication Date cannot be null.");
        if (isValid) {
            this.publicationDate = publicationDate;
        }
        return this;
    }

    public ArticleBuilder abstractContent(String abstractContent) {
        boolean isValid = v.optional(v.notNullOrEmpty(abstractContent), "Article Abstract cannot be blank.");
        if (isValid) {
            this.abstractContent = abstractContent;
        }
        return this;
    }

    public ArticleBuilder author(String author) {
        boolean isValid = v.optional(v.notNullOrEmpty(author), "Article Author cannot be blank.");
        if (isValid) {
            this.author = author;
        }
        return this;
    }

    public ArticleBuilder source(String source) {
        boolean isValid = v.optional(v.notNullOrEmpty(source), "Article Source cannot be blank.");
        if (isValid) {
            this.source = source;
        }
        return this;
    }

    public ArticleBuilder updatedDate(Date updatedDate) {
        boolean isValid = v.optional(v.notNull(updatedDate), "Article Updated Date cannot be null.");
        if (isValid) {
            this.updatedDate = updatedDate;
        }
        return this;
    }

    public ArticleBuilder media(Media media) {
        boolean isValid = v.optional(v.notNull(media), "Article Media cannot be null.");
        if (isValid) {
            this.media = media;
        }
        return this;
    }

    public ArticleBuilder isBreaking(Boolean isBreaking) {
        boolean isValid = v.optional(v.notNull(isBreaking), "Article isBreaking flag cannot be null.");
        if (isValid) {
            this.isBreaking = isBreaking;
        }
        return this;
    }

    public ArticleBuilder appLinkIos(String appLinkIos) {
        boolean isValid = v.optional(v.notNullOrEmpty(appLinkIos), "App Link Ios cannot be blank.");
        if (isValid) {
            this.appLinkIos = appLinkIos;
        }
        return this;
    }

    public ArticleBuilder appLinkAndroid(String appLinkAndroid) {
        boolean isValid = v.optional(v.notNullOrEmpty(appLinkAndroid), "App Link Android cannot be blank.");
        if (isValid) {
            this.appLinkAndroid = appLinkAndroid;
        }
        return this;
    }

    /**
     * Instantiate a <code>Article</code> with the data in this builder.
     * @return the constructed article
     */
    public Article build() {
        Article article = new Article(this);
        return article;
    }

    /**
     * Get a list of validation errors.
     * @return the list of errors.
     */
    public List<String> getErrors(){
        return v.getErrors();
    }


}
