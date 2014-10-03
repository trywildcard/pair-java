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
        boolean isValid = v.required(v.notNullOrEmpty(webUrl), "Must specify a product webUrl.");
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
