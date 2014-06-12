package com.wildcard.model;

import java.util.List;
import java.util.Map;

public class ProductCard implements Card {
    private final CardType cardType;
    private final String name;
    private final String url;
    private final List<Offer> offers;
    private final String merchant;
    private final String brand;
    private final String description;
    private final List<ProductColor> colors;
    private final List<String> images;
    private final Float rating;
    private final Float ratingScale;
    private final Integer ratingCount;
    private final List<String> relatedItems;
    private final Map<String, String> sizes;
    private final List<String> options;
    private final String model;
    private final String appLinkIos;
    private final String appLinkAndroid;

    public ProductCard(ProductCardBuilder builder) {
        this.cardType = CardType.PRODUCT;
        this.name = builder.name;
        this.url = builder.url;
        this.offers = builder.offers;
        this.merchant = builder.merchant;
        this.brand = builder.brand;
        this.description = builder.description;
        this.colors = builder.colors;
        this.images = builder.images;
        this.rating = builder.rating;
        this.ratingScale = builder.ratingScale;
        this.ratingCount = builder.ratingCount;
        this.relatedItems = builder.relatedItems;
        this.sizes = builder.sizes;
        this.options = builder.options;
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

    public List<String> getRelatedItems() {
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

    public List<String> getImages() {
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

    public String getUrl() {
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
    
    public String writeAsJson() {
        return null;
    }

}
