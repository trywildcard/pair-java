package com.trywildcard.pair.model.review;

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

public class ReviewCard implements Card {

    private final String pairVersion = Pair.getInstance().getVersion();
    private final CardType cardType = CardType.REVIEW;

    private URL webUrl;
    private Review review;

    @JsonIgnore
    protected ValidationTool v = new ValidationTool();

    /**
     * Construct a product card
     */
    public ReviewCard(Review review, String webUrl) throws CardBuilderException {
        review(review);
        webUrl(webUrl);
    }

    /*
    * Constructs an review card by attempting to extract relevant meta tags from input web url
    */
    public ReviewCard(String webUrl) throws CardBuilderException {
        webUrl(webUrl);
        MetaTagModel metaTagModel = MetaTagExtractor.getMetaTags(this.webUrl);
        review(new ReviewBuilder(metaTagModel).build());
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

    private void review(Review review) throws CardBuilderException {
        v.required(v.notNull(review), "Must specify a review.");

        this.review = review;
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

    public Review getReview() {
        return review;
    }

    /**
     * Private constructor to allow for Jackson deserialization.
     */
    private ReviewCard(){}

    /**
     * Serialize fields in the Wildcard product card format.
     * @return the string representation of this card.
     * @throws java.io.IOException
     */
    public String writeAsJsonString() throws IOException {
        return new CardSerializer().writeCard(this);
    }
}
