package com.trywildcard.pair.model.media;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.net.URL;
import java.util.Date;

/**
 * Created by karthiksenthil on 10/5/14.
 */
@JsonDeserialize(builder = VideoBuilder.class)
public class Video extends Media {

    //required fields
    private String title;
    private URL embeddedURL;
    private Integer embeddedURLWidth;
    private Integer embeddedURLHeight;

    //optional fields
    private URL streamURL;
    private Date publicationDate;
    private String description;
    private URL posterImageURL;
    private String contributor;
    private String source;
    private String appLinkIos;
    private String appLinkAndroid;

    /** Needed for Jackson deserialization **/
    private Video() {
        super(MediaType.VIDEO);
    }

    public Video(VideoBuilder builder) {
        super(MediaType.VIDEO);
        this.title = builder.title;
        this.embeddedURL = builder.embeddedURL;
        this.embeddedURLHeight = builder.embeddedURLHeight;
        this.embeddedURLWidth = builder.embeddedURLWidth;
        this.streamURL = builder.streamURL;
        this.publicationDate = builder.publicationDate;
        this.description = builder.description;
        this.posterImageURL = builder.posterImageURL;
        this.contributor = builder.contributor;
        this.source = builder.source;
        this.appLinkAndroid = builder.appLinkAndroid;
        this.appLinkIos = builder.appLinkIos;
    }

    public MediaType getType() {
        return type;
    }

    public String getAppLinkAndroid() {
        return appLinkAndroid;
    }

    public String getAppLinkIos() {
        return appLinkIos;
    }

    public String getSource() {
        return source;
    }

    public String getContributor() {
        return contributor;
    }

    public URL getPosterImageURL() {
        return posterImageURL;
    }

    public String getDescription() {
        return description;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public URL getStreamURL() {
        return streamURL;
    }

    public Integer getEmbeddedURLHeight() {
        return embeddedURLHeight;
    }

    public Integer getEmbeddedURLWidth() {
        return embeddedURLWidth;
    }

    public URL getEmbeddedURL() {
        return embeddedURL;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Video video = (Video) o;

        if (appLinkAndroid != null ? !appLinkAndroid.equals(video.appLinkAndroid) : video.appLinkAndroid != null)
            return false;
        if (appLinkIos != null ? !appLinkIos.equals(video.appLinkIos) : video.appLinkIos != null) return false;
        if (contributor != null ? !contributor.equals(video.contributor) : video.contributor != null) return false;
        if (description != null ? !description.equals(video.description) : video.description != null) return false;
        if (embeddedURL != null ? !embeddedURL.equals(video.embeddedURL) : video.embeddedURL != null) return false;
        if (embeddedURLHeight != null ? !embeddedURLHeight.equals(video.embeddedURLHeight) : video.embeddedURLHeight != null)
            return false;
        if (embeddedURLWidth != null ? !embeddedURLWidth.equals(video.embeddedURLWidth) : video.embeddedURLWidth != null)
            return false;
        if (posterImageURL != null ? !posterImageURL.equals(video.posterImageURL) : video.posterImageURL != null)
            return false;
        if (publicationDate != null ? !publicationDate.equals(video.publicationDate) : video.publicationDate != null)
            return false;
        if (source != null ? !source.equals(video.source) : video.source != null) return false;
        if (streamURL != null ? !streamURL.equals(video.streamURL) : video.streamURL != null) return false;
        if (title != null ? !title.equals(video.title) : video.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (embeddedURL != null ? embeddedURL.hashCode() : 0);
        result = 31 * result + (embeddedURLWidth != null ? embeddedURLWidth.hashCode() : 0);
        result = 31 * result + (embeddedURLHeight != null ? embeddedURLHeight.hashCode() : 0);
        result = 31 * result + (streamURL != null ? streamURL.hashCode() : 0);
        result = 31 * result + (publicationDate != null ? publicationDate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (posterImageURL != null ? posterImageURL.hashCode() : 0);
        result = 31 * result + (contributor != null ? contributor.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (appLinkIos != null ? appLinkIos.hashCode() : 0);
        result = 31 * result + (appLinkAndroid != null ? appLinkAndroid.hashCode() : 0);
        return result;
    }
}
