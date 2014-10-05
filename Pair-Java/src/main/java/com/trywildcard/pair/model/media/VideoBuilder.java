package com.trywildcard.pair.model.media;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.model.Builder;
import com.trywildcard.pair.validation.ValidationTool;

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
    protected URL embeddedURL;
    protected Integer embeddedURLWidth;
    protected Integer embeddedURLHeight;

    //optional fields
    protected URL streamURL;
    protected Date publicationDate;
    protected String description;
    protected URL posterImageURL;
    protected String contributor;
    protected String source;
    protected String appLinkIos;
    protected String appLinkAndroid;

    /** Private Constructor **/
    private VideoBuilder() { }

    /**
     * Construct an <code>VideoBuilder</code> provided a title and embeddedURL.
     */
    public VideoBuilder(String title, String embeddedURL, Integer embeddedURLWidth, Integer embeddedURLHeight) throws CardBuilderException {
        title(title);
        embeddedURL(embeddedURL);
        embeddedURLHeight(embeddedURLHeight);
        embeddedURLWidth(embeddedURLWidth);
    }

    private VideoBuilder title(String title) throws CardBuilderException {
        boolean isValid = v.required(v.notNullOrEmpty(title), "Video Title cannot be blank.");
        if (isValid) {
            this.title = title;
        }
        return this;
    }

    private VideoBuilder embeddedURL(String embeddedURL) throws CardBuilderException {
        boolean isValid = v.required(v.notNullOrEmpty(embeddedURL), "Must specify a video embedded URL.");
        if (isValid) {
            try {
                this.embeddedURL = new URL(embeddedURL);
            } catch (MalformedURLException e) {
                v.required(v.fail(), "Could not parse URL from embedded URL.");
            }
        }

        return this;
    }

    public void embeddedURLHeight(Integer embeddedURLHeight) throws CardBuilderException {
        boolean isNotNull = v.required(v.notNull(embeddedURLHeight), "Video embeddedURLHeight must not be null");
        boolean isNotNegative = v.required(v.notNegative(embeddedURLHeight), "Video embeddedURLHeight must be a non-negative Integer.");

        if (isNotNull && isNotNegative) {
            this.embeddedURLHeight = embeddedURLHeight;
        }
    }

    public void embeddedURLWidth(Integer embeddedURLWidth) throws CardBuilderException {
        boolean isNotNull = v.optional(v.notNull(embeddedURLWidth), "Video embeddedURLWidth must not be null");
        boolean isNotNegative = v.optional(v.notNegative(embeddedURLWidth), "Video embeddedURLWidth must be a non-negative Integer.");

        if (isNotNull && isNotNegative) {
            this.embeddedURLWidth = embeddedURLWidth;
        }
    }

    public VideoBuilder streamURL(String streamURL) throws CardBuilderException {
        boolean isValid = v.optional(v.notNullOrEmpty(streamURL), "Must specify a video streamURL.");
        if (isValid) {
            try {
                this.streamURL = new URL(streamURL);
            } catch (MalformedURLException e) {
                v.optional(v.fail(), "Could not parse URL from video stream URL.");
            }
        }

        return this;
    }

    public VideoBuilder posterImageURL(String posterImageURL) throws CardBuilderException {
        boolean isValid = v.optional(v.notNullOrEmpty(posterImageURL), "Must specify a posterImageURL.");
        if (isValid) {
            try {
                this.posterImageURL = new URL(posterImageURL);
            } catch (MalformedURLException e) {
                v.optional(v.fail(), "Could not parse URL from video posterImageURL.");
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

    public VideoBuilder contributor(String contributor) {
        boolean isValid = v.optional(v.notNullOrEmpty(contributor), "Video Contributor cannot be blank.");
        if (isValid) {
            this.contributor = contributor;
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
