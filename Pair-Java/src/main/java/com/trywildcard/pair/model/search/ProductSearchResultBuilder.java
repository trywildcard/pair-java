package com.trywildcard.pair.model.search;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.model.Builder;
import com.trywildcard.pair.model.product.Price;
import com.trywildcard.pair.validation.ValidationTool;

import java.net.MalformedURLException;
import java.net.URL;

@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
public class ProductSearchResultBuilder implements Builder<ProductSearchResult> {

    private ValidationTool v = new ValidationTool();

    protected String name;
    protected URL productCardAddress;
    protected Price price;
    protected URL imageUrl;

    /**
     * Construct a <code>ProductSearchResultBuilder</code>
     * @param name the name of the product
     * @param productCardAddress the address to access the product card
     * @param price the primary price of this product.
     */
    public ProductSearchResultBuilder(String name, String productCardAddress, Price price, String imageUrl) throws CardBuilderException {
        name(name);
        productCardAddress(productCardAddress);
        price(price);
        imageUrl(imageUrl);
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

    private void productCardAddress(String productCardAddress) throws CardBuilderException {
        v.required(v.notNull(productCardAddress), "Must supply a card address.");
        try {
            this.productCardAddress = new URL(productCardAddress);
        } catch (MalformedURLException e) {
            v.required(v.fail(), "Could not parse URL from cardUrl string.");
        }
    }

    private void price(Price price) throws CardBuilderException {
        v.required(v.notNull(price), "Must supply a price");
        this.price = price;
    }

    private void imageUrl(String imgUrl) throws CardBuilderException {
        boolean isValid = v.required(v.notNull(imgUrl), "Tried to add a null imgUrl");

        if (isValid) {
            try {
                this.imageUrl = new URL(imgUrl);
            } catch (MalformedURLException e) {
                v.required(v.fail(), "Could not parse image URL from string.");
            }
        }
    }
}
