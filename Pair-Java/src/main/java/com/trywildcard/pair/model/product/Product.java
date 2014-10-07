package com.trywildcard.pair.model.product;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@JsonDeserialize(builder = ProductBuilder.class)

/* Structures a product object.  Must be constructed using a ProductBuilder */
public class Product {

    //required fields
    protected String name;
    private final List<URL> images;

    //optional fields
    private final String merchant;
    private final String brand;
    private final String description;
    private final List<ProductColor> colors;
    private final Float rating;
    private final Float ratingScale;
    private final Integer ratingCount;
    private final List<URL> relatedItems;
    private final List<URL> referencedItems;
    private final Map<String, String> sizes;
    private final List<String> options;
    private final Gender gender;
    private final String model;
    private final String appLinkIos;
    private final String appLinkAndroid;

    /**
     * Construct a product card using a <code>ProductBuilder</code>, which is responsible for validations.
     * @param builder the builder for this product card.
     */
    public Product(ProductBuilder builder) {
        this.name = builder.name;
        this.merchant = builder.merchant;
        this.brand = builder.brand;
        this.description = builder.description;
        this.colors = Collections.unmodifiableList(builder.colors);
        this.images = Collections.unmodifiableList(builder.images);
        this.rating = builder.rating;
        this.ratingScale = builder.ratingScale;
        this.ratingCount = builder.ratingCount;
        this.relatedItems = Collections.unmodifiableList(builder.relatedItems);
        this.referencedItems = Collections.unmodifiableList(builder.referencedItems);
        this.sizes = Collections.unmodifiableMap(builder.sizes);
        this.options = Collections.unmodifiableList(builder.options);
        this.gender = builder.gender;
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

    public Gender getGender() {
        return gender;
    }

    public Map<String, String> getSizes() {
        return sizes;
    }

    public List<URL> getReferencedItems() {
        return referencedItems;
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

    public List<ProductColor> getColors() {
        return colors;
    }

    public String getDescription() {
        return description;
    }

    public String getBrand() {
        return brand;
    }

    public String getMerchant() {
        return merchant;
    }

    public String getName() {
        return name;
    }
}
