package com.wildcard.model;

import java.util.ArrayList;
import java.util.List;

public class ProductCardBuilder {

    // required fields
    protected String name;
    protected List<Offer> offers;

    // optional filds
    protected String url;
    protected float price;
    protected String merchant;
    protected String brand;
    protected String description;
    protected Gender gender;
    protected List<Color> colors;
    protected List<String> images;
    protected List<String> videos;
    protected float rating;
    protected float ratingScale;
    protected int ratingCount;
    protected List<String> relatedItems;
    protected List<String> options;
    protected float weight;
    protected String pattern;
    protected String condition;
    protected String model;
    protected String material;
    protected float shippingCost;
    protected String appLinkIos;
    protected String appLinkAndroid;

    public ProductCardBuilder() {
        this.offers = new ArrayList<Offer>();
    }
    
    public ProductCardBuilder name(String name){
        this.name = name;
        return this;
    }

    public ProductCardBuilder appLinkAndroid(String appLinkAndroid) {
        this.appLinkAndroid = appLinkAndroid;
        return this;
    }

    public ProductCardBuilder appLinkIos(String appLinkIos) {
        this.appLinkIos = appLinkIos;
        return this;
    }

    public ProductCardBuilder shippingCost(float shippingCost) {
        this.shippingCost = shippingCost;
        return this;
    }

    public ProductCardBuilder material(String material) {
        this.material = material;
        return this;
    }

    public ProductCardBuilder model(String model) {
        this.model = model;
        return this;
    }

    public ProductCardBuilder condition(String condition) {
        this.condition = condition;
        return this;
    }

    public ProductCardBuilder pattern(String pattern) {
        this.pattern = pattern;
        return this;
    }

    public ProductCardBuilder weight(float weight) {
        this.weight = weight;
        return this;
    }

    public ProductCardBuilder options(List<String> options) {
        this.options = options;
        return this;
    }

    public ProductCardBuilder relatedItems(List<String> relatedItems) {
        this.relatedItems = relatedItems;
        return this;
    }

    public ProductCardBuilder ratingCount(int ratingCount) {
        this.ratingCount = ratingCount;
        return this;
    }

    public ProductCardBuilder ratingScale(float ratingScale) {
        this.ratingScale = ratingScale;
        return this;
    }

    public ProductCardBuilder rating(float rating) {
        this.rating = rating;
        return this;
    }

    public ProductCardBuilder videos(List<String> videos) {
        this.videos = videos;
        return this;
    }
    
    public ProductCardBuilder image(String imgUrl){
        if (images == null){
            this.images = new ArrayList<String>();
        }
        images.add(imgUrl);
        return this;
    }

    public ProductCardBuilder images(List<String> images) {
        if (images == null){
            this.images = images;
        } else {
            for (String img : images){
                this.images.add(img);
            }
        }
        return this;
    }
    
    public ProductCardBuilder color(Color color){
        if (colors == null){
            this.colors = new ArrayList<Color>();
        }
        colors.add(color);
        return this;
    }

    public ProductCardBuilder colors(List<Color> colors){
        if (colors == null){
            this.colors = colors;
        } else {
            for (Color c : colors){
                this.colors.add(c);
            }
        }
        return this;
    }
    
    public ProductCardBuilder gender(Gender gender) {
        this.gender = gender;
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

    public ProductCardBuilder url(String url) {
        this.url = url;
        return this;
    }

    public ProductCardBuilder merchant(String merchant) {
        this.merchant = merchant;
        return this;
    }

    public ProductCardBuilder price(float price) {
        OfferBuilder offerBuilder = new OfferBuilder();
        offerBuilder.price(price);
        
        this.offers.add(offerBuilder.build());
        return this;
    }

    public ProductCard build() {
        ProductCard card = new ProductCard(this);
        
        if (card.getOffers().size() == 0) {
            throw new IllegalStateException("Must specify at least one offer");
        }

        return card;
    }
}
