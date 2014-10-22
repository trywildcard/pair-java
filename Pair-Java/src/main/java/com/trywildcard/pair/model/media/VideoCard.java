package com.trywildcard.pair.model.media;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trywildcard.pair.Pair;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.model.Card;
import com.trywildcard.pair.model.CardType;
import com.trywildcard.pair.util.CardSerializer;
import com.trywildcard.pair.validation.ValidationTool;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by karthiksenthil on 10/5/14.
 */
public class VideoCard implements Card {

    private final String pairVersion = Pair.getInstance().getVersion();
    private final CardType cardType = CardType.VIDEO;

    private URL webUrl;
    private Video media;

    @JsonIgnore
    protected ValidationTool v = new ValidationTool();

    /**
     * Construct a product card
     */
    public VideoCard(Video media, String webUrl) throws CardBuilderException {
        media(media);
        webUrl(webUrl);
    }

    private void webUrl(String webUrl) throws CardBuilderException {
        boolean isValid = v.required(v.notNullOrEmpty(webUrl), "Must specify a article webUrl.");
        if (isValid) {
            try {
                this.webUrl = new URL(webUrl);
            } catch (MalformedURLException e) {
                v.required(v.fail(), "Could not parse URL from webUrl string.");
            }
        }
    }

    private void media(Video media) throws CardBuilderException {
        v.required(v.notNull(media), "Must specify a video.");

        this.media = media;
    }

    public String getPairVersion() {
        return pairVersion;
    }

    public CardType getCardType() {
        return cardType;
    }

    public URL getWebUrl() {
        return webUrl;
    }

    public Video getMedia() {
        return media;
    }

    /**
     * Private constructor to allow for Jackson deserialization.
     */
    private VideoCard(){}

    /**
     * Serialize fields in the Wildcard product card format.
     * @return the string representation of this card.
     * @throws java.io.IOException
     */
    public String writeAsJsonString() throws IOException {
        return new CardSerializer().writeCard(this);
    }
}
