package com.wildcard.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductCardBuilder {
    
    // required fields
    String name;
    List<Offer> offers;
    String url;

    // optional filds
    String merchant;
    String brand;
    String description;
    List<ProductColor> colors = new ArrayList<ProductColor>();
    List<String> images = new ArrayList<String>();
    Float rating;
    Float ratingScale;
    Integer ratingCount;
    List<String> relatedItems = new ArrayList<String>();
    Map<String, String> sizes = new HashMap<String,String>();
    List<String> options = new ArrayList<String>();
    String model;
    String appLinkIos;
    String appLinkAndroid;

    public ProductCardBuilder(String productName, List<Offer> offers, String url) {

        // very minimal validation
        // TODO: null check, do it first
        if (offers == null || offers.isEmpty()) {
            throw new IllegalStateException("Must specify at least one offer");
        }
        
        this.name = productName;
        this.offers = offers;
        this.url = url;
    }
    
    public ProductCardBuilder appLinkAndroid(String appLinkAndroid) {
        this.appLinkAndroid = appLinkAndroid;
        return this;
    }

    public ProductCardBuilder appLinkIos(String appLinkIos) {
        this.appLinkIos = appLinkIos;
        return this;
    }

    public ProductCardBuilder model(String model) {
        this.model = model;
        return this;
    }
    
    public ProductCardBuilder sizes(Map<String, String> sizes){
        for (String key : sizes.keySet()){
            this.sizes.put(key, sizes.get(key));
        }
        return this;
    }
    
    public ProductCardBuilder option(String option){
        this.options.add(option);
        return this;
    }

    public ProductCardBuilder options(List<String> options) {
        for (String option : options){
            this.options.add(option);
        }
        return this;
    }

    public ProductCardBuilder relatedItem(String relatedItem){
        this.relatedItems.add(relatedItem);
        return this;
    }
    
    public ProductCardBuilder relatedItems(List<String> relatedItems) {
        for (String relatedItem : relatedItems){
            this.relatedItems.add(relatedItem);
        }
        return this;
    }

    public ProductCardBuilder ratingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
        return this;
    }

    public ProductCardBuilder ratingScale(Float ratingScale) {
        this.ratingScale = ratingScale;
        return this;
    }

    public ProductCardBuilder rating(Float rating) {
        this.rating = rating;
        return this;
    }
    
    public ProductCardBuilder image(String imgUrl){
        images.add(imgUrl);
        return this;
    }

    public ProductCardBuilder images(List<String> images) {
        for (String img : images){
            this.images.add(img);
        }
        return this;
    }
    
    public ProductCardBuilder color(ProductColor color){
        colors.add(color);
        return this;
    }

    public ProductCardBuilder colors(List<ProductColor> colors){
        for (ProductColor c : colors){
            this.colors.add(c);
        }
        return this;
    }

    public ProductCardBuilder description(String description) {
        this.description = description;
        return this;
    }

    public ProductCardBuilder brand(String brand) {
        this.brand = brand;
        return this;
    }

    public ProductCardBuilder merchant(String merchant) {
        this.merchant = merchant;
        return this;
    }

    public ProductCard build() {
        ProductCard card = new ProductCard(this);

        return card;
    }
}
