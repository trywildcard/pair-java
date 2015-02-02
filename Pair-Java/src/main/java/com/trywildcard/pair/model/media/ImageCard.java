package com.trywildcard.pair.model.media;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.model.AbstractCard;
import com.trywildcard.pair.model.CardType;

import java.util.List;

public class ImageCard extends AbstractCard {
    private Image media;

    public ImageCard(Image media) throws CardBuilderException {
        this.cardType = CardType.IMAGE;
        media(media);
    }

    public ImageCard(Image media, String webUrl) throws CardBuilderException {
        this.cardType = CardType.IMAGE;
        media(media);
        webUrl(webUrl);
    }

    /*
    * Constructs a image card by attempting to extract relevant meta tags from input web url
    */
    public ImageCard(String webUrl) throws CardBuilderException {
        super(webUrl);
        this.cardType = CardType.IMAGE;
        media(new Image(this.getMetaTagModel()));
    }

    private void media(Image media) throws CardBuilderException {
        v.required(v.notNull(media), "Must specify an image.");

        this.media = media;
    }

    public Image getMedia() {
        return media;
    }

    /**
     * Private constructor to allow for Jackson deserialization.
     */
    private ImageCard(){}

    /**
     * Serialize fields in the Wildcard product card format.
     * @return the string representation of this card.
     * @throws java.io.IOException
     */

    @JsonIgnore
    public List<String> getErrors(){
        return v.getErrors();
    }
}
