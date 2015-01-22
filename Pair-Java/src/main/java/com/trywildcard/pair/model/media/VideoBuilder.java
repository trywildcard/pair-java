package com.trywildcard.pair.model.media;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.extraction.MetaTagModel;
import com.trywildcard.pair.model.Builder;
import com.trywildcard.pair.validation.ValidationTool;
import org.apache.commons.lang3.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * Created by karthiksenthil on 10/5/14.
 */
@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
public class VideoBuilder implements Builder<Video> {

    @JsonIgnore
    protected ValidationTool v = new ValidationTool();

    protected String title;
    protected URL embeddedUrl;
    protected Integer embeddedUrlWidth;
    protected Integer embeddedUrlHeight;

    //optional fields
    protected URL streamUrl;
    protected String streamContentType;
    protected Date publicationDate;
    protected String description;
    protected URL posterImageUrl;
    protected String creator;
    protected String source;
    protected String appLinkIos;
    protected String appLinkAndroid;

    /** Private Constructor **/
    private VideoBuilder() { }

    /**
     * Construct an <code>VideoBuilder</code> provided a title and embeddedUrl.
     */
    public VideoBuilder(String title, String embeddedUrl, Integer embeddedUrlWidth, Integer embeddedUrlHeight) throws CardBuilderException {
        title(title);
        embeddedUrl(embeddedUrl);
        embeddedUrlHeight(embeddedUrlHeight);
        embeddedUrlWidth(embeddedUrlWidth);
    }

    /**
     * Construct an VideoBuilder provided a meta tag model
     */
    public VideoBuilder(MetaTagModel metaTagModel) throws CardBuilderException {

        try {
            if (metaTagModel == null) {
                throw new CardBuilderException("MetaTagModel is required");
            }

            if (StringUtils.isEmpty(metaTagModel.getTitle()) || StringUtils.isEmpty(metaTagModel.getVideoUrl())
                    || StringUtils.isEmpty(metaTagModel.getVideoHeight()) || StringUtils.isEmpty(metaTagModel.getVideoWidth())) {
                throw new CardBuilderException("Either video title, url, width, or height is not contained in meta tags" +
                        " - all of which are required to create a VideoBuilder");
            }

            title(metaTagModel.getTitle());
            embeddedUrl(metaTagModel.getVideoUrl());
            embeddedUrlHeight(Integer.parseInt(metaTagModel.getVideoHeight()));
            embeddedUrlWidth(Integer.parseInt(metaTagModel.getVideoWidth()));

            /* optional fields to attempt to fill in */
            description(metaTagModel.getDescription());
            posterImageUrl(metaTagModel.getImageUrl());
            appLinkIos(metaTagModel.getAppLinkIos());
            appLinkAndroid(metaTagModel.getAppLinkAndroid());
        } catch (NumberFormatException nfe) {
            throw new CardBuilderException("Unable to convert video width or height meta tag value to a valid integer, " +
                    "which is required to construct a VideoBuilder", nfe);
        }
    }

    private VideoBuilder title(String title) throws CardBuilderException {
        boolean isValid = v.required(v.notNullOrEmpty(title), "Video Title cannot be blank.");
        if (isValid) {
            this.title = title;
        }
        return this;
    }

    private VideoBuilder embeddedUrl(String embeddedURL) throws CardBuilderException {
        boolean isValid = v.required(v.notNullOrEmpty(embeddedURL), "Must specify a video embedded URL.");
        if (isValid) {
            try {
                this.embeddedUrl = new URL(embeddedURL);
            } catch (MalformedURLException e) {
                v.required(v.fail(), "Could not parse URL from embedded URL.");
            }
        }

        return this;
    }

    private void embeddedUrlHeight(Integer embeddedURLHeight) throws CardBuilderException {
        boolean isNotNull = v.required(v.notNull(embeddedURLHeight), "Video embeddedUrlHeight must not be null");
        boolean isNotNegative = v.required(v.notNegative(embeddedURLHeight), "Video embeddedUrlHeight must be a non-negative Integer.");

        if (isNotNull && isNotNegative) {
            this.embeddedUrlHeight = embeddedURLHeight;
        }
    }

    private void embeddedUrlWidth(Integer embeddedURLWidth) throws CardBuilderException {
        boolean isNotNull = v.required(v.notNull(embeddedURLWidth), "Video embeddedUrlWidth must not be null");
        boolean isNotNegative = v.required(v.notNegative(embeddedURLWidth), "Video embeddedUrlWidth must be a non-negative Integer.");

        if (isNotNull && isNotNegative) {
            this.embeddedUrlWidth = embeddedURLWidth;
        }
    }

    public VideoBuilder streamUrl(String streamUrl) {
        boolean isValid = v.optional(v.notNullOrEmpty(streamUrl), "Must specify a video streamUrl.");
        if (isValid) {
            try {
                this.streamUrl = new URL(streamUrl);
            } catch (MalformedURLException e) {
                v.optional(v.fail(), "Could not parse URL from video stream URL.");
            }
        }

        return this;
    }

    public VideoBuilder streamContentType(String streamContentType) {
        boolean isValid = v.optional(v.notNullOrEmpty(streamContentType), "Must specify a video streamUrl.");
        if (isValid) {
            this.streamContentType = streamContentType;
        }


        return this;
    }

    public VideoBuilder posterImageUrl(String posterImageUrl) {
        boolean isValid = v.optional(v.notNullOrEmpty(posterImageUrl), "Must specify a posterImageUrl.");
        if (isValid) {
            try {
                this.posterImageUrl = new URL(posterImageUrl);
            } catch (MalformedURLException e) {
                v.optional(v.fail(), "Could not parse URL from video posterImageUrl.");
            }
        }

        return this;
    }


    public VideoBuilder publicationDate(Date publicationDate) {
        boolean isValid = v.optional(v.notNull(publicationDate), "Article Publication Date cannot be null.");
        if (isValid) {
            this.publicationDate = publicationDate;
        }
        return this;
    }

    public VideoBuilder description(String description) {
        boolean isValid = v.optional(v.notNullOrEmpty(description), "Video description cannot be blank.");
        if (isValid) {
            this.description = description;
        }
        return this;
    }

    public VideoBuilder creator(String creator) {
        boolean isValid = v.optional(v.notNullOrEmpty(creator), "Video Creator cannot be blank.");
        if (isValid) {
            this.creator = creator;
        }
        return this;
    }

    public VideoBuilder source(String source) {
        boolean isValid = v.optional(v.notNullOrEmpty(source), "Vdieo Source cannot be blank.");
        if (isValid) {
            this.source = source;
        }
        return this;
    }


    public VideoBuilder appLinkIos(String appLinkIos) {
        boolean isValid = v.optional(v.notNullOrEmpty(appLinkIos), "App Link Ios cannot be blank.");
        if (isValid) {
            this.appLinkIos = appLinkIos;
        }
        return this;
    }

    public VideoBuilder appLinkAndroid(String appLinkAndroid) {
        boolean isValid = v.optional(v.notNullOrEmpty(appLinkAndroid), "App Link Android cannot be blank.");
        if (isValid) {
            this.appLinkAndroid = appLinkAndroid;
        }
        return this;
    }

    /**
     * Instantiate a <code>Video</code> with the data in this builder.
     * @return the constructed video
     */
    public Video build() {
        Video video = new Video(this);
        return video;
    }

    /**
     * Get a list of validation errors.
     * @return the list of errors.
     */
    public List<String> getErrors(){
        return v.getErrors();
    }


}
