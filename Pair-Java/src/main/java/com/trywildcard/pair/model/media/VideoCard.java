package com.trywildcard.pair.model.media;

import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.model.AbstractCard;
import com.trywildcard.pair.model.CardType;

/**
 * Created by karthiksenthil on 10/5/14.
 */
public class VideoCard extends AbstractCard {
    private Video media;

    /**
     * Construct a product card
     */
    public VideoCard(Video media, String webUrl) throws CardBuilderException {
        this.cardType = CardType.VIDEO;
        media(media);
        webUrl(webUrl);
    }

    /*
    * Constructs a video card by attempting to extract relevant meta tags from input web url
    */
    public VideoCard(String webUrl) throws CardBuilderException {
        super(webUrl);
        this.cardType = CardType.VIDEO;
        media(new VideoBuilder(this.getMetaTagModel()).build());
    }

    private void media(Video media) throws CardBuilderException {
        v.required(v.notNull(media), "Must specify a video.");

        this.media = media;
    }

    public Video getMedia() {
        return media;
    }

    /**
     * Private constructor to allow for Jackson deserialization.
     */
    private VideoCard(){}
}
