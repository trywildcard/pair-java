package com.trywildcard.pair.model.summary;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.extraction.MetaTagModel;
import com.trywildcard.pair.model.media.Image;
import com.trywildcard.pair.model.media.Media;
import com.trywildcard.pair.validation.ValidationTool;

import java.util.List;

public class Summary {

    @JsonIgnore
    protected ValidationTool v = new ValidationTool();

    private String title;
    private String description;
    private Media media;

    /**
     * Needed for Jackson deserialization *
     */
    private Summary() { }

    public Summary(String title, String description) throws CardBuilderException {
        setTitle(title);
        setDescription(description);
    }

    public Summary(MetaTagModel metaTagModel) throws CardBuilderException {
        if (metaTagModel == null) {
            throw new CardBuilderException("MetaTagModel is required");
        }

        /* required fields */
        setTitle(metaTagModel.getTitle());
        setDescription(metaTagModel.getDescription());

        /* Trying to set optional fields if found */
        try {
            setMedia(new Image(metaTagModel.getImageUrl()));
        } catch (CardBuilderException cbe) {
            //if exception is thrown, let's ignore since media is optional for an article
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws CardBuilderException {
        boolean isValid = v.required(v.notNullOrEmpty(description), "Description cannot be blank.");
        if (isValid) {
            this.description = description;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws CardBuilderException {
        boolean isValid = v.required(v.notNullOrEmpty(title), "Title cannot be blank.");
        if (isValid) {
            this.title = title;
        }
    }

    public void setMedia(Image media) throws CardBuilderException {
        v.required(v.notNull(media), "Must specify a image.");

        this.media = media;
    }

    public Media getMedia() {
        return media;
    }

    /**
     * Get a list of validation errors.
     * @return the list of errors.
     */
    public List<String> getErrors(){
        return v.getErrors();
    }

}
