package com.trywildcard.pair.model.article;

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

public class ArticleCard implements Card {

    private final String pairVersion = Pair.getInstance().getVersion();
    private final CardType cardType = CardType.ARTICLE;

    private URL webUrl;
    private Article article;

    @JsonIgnore
    protected ValidationTool v = new ValidationTool();

    /**
     * Construct an article card
     */
    public ArticleCard(Article article, String webUrl) throws CardBuilderException {
        article(article);
        webUrl(webUrl);
    }

    /*
    * Constructs an article card by attempting to extract relevant meta tags from input web url
    */
    public ArticleCard(String webUrl) throws CardBuilderException {
        webUrl(webUrl);
        MetaTagModel metaTagModel = MetaTagExtractor.getMetaTags(this.webUrl);
        article(new ArticleBuilder(metaTagModel).build());
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

    private void article(Article article) throws CardBuilderException {
        v.required(v.notNull(article), "Must specify an article.");

        this.article = article;
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

    public Article getArticle() {
        return article;
    }

    /**
     * Private constructor to allow for Jackson deserialization.
     */
    private ArticleCard(){}

    /**
     * Serialize fields in the Wildcard product card format.
     * @return the string representation of this card.
     * @throws java.io.IOException
     */
    public String writeAsJsonString() throws IOException {
        return new CardSerializer().writeCard(this);
    }
}
