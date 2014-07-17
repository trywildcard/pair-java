package com.trywildcard.pair.model.search;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.model.Builder;
import com.trywildcard.pair.model.Price;
import com.trywildcard.pair.validation.ValidationTool;

import java.net.MalformedURLException;
import java.net.URL;

@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
public class ProductSearchResultBuilder implements Builder<ProductSearchResult> {

    private ValidationTool v = new ValidationTool();

    protected String name;
    protected URL productCardUrl;
    protected Price price;
    protected String imageUrl;

    /**
     * Construct a <code>ProductSearchResultBuilder</code>
     * @param name the name of the product
     * @param productCardUrl the url to access the product in a web browser
     * @param price the primary price of this product.
     */
    public ProductSearchResultBuilder(String name, String productCardUrl, Price price) throws CardBuilderException {
        name(name);
        productCardUrl(productCardUrl);
        price(price);
    }

    public void imageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    /**
     * Instantiate a <code>ProductSearchResult</code> with the data in this builder.
     * @return the constructed product search result
     */
    public ProductSearchResult build(){
        return new ProductSearchResult(this);
    }


    /**
     * Private constructor to allow for Jackson deserialization.
     */
    private ProductSearchResultBuilder(){}


    private void name(String name) throws CardBuilderException {
        v.required(v.notNullOrEmpty(name), "Must supply a name.");
        this.name = name;
    }

    private void productCardUrl(String productCardUrl) throws CardBuilderException {
        v.required(v.notNull(productCardUrl), "Must supply a cardUrl.");
        try {
            this.productCardUrl = new URL(productCardUrl);
        } catch (MalformedURLException e) {
            v.optional(v.fail(), "Could not parse URL from cardUrl string.");
        }
    }

    private void price(Price price) throws CardBuilderException {
        v.required(v.notNull(price), "Must supply a price");
        this.price = price;
    }
}
