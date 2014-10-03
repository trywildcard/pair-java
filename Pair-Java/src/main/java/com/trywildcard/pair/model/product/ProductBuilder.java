package com.trywildcard.pair.model.product;

import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.model.Price;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by karthiksenthil on 10/3/14.
 */
public class ProductBuilder {

    //required fields
    protected final String name;

    //optional fields
    protected final String merchant;
    protected final String brand;
    protected final String description;
    protected final List<ProductColor> colors;
    protected final List<URL> images;
    protected final Float rating;
    protected final Float ratingScale;
    protected final Integer ratingCount;
    protected final List<URL> relatedItems;
    protected final List<URL> referencedItems;
    protected final Map<String, String> sizes;
    private final List<String> options;
    protected final String model;
    protected final String appLinkIos;
    protected final String appLinkAndroid;



    private ProductBuilder(){}

    /**
     * Construct an <code>ProductBuilder</code> provided a name.
     * @param name
     */
    public ProductBuilder(String name) throws CardBuilderException {
        name(name);
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
}
