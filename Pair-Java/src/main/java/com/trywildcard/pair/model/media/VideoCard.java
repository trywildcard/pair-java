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
    private Video video;

    @JsonIgnore
    protected ValidationTool v = new ValidationTool();

    /**
     * Construct a product card
     */
    public VideoCard(Video video, String webUrl) throws CardBuilderException {
        video(video);
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

    private void video(Video video) throws CardBuilderException {
        v.required(v.notNull(video), "Must specify a video.");

        this.video = video;
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

    public Video getVideo() {
        return video;
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
