package com.trywildcard.pair.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trywildcard.pair.Pair;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.model.Card;
import com.trywildcard.pair.model.CardType;
import com.trywildcard.pair.util.CardSerializer;
import com.trywildcard.pair.extraction.MetaTagExtractor;
import com.trywildcard.pair.extraction.MetaTagModel;
import com.trywildcard.pair.validation.ValidationTool;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Structures a Product Card.
 */
public final class ProductCard implements Card {
    private final String pairVersion = Pair.getInstance().getVersion();
    private final CardType cardType = CardType.PRODUCT;

    private URL webUrl;
    private List<Offer> offers = new ArrayList<Offer>();
    private Product product;
    private List<String> keywords;

    @JsonIgnore
    protected ValidationTool v = new ValidationTool();

    /**
     * Construct a product card
     */
    public ProductCard(Product product, Offer offer, String webUrl) throws CardBuilderException {
        product(product);
        offer(offer);
        webUrl(webUrl);
    }

    /**
     * Construct a product card
     */
    public ProductCard(Product product, List<Offer> offers, String webUrl) throws CardBuilderException {
        product(product);
        offers(offers);
        webUrl(webUrl);
    }

    /**
     * Construct a product card
     */
    public ProductCard(Product product, Float price, String webUrl) throws CardBuilderException {
        product(product);
        Offer offer = new OfferBuilder(price).build();
        offer(offer);
        webUrl(webUrl);
    }

    /**
     * Construct a product card with just web URL - attempts to do tag extraction
     * @return
     */
    public ProductCard(String webUrl) throws CardBuilderException {
        webUrl(webUrl);

        MetaTagModel metaTagModel = MetaTagExtractor.getMetaTags(this.webUrl);
        product(new ProductBuilder(metaTagModel).build());
        offer(new OfferBuilder(metaTagModel).build());
    }

    /**
     * Construct a product card with just web URL and price - attempts to do tag extraction
     * @return
     */
    public ProductCard(String webUrl, Float price) throws CardBuilderException {
        webUrl(webUrl);

        MetaTagModel metaTagModel = MetaTagExtractor.getMetaTags(this.webUrl);
        product(new ProductBuilder(metaTagModel).build());

        Offer offer = new OfferBuilder(price).build();
        offer(offer);
    }

    public String getPairVersion(){
        return pairVersion;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    private void webUrl(String webUrl) throws CardBuilderException {
        boolean isValid = v.required(v.notNullOrEmpty(webUrl), "Must specify a product webUrl.");
        if (isValid) {
            try {
                this.webUrl = new URL(webUrl);
            } catch (MalformedURLException e) {
                v.required(v.fail(), "Could not parse URL from webUrl string.");
            }
        }
    }

    private void offers(List<Offer> offers) throws CardBuilderException {
        boolean isValid = v.required(v.notNullOrEmpty(offers), "Must specify at least one offer.");

        boolean foundValidOffer = false;

        if (isValid) {
            for (Offer offer : offers){
                boolean isValidOffer = v.optional(v.notNull(offer), "Tried to set null offer.");
                if (isValidOffer){
                    foundValidOffer = true;
                    this.offers.add(offer);
                }
            }
        }

        v.required(foundValidOffer, "Must specify at least one offer.");
    }

    private void offer(Offer offer) throws CardBuilderException {
        boolean isValidOffer = v.required(v.notNull(offer), "Tried to set null offer.");
        offers.add(offer);
    }

    private void product(Product product) throws CardBuilderException {
        v.required(v.notNull(product), "Must specify a product.");

        this.product = product;
    }

    public void setKeywords(List<String> keywords) throws CardBuilderException {
        v.required(v.notNull(keywords), "Keywords cannot be null.");

        this.keywords = keywords;
    }

    public URL getWebUrl() {
        return webUrl;
    }

    public CardType getCardType() {
        return cardType;
    }

    public Product getProduct() {
        return product;
    }

    public List<String> getKeywords() { return keywords; }

    /**
     * Private constructor to allow for Jackson deserialization.
     */
    private ProductCard(){}

    /**
     * Serialize fields in the Wildcard product card format.
     * @return the string representation of this card.
     * @throws IOException
     */
    public String writeAsJsonString() throws IOException{
        return new CardSerializer().writeCard(this);
    }

}
