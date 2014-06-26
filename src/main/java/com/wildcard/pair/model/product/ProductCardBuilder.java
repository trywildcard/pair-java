package com.wildcard.pair.model.product;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.wildcard.pair.model.Builder;
import com.wildcard.pair.model.CardType;
import com.wildcard.pair.util.ValidationTool;

@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
public class ProductCardBuilder implements Builder<ProductCard> {
    
    // required fields
    CardType cardType;
    String name;
    List<Offer> offers = new ArrayList<Offer>();
    URL webUrl;

    // optional fields
    String productId;
    String merchant;
    String brand;
    String description;
    List<ProductColor> colors = new ArrayList<ProductColor>();
    List<URL> images = new ArrayList<URL>();
    Float rating;
    Float ratingScale;
    Integer ratingCount;
    List<URL> relatedItems = new ArrayList<URL>();
    List<URL> referencedItems = new ArrayList<URL>();
    Map<String, String> sizes = new HashMap<String,String>();
    List<String> options = new ArrayList<String>();
    String model;
    String appLinkIos;
    String appLinkAndroid;

    /**
     * Construct a builder by providing a list of pre-made offers.
     *
     * @param name product name
     * @param offers list of offers
     * @param webUrl url to access this product in a web browser
     */
    public ProductCardBuilder(String name, List<Offer> offers, URL webUrl) {
        cardType(CardType.PRODUCT);
        name(name);
        offers(offers);
        webUrl(webUrl);
    }

    /**
     * Construct a builder providing a price value instead of a list of offers.
     * @param name
     * @param price
     * @param webUrl
     */
    public ProductCardBuilder(String name, Float price, URL webUrl){
        Offer offer = new OfferBuilder(price).build();
        offer(offer);
        cardType(CardType.PRODUCT);
        name(name);
        webUrl(webUrl);
    }
    
    public ProductCardBuilder appLinkAndroid(String appLinkAndroid) {
        ValidationTool.notEmpty(appLinkAndroid, "Tried to set appLinkAndroid to an empty string.");
        this.appLinkAndroid = appLinkAndroid;
        return this;
    }

    public ProductCardBuilder appLinkIos(String appLinkIos) {
        ValidationTool.notEmpty(appLinkIos, "Tried to set appLinkIos to an empty string.");
        this.appLinkIos = appLinkIos;
        return this;
    }

    public ProductCardBuilder model(String model) {
        ValidationTool.notEmpty(model, "Tried to set model to an empty string.");
        this.model = model;
        return this;
    }
    
    public ProductCardBuilder sizes(Map<String, String> sizes){
        ValidationTool.notNull(sizes, "Sizes must not be null.");
        for (String key : sizes.keySet()){
            String value = sizes.get(key);
            ValidationTool.notNullOrEmpty(value, "Tried to set a null or empty size value.");
            this.sizes.put(key, value);
        }
        return this;
    }
    
    public ProductCardBuilder option(String option){
        ValidationTool.notNullOrEmpty(option, "Tried to add an empty option.");
        this.options.add(option);
        return this;
    }

    public ProductCardBuilder options(List<String> options) {
        ValidationTool.notNull(options, "Options must not be null.");
        for (String option : options){
            ValidationTool.notNullOrEmpty(option, "Tried to add an empty option.");
            this.options.add(option);
        }
        return this;
    }

    public ProductCardBuilder relatedItem(URL relatedItem){
        ValidationTool.notNull(relatedItem, "Tried to add an empty relatedItem.");
        this.relatedItems.add(relatedItem);
        return this;
    }

    public ProductCardBuilder relatedItems(List<URL> relatedItems) {
        ValidationTool.notNull(relatedItems, "relatedItems must not be null.");
        for (URL relatedItem : relatedItems){
            ValidationTool.notNull(relatedItem, "Tried to add an empty relatedItem.");
            this.relatedItems.add(relatedItem);
        }
        return this;
    }

    public ProductCardBuilder referencedItem(URL referencedItem){
        ValidationTool.notNull(referencedItem, "Tried to add an empty relatedItem.");
        this.relatedItems.add(referencedItem);
        return this;
    }

    public ProductCardBuilder referencedItems(List<URL> referencedItems) {
        ValidationTool.notNull(referencedItems, "relatedItems must not be null.");
        for (URL referencedItem : referencedItems){
            ValidationTool.notNull(referencedItem, "Tried to add an empty relatedItem.");
            this.referencedItems.add(referencedItem);
        }
        return this;
    }

    public ProductCardBuilder ratingCount(Integer ratingCount) {
        ValidationTool.notNegative(ratingCount, "ratingCount must be a positive integer.");
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
    
    public ProductCardBuilder image(URL imgUrl){
        ValidationTool.notNull(imgUrl, "Tried to add a null imgUrl");
        images.add(imgUrl);
        return this;
    }

    public ProductCardBuilder images(List<URL> images) {
        ValidationTool.notNull(images, "images must not be null.");
        for (URL img : images){
            ValidationTool.notNull(img, "Tried to add a null image");
            this.images.add(img);
        }
        return this;
    }
    
    public ProductCardBuilder color(ProductColor color){
        ValidationTool.notNull(color, "Tried to add a null color");
        colors.add(color);
        return this;
    }

    public ProductCardBuilder colors(List<ProductColor> colors){
        ValidationTool.notNull(colors, "colors must not be null.");
        for (ProductColor color : colors){
            ValidationTool.notNull(color, "Tried to add a null color");
            this.colors.add(color);
        }
        return this;
    }

    public ProductCardBuilder description(String description) {
        ValidationTool.notEmpty(description, "Tried to set description to an empty string.");
        this.description = description;
        return this;
    }

    public ProductCardBuilder brand(String brand) {
        ValidationTool.notEmpty(brand, "Tried to set brand to an empty string.");
        this.brand = brand;
        return this;
    }

    public ProductCardBuilder merchant(String merchant) {
        ValidationTool.notEmpty(merchant, "Tried to set merchant to an empty string.");
        this.merchant = merchant;
        return this;
    }

    public ProductCardBuilder productId(String productId){
        ValidationTool.notEmpty(productId, "Tried to set productId to an empty string");
        this.productId = productId;
        return this;
    }

    /**
     * Instantiate a <code>ProductCard</code> with the data in this builder.
     * @return the constructed product card
     */
    public ProductCard build() {        
        ProductCard card = new ProductCard(this);
        return card;
    }


    /**
     * Private constructor to allow for Jackson deserialization.
     */
    private ProductCardBuilder(){}
    
    private ProductCardBuilder cardType(CardType cardType){
        this.cardType = cardType;
        return this;
    }
    
    private ProductCardBuilder name(String name) {
        ValidationTool.notNullOrEmpty(name, "Product name cannot be blank.");
        this.name = name;
        return this;
    }
    
    private ProductCardBuilder webUrl(URL webUrl){
        ValidationTool.notNull(webUrl, "Must specify a product webUrl.");
        this.webUrl = webUrl;
        return this;
    }
    
    private ProductCardBuilder offers(List<Offer> offers){
        ValidationTool.notNullOrEmpty(offers, "Must specify at least one offer.");
        
        for (Offer offer : offers){
            this.offers.add(offer);
        }
        
        return this;
    }
    
    private ProductCardBuilder offer(Offer offer){
        offers.add(offer);
        return this;
    }

}
