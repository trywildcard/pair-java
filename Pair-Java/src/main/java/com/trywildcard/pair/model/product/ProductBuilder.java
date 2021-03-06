package com.trywildcard.pair.model.product;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.model.Builder;
import com.trywildcard.pair.extraction.MetaTagModel;
import com.trywildcard.pair.validation.ValidationTool;
import org.apache.commons.lang3.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
public class ProductBuilder implements Builder<Product> {

    protected ValidationTool v = new ValidationTool();

    //required fields
    protected String name;
    protected String description;
    protected List<URL> images = new ArrayList<URL>();

    //optional fields
    protected String merchant;
    protected String brand;
    protected List<ProductColor> colors = new ArrayList<ProductColor>();
    protected Float rating;
    protected Float ratingScale;
    protected Integer ratingCount;
    protected List<URL> relatedItems = new ArrayList<URL>();
    protected List<URL> referencedItems = new ArrayList<URL>();
    protected Map<String, String> sizes = new HashMap<String, String>();
    protected List<String> options = new ArrayList<String>();
    protected Gender gender;
    protected String model;

    private ProductBuilder() {
    }

    /**
     * Construct an <code>ProductBuilder</code> provided a name, description and list of images.
     *
     * @param name
     */
    public ProductBuilder(String name, String description, String image) throws CardBuilderException {
        name(name);
        description(description);
        image(image);
    }

    /**
     * Construct an <code>ProductBuilder</code> provided a name, description and list of images.
     *
     * @param name
     */
    public ProductBuilder(String name, String description, List<String> images) throws CardBuilderException {
        name(name);
        description(description);
        images(images);
    }

    public ProductBuilder(MetaTagModel metaTagModel) throws CardBuilderException {

        if (metaTagModel == null) {
            throw new CardBuilderException("MetaTagModel is required");
        }

        //try to build product first, it requires a name, description and image
        if (StringUtils.isEmpty(metaTagModel.getTitle()) || StringUtils.isEmpty(metaTagModel.getDescription()) || StringUtils.isEmpty(metaTagModel.getImageUrl())) {
            throw new CardBuilderException("Product title and/or product description and/or product image is not contained in meta tags and is required to create a ProductBuilder");
        }

        name(metaTagModel.getTitle());
        description(metaTagModel.getDescription());
        image(metaTagModel.getImageUrl());
    }

    public ProductBuilder model(String model) {
        boolean isValid = v.optional(v.notEmpty(model), "Tried to set model to an empty string.");

        if (isValid) {
            this.model = model;
        }
        return this;
    }

    public ProductBuilder gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public ProductBuilder sizes(Map<String, String> sizes) {
        boolean isValid = v.optional(v.notNull(sizes), "Sizes must not be null.");

        if (isValid) {
            for (String key : sizes.keySet()) {
                String value = sizes.get(key);

                boolean isValidSize = v.optional(v.notNullOrEmpty(value), "Tried to set a null or empty size value.");

                if (isValidSize) {
                    this.sizes.put(key, value);
                }
            }

        }
        return this;
    }

    public ProductBuilder option(String option) {
        boolean isValid = v.optional(v.notNullOrEmpty(option), "Tried to add an empty option.");
        if (isValid) {
            this.options.add(option);
        }
        return this;
    }

    public ProductBuilder options(List<String> options) {
        boolean isValid = v.optional(v.notNull(options), "Options must not be null.");
        if (isValid) {
            for (String option : options) {
                boolean isValidOption = v.optional(v.notNullOrEmpty(option), "Tried to add an empty option.");
                if (isValidOption) {
                    this.options.add(option);
                }
            }
        }
        return this;
    }

    public ProductBuilder relatedItem(String relatedItem) {
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

    public ProductBuilder relatedItems(List<String> relatedItems) {
        boolean isValid = v.optional(v.notNull(relatedItems), "relatedItems must not be null.");
        if (isValid) {
            for (String relatedItem : relatedItems) {
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

    public ProductBuilder referencedItem(String referencedItem) {
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

    public ProductBuilder referencedItems(List<String> referencedItems) {
        boolean isValid = v.optional(v.notNull(referencedItems), "relatedItems must not be null.");
        if (isValid) {
            for (String referencedItem : referencedItems) {
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

    public ProductBuilder ratingCount(Integer ratingCount) {
        boolean isValid = v.optional(v.notNegative(ratingCount), "ratingCount must be a positive integer.");
        if (isValid) {
            this.ratingCount = ratingCount;
        }
        return this;
    }

    public ProductBuilder ratingScale(Float ratingScale) {
        this.ratingScale = ratingScale;
        return this;
    }

    public ProductBuilder rating(Float rating) {
        this.rating = rating;
        return this;
    }

    private ProductBuilder image(String imgUrl) throws CardBuilderException {
        boolean isValid = v.required(v.notNull(imgUrl), "Tried to add a null imgUrl");

        if (isValid) {
            try {
                images.add(new URL(imgUrl));
            } catch (MalformedURLException e) {
                v.required(v.fail(), "Could not parse image URL from string.");
            }
        }
        return this;
    }

    private ProductBuilder images(List<String> images) throws CardBuilderException {
        boolean isValid = v.required(v.notNullOrEmpty(images), "images must not be null.");
        if (isValid) {
            for (String img : images) {
                boolean isValidImg = v.required(v.notNull(img), "Tried to add a null image");
                if (isValidImg) {
                    try {
                        this.images.add(new URL(img));
                    } catch (MalformedURLException e) {
                        v.required(v.fail(), "Could not parse image URL from string.");
                    }
                }
            }
        }
        return this;
    }

    public ProductBuilder color(ProductColor color) {
        boolean isValid = v.optional(v.notNull(color), "Tried to add a null color");
        if (isValid) {
            colors.add(color);
        }
        return this;
    }

    public ProductBuilder colors(List<ProductColor> colors) {
        boolean isValid = v.optional(v.notNull(colors), "colors must not be null.");
        if (isValid) {
            for (ProductColor color : colors) {
                boolean isValidColor = v.optional(v.notNull(color), "Tried to add a null color");
                if (isValidColor) {
                    this.colors.add(color);
                }
            }
        }
        return this;
    }

    public ProductBuilder description(String description) throws CardBuilderException {
        boolean isValid = v.required(v.notNullOrEmpty(description), "Product Description cannot be blank");
        if (isValid) {
            this.description = description;
        }
        return this;
    }

    public ProductBuilder brand(String brand) {
        boolean isValid = v.optional(v.notEmpty(brand), "Tried to set brand to an empty string.");
        if (isValid) {
            this.brand = brand;
        }
        return this;
    }

    public ProductBuilder merchant(String merchant) {
        boolean isValid = v.optional(v.notEmpty(merchant), "Tried to set merchant to an empty string.");
        if (isValid) {
            this.merchant = merchant;
        }
        return this;
    }

    private ProductBuilder name(String name) throws CardBuilderException {
        boolean isValid = v.required(v.notNullOrEmpty(name), "Product name cannot be blank.");
        if (isValid) {
            this.name = name;
        }
        return this;
    }

    /**
     * Instantiate a <code>ProductCard</code> with the data in this builder.
     *
     * @return the constructed product card
     */
    public Product build() {
        Product product = new Product(this);
        return product;
    }

    /**
     * Get a list of validation errors.
     *
     * @return the list of errors.
     */
    public List<String> getErrors() {
        return v.getErrors();
    }

}
