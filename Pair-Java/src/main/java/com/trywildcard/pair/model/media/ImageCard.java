package com.trywildcard.pair.model.media;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trywildcard.pair.Pair;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.extraction.MetaTagExtractor;
import com.trywildcard.pair.extraction.MetaTagModel;
import com.trywildcard.pair.model.Card;
import com.trywildcard.pair.model.CardType;
import com.trywildcard.pair.util.CardSerializer;
import com.trywildcard.pair.validation.ValidationTool;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ImageCard implements Card {

    private final String pairVersion = Pair.getInstance().getVersion();
    private final CardType cardType = CardType.IMAGE;

    private URL webUrl;
    private Image media;
    private List<String> keywords;

    @JsonIgnore
    protected ValidationTool v = new ValidationTool();

    public ImageCard(Image media) throws CardBuilderException {
        media(media);
    }

    public ImageCard(Image media, String webUrl) throws CardBuilderException {
        media(media);
        webUrl(webUrl);
    }

    /*
    * Constructs a image card by attempting to extract relevant meta tags from input web url
    */
    public ImageCard(String webUrl) throws CardBuilderException {
        webUrl(webUrl);
        MetaTagModel metaTagModel = MetaTagExtractor.getMetaTags(this.webUrl);
        media(new Image(metaTagModel));
    }

    private void webUrl(String webUrl) throws CardBuilderException {
        boolean isValid = v.optional(v.notNullOrEmpty(webUrl), "Must specify a article webUrl.");
        if (isValid) {
            try {
                this.webUrl = new URL(webUrl);
            } catch (MalformedURLException e) {
                v.optional(v.fail(), "Could not parse URL from webUrl string.");
            }
        }
    }

    private void media(Image media) throws CardBuilderException {
        v.required(v.notNull(media), "Must specify a video.");

        this.media = media;
    }

    public void setKeywords(List<String> keywords) throws CardBuilderException {
        v.required(v.notNull(keywords), "Keywords cannot be null.");

        this.keywords = keywords;
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

    public Image getMedia() {
        return media;
    }

    public List<String> getKeywords() { return keywords; }

    /**
     * Private constructor to allow for Jackson deserialization.
     */
    private ImageCard(){}

    /**
     * Serialize fields in the Wildcard product card format.
     * @return the string representation of this card.
     * @throws java.io.IOException
     */
    public String writeAsJsonString() throws IOException {
        return new CardSerializer().writeCard(this);
    }
}
