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
    private String name;
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
    private final List<String> keywords;

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
        this.keywords = builder.keywords;
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

    public List<String> getKeywords() { return keywords; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (appLinkAndroid != null ? !appLinkAndroid.equals(product.appLinkAndroid) : product.appLinkAndroid != null)
            return false;
        if (appLinkIos != null ? !appLinkIos.equals(product.appLinkIos) : product.appLinkIos != null) return false;
        if (brand != null ? !brand.equals(product.brand) : product.brand != null) return false;
        if (colors != null ? !colors.equals(product.colors) : product.colors != null) return false;
        if (description != null ? !description.equals(product.description) : product.description != null) return false;
        if (gender != product.gender) return false;
        if (images != null ? !images.equals(product.images) : product.images != null) return false;
        if (merchant != null ? !merchant.equals(product.merchant) : product.merchant != null) return false;
        if (model != null ? !model.equals(product.model) : product.model != null) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (options != null ? !options.equals(product.options) : product.options != null) return false;
        if (rating != null ? !rating.equals(product.rating) : product.rating != null) return false;
        if (ratingCount != null ? !ratingCount.equals(product.ratingCount) : product.ratingCount != null) return false;
        if (ratingScale != null ? !ratingScale.equals(product.ratingScale) : product.ratingScale != null) return false;
        if (referencedItems != null ? !referencedItems.equals(product.referencedItems) : product.referencedItems != null)
            return false;
        if (relatedItems != null ? !relatedItems.equals(product.relatedItems) : product.relatedItems != null)
            return false;
        if (sizes != null ? !sizes.equals(product.sizes) : product.sizes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (merchant != null ? merchant.hashCode() : 0);
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (colors != null ? colors.hashCode() : 0);
        result = 31 * result + (images != null ? images.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (ratingScale != null ? ratingScale.hashCode() : 0);
        result = 31 * result + (ratingCount != null ? ratingCount.hashCode() : 0);
        result = 31 * result + (relatedItems != null ? relatedItems.hashCode() : 0);
        result = 31 * result + (referencedItems != null ? referencedItems.hashCode() : 0);
        result = 31 * result + (sizes != null ? sizes.hashCode() : 0);
        result = 31 * result + (options != null ? options.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (appLinkIos != null ? appLinkIos.hashCode() : 0);
        result = 31 * result + (appLinkAndroid != null ? appLinkAndroid.hashCode() : 0);
        return result;
    }
}
