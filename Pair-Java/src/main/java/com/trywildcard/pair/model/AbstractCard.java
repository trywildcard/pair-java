package com.trywildcard.pair.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trywildcard.pair.Pair;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.extraction.MetaTagExtractor;
import com.trywildcard.pair.extraction.MetaTagModel;
import com.trywildcard.pair.model.creator.Creator;
import com.trywildcard.pair.util.CardSerializer;
import com.trywildcard.pair.validation.ValidationTool;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Generic class for cards.
 */

public abstract class AbstractCard implements Card {
    protected final String pairVersion = Pair.getInstance().getVersion();
    protected CardType cardType;
    protected URL webUrl;
    protected List<String> keywords;
    protected String appLinkIos;
    protected String appLinkAndroid;

    private Creator creator;

    @JsonIgnore
    protected MetaTagModel metaTagModel;


    @JsonIgnore
    protected ValidationTool v = new ValidationTool();

    public AbstractCard(String webUrl) throws CardBuilderException {
        webUrl(webUrl);
        MetaTagModel metaTagModel = MetaTagExtractor.getMetaTags(this.webUrl);
        setAppLinkIos(metaTagModel.getAppLinkIos());
        setAppLinkAndroid(metaTagModel.getAppLinkAndroid());
        setMetaTagModel(metaTagModel);
    }

    protected AbstractCard() {
    }

    public void setKeywords(List<String> keywords) throws CardBuilderException {
        boolean isValid =  v.optional(v.notNull(keywords), "Keywords cannot be null.");
        if (isValid) {
            this.keywords = keywords;
        }
    }

    protected void webUrl(String webUrl) throws CardBuilderException {
        boolean isValid = v.required(v.notNullOrEmpty(webUrl), "Must specify a card webUrl.");
        if (isValid) {
            try {
                this.webUrl = new URL(webUrl);
            } catch (MalformedURLException e) {
                v.required(v.fail(), "Could not parse URL from webUrl string.");
            }
        }
    }

    public void setAppLinkIos(String appLinkIos) {
        boolean isValid = v.optional(v.notNullOrEmpty(appLinkIos), "App Link Ios cannot be blank.");
        if (isValid) {
            this.appLinkIos = appLinkIos;
        }
    }

    public void setAppLinkAndroid(String appLinkAndroid) {
        boolean isValid = v.optional(v.notNullOrEmpty(appLinkAndroid), "App Link Android cannot be blank.");
        if (isValid) {
            this.appLinkAndroid = appLinkAndroid;
        }
    }

    public void setCreator(Creator creator) throws CardBuilderException {
        boolean isValid = v.optional(v.notNull(creator), "creator cannot be null");

        if (isValid) {
            this.creator = creator;
        }
    }

    private void setMetaTagModel(MetaTagModel metaTagModel) {
        this.metaTagModel = metaTagModel;
    }

    @JsonIgnore
    protected MetaTagModel getMetaTagModel() {
        return metaTagModel;
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

    public List<String> getKeywords() { return keywords; }

    public String getAppLinkIos() {
        return appLinkIos;
    }

    public String getAppLinkAndroid() {
        return appLinkAndroid;
    }

    public Creator getCreator() { return creator; }

    /**
     * Serialize fields in the Wildcard card format.
     * @return the string representation of this card.
     * @throws java.io.IOException
     */
    public String writeAsJsonString() throws IOException {
        return new CardSerializer().writeCard(this);
    }
}
