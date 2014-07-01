package com.wildcard.pair.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.wildcard.pair.model.Builder;
import com.wildcard.pair.model.CardType;
import com.wildcard.pair.util.ValidationTool;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
public class ProductCardBuilder implements Builder<ProductCard> {

    private ValidationTool v = new ValidationTool();

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
        boolean isValid = v.notEmpty(appLinkAndroid, v.OPTIONAL, "Tried to set appLinkAndroid to an empty string.");

        if (isValid) {
            this.appLinkAndroid = appLinkAndroid;
        }

        return this;
    }

    public ProductCardBuilder appLinkIos(String appLinkIos) {
        boolean isValid = v.notEmpty(appLinkIos, v.OPTIONAL, "Tried to set appLinkIos to an empty string.");

        if (isValid){
            this.appLinkIos = appLinkIos;
        }
        return this;
    }

    public ProductCardBuilder model(String model) {
        boolean isValid = v.notEmpty(model, v.OPTIONAL, "Tried to set model to an empty string.");

        if (isValid){
            this.model = model;
        }
        return this;
    }
    
    public ProductCardBuilder sizes(Map<String, String> sizes){
        boolean isValid = v.notNull(sizes, v.OPTIONAL, "Sizes must not be null.");

        if (isValid){
            for (String key : sizes.keySet()){
                String value = sizes.get(key);

                boolean isValidSize = v.notNullOrEmpty(value, v.OPTIONAL, "Tried to set a null or empty size value.");

                if (isValidSize){
                    this.sizes.put(key, value);
                };
            }

        }
        return this;
    }
    
    public ProductCardBuilder option(String option){
        boolean isValid = v.notNullOrEmpty(option, v.OPTIONAL, "Tried to add an empty option.");
        if (isValid) {
            this.options.add(option);
        }
        return this;
    }

    public ProductCardBuilder options(List<String> options) {
        boolean isValid = v.notNull(options, v.OPTIONAL, "Options must not be null.");
        if (isValid) {
            for (String option : options){
                boolean isValidOption = v.notNullOrEmpty(option, v.OPTIONAL, "Tried to add an empty option.");
                if (isValidOption) {
                    this.options.add(option);
                }
            }
        }
        return this;
    }

    public ProductCardBuilder relatedItem(URL relatedItem){
        boolean isValid = v.notNull(relatedItem, v.OPTIONAL, "Tried to add an empty relatedItem.");
        if (isValid) {
            this.relatedItems.add(relatedItem);
        }
        return this;
    }

    public ProductCardBuilder relatedItems(List<URL> relatedItems) {
        boolean isValid = v.notNull(relatedItems, v.OPTIONAL, "relatedItems must not be null.");
        if (isValid) {
            for (URL relatedItem : relatedItems){
                boolean isValidRelatedItem = v.notNull(relatedItem, v.OPTIONAL, "Tried to add an empty relatedItem.");
                if (isValidRelatedItem) {
                    this.relatedItems.add(relatedItem);
                }
            }
        }
        return this;
    }

    public ProductCardBuilder referencedItem(URL referencedItem){
        boolean isValid = v.notNull(referencedItem, v.OPTIONAL, "Tried to add an empty relatedItem.");
        if (isValid) {
            this.relatedItems.add(referencedItem);
        }
        return this;
    }

    public ProductCardBuilder referencedItems(List<URL> referencedItems) {
        boolean isValid = v.notNull(referencedItems, v.OPTIONAL, "relatedItems must not be null.");
        if (isValid) {
            for (URL referencedItem : referencedItems){
                boolean isValidReferenceItem = v.notNull(referencedItem, v.OPTIONAL, "Tried to add an empty relatedItem.");
                if (isValid) {
                    this.referencedItems.add(referencedItem);
                }
            }
        }
        return this;
    }

    public ProductCardBuilder ratingCount(Integer ratingCount) {
        boolean isValid = v.notNegative(ratingCount, v.OPTIONAL, "ratingCount must be a positive integer.");
        if (isValid) {
            this.ratingCount = ratingCount;
        }
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
        boolean isValid = v.notNull(imgUrl, v.OPTIONAL, "Tried to add a null imgUrl");
        if (isValid) {
            images.add(imgUrl);
        }
        return this;
    }

    public ProductCardBuilder images(List<URL> images) {
        boolean isValid = v.notNull(images, v.OPTIONAL, "images must not be null.");
        if (isValid) {
            for (URL img : images){
                boolean isValidImg = v.notNull(img, v.OPTIONAL, "Tried to add a null image");
                if (isValidImg) {
                    this.images.add(img);
                }
            }
        }
        return this;
    }
    
    public ProductCardBuilder color(ProductColor color){
        boolean isValid = v.notNull(color, v.OPTIONAL, "Tried to add a null color");
        if (isValid) {
            colors.add(color);
        }
        return this;
    }

    public ProductCardBuilder colors(List<ProductColor> colors){
        boolean isValid = v.notNull(colors, v.OPTIONAL, "colors must not be null.");
        if (isValid) {
            for (ProductColor color : colors){
                boolean isValidColor = v.notNull(color, v.OPTIONAL, "Tried to add a null color");
                if (isValidColor) {
                    this.colors.add(color);
                }
            }
        }
        return this;
    }

    public ProductCardBuilder description(String description) {
        boolean isValid = v.notEmpty(description, v.OPTIONAL, "Tried to set description to an empty string.");
        if (isValid) {
            this.description = description;
        }
        return this;
    }

    public ProductCardBuilder brand(String brand) {
        boolean isValid = v.notEmpty(brand, v.OPTIONAL, "Tried to set brand to an empty string.");
        if (isValid) {
            this.brand = brand;
        }
        return this;
    }

    public ProductCardBuilder merchant(String merchant) {
        boolean isValid = v.notEmpty(merchant, v.OPTIONAL, "Tried to set merchant to an empty string.");
        if (isValid) {
            this.merchant = merchant;
        }
        return this;
    }

    public ProductCardBuilder productId(String productId){
        boolean isValid = v.notEmpty(productId, v.OPTIONAL, "Tried to set productId to an empty string");
        if (isValid) {
            this.productId = productId;
        }
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
     * Get a list of validation errors.
     * @return the list of errors.
     */
    public List<String> getErrors(){
        return v.getErrors();
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
        boolean isValid = v.notNullOrEmpty(name, v.REQUIRED, "Product name cannot be blank.");
        if (isValid) {
            this.name = name;
        }
        return this;
    }
    
    private ProductCardBuilder webUrl(URL webUrl){
        boolean isValid = v.notNull(webUrl, v.REQUIRED, "Must specify a product webUrl.");
        if (isValid) {
            this.webUrl = webUrl;
        }
        return this;
    }
    
    private ProductCardBuilder offers(List<Offer> offers){
        boolean isValid = v.notNullOrEmpty(offers, v.REQUIRED, "Must specify at least one offer.");

        boolean foundValidOffer = false;

        if (isValid) {
            for (Offer offer : offers){
                boolean isValidOffer = v.notNull(offer, v.OPTIONAL, ".");
                if (isValidOffer){
                    foundValidOffer = true;
                    this.offers.add(offer);
                }
            }
        }

        if (!foundValidOffer){
            v.fail(v.REQUIRED, "Must specify at least one offer.");
        }

        return this;
    }
    
    private ProductCardBuilder offer(Offer offer){
        offers.add(offer);
        return this;
    }

}
