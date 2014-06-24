package com.wildcard.pair.model.search;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.wildcard.pair.model.Builder;
import com.wildcard.pair.model.Price;
import com.wildcard.pair.util.ValidationTool;

import java.net.URL;

@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
public class ProductSearchResultBuilder implements Builder<ProductSearchResult> {

    String name;
    URL cardUrl;
    Price price;
    URL image;

    /**
     * Construct a <code>ProductSearchResultBuilder</code>
     * @param name the name of the product
     * @param product_card_url the url to access the product in a web browser
     * @param price the primary price of this product.
     */
    public ProductSearchResultBuilder(String name, URL cardUrl, Price price){
        name(name);
        cardUrl(cardUrl);
        price(price);
    }

    public void image(URL image){
        this.image = image;
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


    private void name(String name){
        ValidationTool.notNullOrEmpty(name, "Must supply a name.");
        this.name = name;
    }

    private void cardUrl(URL cardUrl){
        ValidationTool.notNull(cardUrl, "Must supply a cardUrl.");
        this.cardUrl = cardUrl;
    }

    private void price(Price price){
        ValidationTool.notNull(price, "Must supply a price");
        this.price = price;
    }
}
