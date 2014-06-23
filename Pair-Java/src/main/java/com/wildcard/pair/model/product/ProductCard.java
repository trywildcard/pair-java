package com.wildcard.pair.model.product;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wildcard.pair.model.Card;
import com.wildcard.pair.model.CardType;
import com.wildcard.pair.util.CardSerializer;

@JsonDeserialize(builder = ProductCardBuilder.class)
/**
 * Structures a Product Card. Must be constructed using <code>ProductCardBuilder</code>.
 */
public final class ProductCard implements Card {
    private final CardType cardType;
    private final String name;
    private final URL url;
    private final List<Offer> offers;
    private final String merchant;
    private final String brand;
    private final String description;
    private final List<ProductColor> colors;
    private final List<URL> images;
    private final Float rating;
    private final Float ratingScale;
    private final Integer ratingCount;
    private final List<URL> relatedItems;
    private final Map<String, String> sizes;
    private final List<String> options;
    private final String model;
    private final String appLinkIos;
    private final String appLinkAndroid;


    /**
     * Construct a product card using an <code>OfferBuilder</code>, which is responsible for validations.
     * @param builder the builder for this product card.
     */
    public ProductCard(ProductCardBuilder builder) {
        this.cardType = builder.cardType;
        this.name = builder.name;
        this.url = builder.url;
        this.offers = Collections.unmodifiableList(builder.offers);
        this.merchant = builder.merchant;
        this.brand = builder.brand;
        this.description = builder.description;
        this.colors = Collections.unmodifiableList(builder.colors);
        this.images = Collections.unmodifiableList(builder.images);
        this.rating = builder.rating;
        this.ratingScale = builder.ratingScale;
        this.ratingCount = builder.ratingCount;
        this.relatedItems = Collections.unmodifiableList(builder.relatedItems);
        this.sizes = Collections.unmodifiableMap(builder.sizes);
        this.options = Collections.unmodifiableList(builder.options);
        this.model = builder.model;
        this.appLinkIos = builder.appLinkIos;
        this.appLinkAndroid = builder.appLinkAndroid;
    }
    
    public String getAppLinkAndroid() {
        return appLinkAndroid;
    }

    public String getAppLinkIos() {
        return appLinkIos;
    }

    public String getModel() {
        return model;
    }

    public List<String> getOptions() {
        return options;
    }

    public List<URL> getRelatedItems() {
        return relatedItems;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public Float getRatingScale() {
        return ratingScale;
    }

    public Float getRating() {
        return rating;
    }

    public List<URL> getImages() {
        return images;
    }

    public String getDescription() {
        return description;
    }

    public String getMerchant() {
        return merchant;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public URL getUrl() {
        return url;
    }

    public CardType getCardType() {
        return cardType;
    }

    public List<ProductColor> getColors() {
        return colors;
    }

    public Map<String, String> getSizes() {
        return sizes;
    }

    /**
     * Serialize fields in the Wildcard product card format.
     * @return the string representation of this card.
     * @throws IOException
     */
    public String writeAsJsonString() throws IOException{
        return new CardSerializer().writeCard(this);
    }
}