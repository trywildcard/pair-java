package com.trywildcard.pair.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.model.AbstractCard;
import com.trywildcard.pair.model.CardType;
import com.trywildcard.pair.extraction.MetaTagExtractor;
import com.trywildcard.pair.extraction.MetaTagModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Structures a Product AbstractCard.
 */
public final class ProductCard extends AbstractCard {

    private List<Offer> offers = new ArrayList<Offer>();
    private Product product;

    /**
     * Construct a product card
     */
    public ProductCard(Product product, Offer offer, String webUrl) throws CardBuilderException {
        this.cardType = CardType.PRODUCT;
        product(product);
        offer(offer);
        webUrl(webUrl);
    }

    /**
     * Construct a product card
     */
    public ProductCard(Product product, List<Offer> offers, String webUrl) throws CardBuilderException {
        this.cardType = CardType.PRODUCT;
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

    public List<Offer> getOffers() {
        return offers;
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

    public Product getProduct() {
        return product;
    }

    /**
     * Private constructor to allow for Jackson deserialization.
     */
    private ProductCard(){}
}
