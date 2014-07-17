package com.trywildcard.pair.model.product;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.trywildcard.pair.Pair;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.model.Builder;
import com.trywildcard.pair.model.CardType;
import com.trywildcard.pair.validation.ValidationTool;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
public class ProductCardBuilder implements Builder<ProductCard> {

    protected ValidationTool v = new ValidationTool();

    protected String pairVersion = Pair.getInstance().getVersion();

    // required fields
    protected CardType cardType;
    protected String name;
    protected List<Offer> offers = new ArrayList<Offer>();
    protected URL webUrl;

    // optional fields
    protected String productId;
    protected String merchant;
    protected String brand;
    protected String description;
    protected List<ProductColor> colors = new ArrayList<ProductColor>();
    protected List<URL> images = new ArrayList<URL>();
    protected Float rating;
    protected Float ratingScale;
    protected Integer ratingCount;
    protected List<URL> relatedItems = new ArrayList<URL>();
    protected List<URL> referencedItems = new ArrayList<URL>();
    protected Map<String, String> sizes = new HashMap<String,String>();
    protected List<String> options = new ArrayList<String>();
    protected String model;
    protected String appLinkIos;
    protected String appLinkAndroid;

    /**
     * Construct a builder by providing a list of pre-made offers.
     *
     * @param name product name
     * @param offers list of offers
     * @param webUrl url to access this product in a web browser
     */
    public ProductCardBuilder(String name, List<Offer> offers, String webUrl) throws CardBuilderException {
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
    public ProductCardBuilder(String name, Float price, String webUrl) throws CardBuilderException {
        Offer offer = new OfferBuilder(price).build();
        offer(offer);
        cardType(CardType.PRODUCT);
        name(name);
        webUrl(webUrl);
    }
    
    public ProductCardBuilder appLinkAndroid(String appLinkAndroid) {
        boolean isValid = v.optional(v.notEmpty(appLinkAndroid), "Tried to set appLinkAndroid to an empty string.");

        if (isValid) {
            this.appLinkAndroid = appLinkAndroid;
        }

        return this;
    }

    public ProductCardBuilder appLinkIos(String appLinkIos) {
        boolean isValid = v.optional(v.notEmpty(appLinkIos), "Tried to set appLinkIos to an empty string.");

        if (isValid){
            this.appLinkIos = appLinkIos;
        }
        return this;
    }

    public ProductCardBuilder model(String model) {
        boolean isValid = v.optional(v.notEmpty(model), "Tried to set model to an empty string.");

        if (isValid){
            this.model = model;
        }
        return this;
    }
    
    public ProductCardBuilder sizes(Map<String, String> sizes){
        boolean isValid = v.optional(v.notNull(sizes), "Sizes must not be null.");

        if (isValid){
            for (String key : sizes.keySet()){
                String value = sizes.get(key);

                boolean isValidSize = v.optional(v.notNullOrEmpty(value), "Tried to set a null or empty size value.");

                if (isValidSize){
                    this.sizes.put(key, value);
                }
            }

        }
        return this;
    }
    
    public ProductCardBuilder option(String option){
        boolean isValid = v.optional(v.notNullOrEmpty(option), "Tried to add an empty option.");
        if (isValid) {
            this.options.add(option);
        }
        return this;
    }

    public ProductCardBuilder options(List<String> options) {
        boolean isValid = v.optional(v.notNull(options), "Options must not be null.");
        if (isValid) {
            for (String option : options){
                boolean isValidOption = v.optional(v.notNullOrEmpty(option), "Tried to add an empty option.");
                if (isValidOption) {
                    this.options.add(option);
                }
            }
        }
        return this;
    }

    public ProductCardBuilder relatedItem(String relatedItem){
        boolean isValid = v.optional(v.notNull(relatedItem), "Tried to add an empty relatedItem.");
        if (isValid) {
            try {
                this.relatedItems.add(new URL(relatedItem));
            } catch (MalformedURLException e) {
                v.optional(v.fail(), "Could not parse URL from relatedItem string.");
            }
        }
        return this;
    }

    public ProductCardBuilder relatedItems(List<String> relatedItems) {
        boolean isValid = v.optional(v.notNull(relatedItems), "relatedItems must not be null.");
        if (isValid) {
            for (String relatedItem : relatedItems){
                boolean isValidRelatedItem = v.optional(v.notNull(relatedItem), "Tried to add an empty relatedItem.");
                if (isValidRelatedItem) {
                    try {
                        this.relatedItems.add(new URL(relatedItem));
                    } catch (MalformedURLException e) {
                        v.optional(v.fail(), "Could not parse URL from relatedItem string.");
                    }
                }
            }
        }
        return this;
    }

    public ProductCardBuilder referencedItem(String referencedItem){
        boolean isValid = v.optional(v.notNull(referencedItem), "Tried to add an empty relatedItem.");
        if (isValid) {
            try {
                this.relatedItems.add(new URL(referencedItem));
            } catch (MalformedURLException e) {
                v.optional(v.fail(), "Could not parse URL from string.");
            }
        }
        return this;
    }

    public ProductCardBuilder referencedItems(List<String> referencedItems) {
        boolean isValid = v.optional(v.notNull(referencedItems), "relatedItems must not be null.");
        if (isValid) {
            for (String referencedItem : referencedItems){
                boolean isValidReferenceItem = v.optional(v.notNull(referencedItem), "Tried to add an empty relatedItem.");
                if (isValidReferenceItem) {
                    try {
                        this.referencedItems.add(new URL(referencedItem));
                    } catch (MalformedURLException e) {
                        v.optional(v.fail(), "Could not parse URL from string.");
                    }
                }
            }
        }
        return this;
    }

    public ProductCardBuilder ratingCount(Integer ratingCount) {
        boolean isValid = v.optional(v.notNegative(ratingCount), "ratingCount must be a positive integer.");
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
    
    public ProductCardBuilder image(String imgUrl){
        boolean isValid = v.optional(v.notNull(imgUrl), "Tried to add a null imgUrl");

        if (isValid) {
            try {
                images.add(new URL(imgUrl));
            } catch (MalformedURLException e) {
                v.optional(v.fail(), "Could not parse image URL from string.");
            }
        }
        return this;
    }

    public ProductCardBuilder images(List<String> images) {
        boolean isValid = v.optional(v.notNull(images), "images must not be null.");
        if (isValid) {
            for (String img : images){
                boolean isValidImg = v.optional(v.notNull(img), "Tried to add a null image");
                if (isValidImg) {
                    try {
                        this.images.add(new URL(img));
                    } catch (MalformedURLException e) {
                        v.optional(v.fail(),"Could not parse image URL from string.");
                    }
                }
            }
        }
        return this;
    }
    
    public ProductCardBuilder color(ProductColor color){
        boolean isValid = v.optional(v.notNull(color), "Tried to add a null color");
        if (isValid) {
            colors.add(color);
        }
        return this;
    }

    public ProductCardBuilder colors(List<ProductColor> colors){
        boolean isValid = v.optional(v.notNull(colors), "colors must not be null.");
        if (isValid) {
            for (ProductColor color : colors){
                boolean isValidColor = v.optional(v.notNull(color), "Tried to add a null color");
                if (isValidColor) {
                    this.colors.add(color);
                }
            }
        }
        return this;
    }

    public ProductCardBuilder description(String description) {
        boolean isValid = v.optional(v.notEmpty(description), "Tried to set description to an empty string.");
        if (isValid) {
            this.description = description;
        }
        return this;
    }

    public ProductCardBuilder brand(String brand) {
        boolean isValid = v.optional(v.notEmpty(brand), "Tried to set brand to an empty string.");
        if (isValid) {
            this.brand = brand;
        }
        return this;
    }

    public ProductCardBuilder merchant(String merchant) {
        boolean isValid = v.optional(v.notEmpty(merchant), "Tried to set merchant to an empty string.");
        if (isValid) {
            this.merchant = merchant;
        }
        return this;
    }

    public ProductCardBuilder productId(String productId){
        boolean isValid = v.optional(v.notEmpty(productId), "Tried to set productId to an empty string");
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
    
    private ProductCardBuilder name(String name) throws CardBuilderException {
        boolean isValid = v.required(v.notNullOrEmpty(name), "Product name cannot be blank.");
        if (isValid) {
            this.name = name;
        }
        return this;
    }
    
    private ProductCardBuilder webUrl(String webUrl) throws CardBuilderException {
        boolean isValid = v.required(v.notNull(webUrl), "Must specify a product webUrl.");
        if (isValid) {
            try {
                this.webUrl = new URL(webUrl);
            } catch (MalformedURLException e) {
                v.required(v.fail(), "Could not parse URL from webUrl string.");
            }
        }
        return this;
    }
    
    private ProductCardBuilder offers(List<Offer> offers) throws CardBuilderException {
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

        return this;
    }
    
    private ProductCardBuilder offer(Offer offer){
        offers.add(offer);
        return this;
    }

    private ProductCardBuilder pairVersion(String pairVersion){
        this.pairVersion = pairVersion;
        return this;
    }

}
