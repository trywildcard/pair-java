package com.trywildcard.pair.model.media;

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
    private URL embeddedUrl;
    private Integer embeddedUrlWidth;
    private Integer embeddedUrlHeight;

    //optional fields
    private URL streamUrl;
    private String streamContentType;
    private Date publicationDate;
    private String description;
    private URL posterImageUrl;
    private String creator;
    private String source;

    /** Needed for Jackson deserialization **/
    private Video() {
        super(MediaType.VIDEO);
    }

    public Video(VideoBuilder builder) {
        super(MediaType.VIDEO);
        this.title = builder.title;
        this.embeddedUrl = builder.embeddedUrl;
        this.embeddedUrlHeight = builder.embeddedUrlHeight;
        this.embeddedUrlWidth = builder.embeddedUrlWidth;
        this.streamUrl = builder.streamUrl;
        this.streamContentType = builder.streamContentType;
        this.publicationDate = builder.publicationDate;
        this.description = builder.description;
        this.posterImageUrl = builder.posterImageUrl;
        this.creator = builder.creator;
        this.source = builder.source;
    }

    public MediaType getType() {
        return type;
    }

    public String getSource() {
        return source;
    }

    public String getCreator() {
        return creator;
    }

    public URL getPosterImageUrl() {
        return posterImageUrl;
    }

    public String getDescription() {
        return description;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public URL getStreamUrl() {
        return streamUrl;
    }

    public Integer getEmbeddedUrlHeight() {
        return embeddedUrlHeight;
    }

    public Integer getEmbeddedUrlWidth() {
        return embeddedUrlWidth;
    }

    public URL getEmbeddedUrl() {
        return embeddedUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getStreamContentType() {
        return streamContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Video video = (Video) o;

        if (creator != null ? !creator.equals(video.creator) : video.creator != null) return false;
        if (description != null ? !description.equals(video.description) : video.description != null) return false;
        if (embeddedUrl != null ? !embeddedUrl.equals(video.embeddedUrl) : video.embeddedUrl != null) return false;
        if (embeddedUrlHeight != null ? !embeddedUrlHeight.equals(video.embeddedUrlHeight) : video.embeddedUrlHeight != null)
            return false;
        if (embeddedUrlWidth != null ? !embeddedUrlWidth.equals(video.embeddedUrlWidth) : video.embeddedUrlWidth != null)
            return false;
        if (posterImageUrl != null ? !posterImageUrl.equals(video.posterImageUrl) : video.posterImageUrl != null)
            return false;
        if (publicationDate != null ? !publicationDate.equals(video.publicationDate) : video.publicationDate != null)
            return false;
        if (source != null ? !source.equals(video.source) : video.source != null) return false;
        if (streamContentType != null ? !streamContentType.equals(video.streamContentType) : video.streamContentType != null)
            return false;
        if (streamUrl != null ? !streamUrl.equals(video.streamUrl) : video.streamUrl != null) return false;
        if (title != null ? !title.equals(video.title) : video.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (embeddedUrl != null ? embeddedUrl.hashCode() : 0);
        result = 31 * result + (embeddedUrlWidth != null ? embeddedUrlWidth.hashCode() : 0);
        result = 31 * result + (embeddedUrlHeight != null ? embeddedUrlHeight.hashCode() : 0);
        result = 31 * result + (streamUrl != null ? streamUrl.hashCode() : 0);
        result = 31 * result + (streamContentType != null ? streamContentType.hashCode() : 0);
        result = 31 * result + (publicationDate != null ? publicationDate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (posterImageUrl != null ? posterImageUrl.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        return result;
    }
}
