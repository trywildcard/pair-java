package com.wildcard.model;

import java.util.List;

public class ProductCard implements Card {
    private final CardType cardType;
    private final String name;
    private final String url;
    private final List<Offer> offers;
    private final String merchant;
    private final String brand;
    private final String description;
    private final Gender gender;
    private final List<Color> colors;
    private final List<String> images;
    private final List<String> videos;
    private final float rating;
    private final float ratingScale;
    private final int ratingCount;
    private final List<String> relatedItems;
    // TODO: product.sizes
    private final List<String> options;
    private final float weight;
    private final String pattern;
    private final String condition;
    private final String model;
    private final String material;
    private final float shippingCost;
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
        this.gender = builder.gender;
        this.images = builder.images;
        this.videos = builder.videos;
        this.rating = builder.rating;
        this.ratingScale = builder.ratingScale;
        this.ratingCount = builder.ratingCount;
        this.relatedItems = builder.relatedItems;
        this.options = builder.options;
        this.weight = builder.weight;
        this.pattern = builder.pattern;
        this.condition = builder.condition;
        this.model = builder.model;
        this.material = builder.material;
        this.shippingCost = builder.shippingCost;
        this.appLinkIos = builder.appLinkIos;
        this.appLinkAndroid = builder.appLinkAndroid;
    }

    public String getAppLinkAndroid() {
        return appLinkAndroid;
    }

    public String getAppLinkIos() {
        return appLinkIos;
    }

    public float getShippingCost() {
        return shippingCost;
    }

    public String getMaterial() {
        return material;
    }

    public String getModel() {
        return model;
    }

    public String getCondition() {
        return condition;
    }

    public String getPattern() {
        return pattern;
    }

    public float getWeight() {
        return weight;
    }

    public List<String> getOptions() {
        return options;
    }

    public List<String> getRelatedItems() {
        return relatedItems;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public float getRatingScale() {
        return ratingScale;
    }

    public float getRating() {
        return rating;
    }

    public List<String> getVideos() {
        return videos;
    }

    public List<String> getImages() {
        return images;
    }

    public Gender getGender() {
        return gender;
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

    public String writeAsJson() {
        return null;
    }

}
