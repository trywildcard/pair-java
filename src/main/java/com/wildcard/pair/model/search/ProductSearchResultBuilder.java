package com.wildcard.pair.model.search;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.wildcard.pair.model.Builder;
import com.wildcard.pair.model.Price;
import com.wildcard.pair.util.ValidationTool;

@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
public class ProductSearchResultBuilder implements Builder<ProductSearchResult> {

    private ValidationTool v = new ValidationTool();

    protected String name;
    protected String cardUrl;
    protected Price price;
    protected String image;

    /**
     * Construct a <code>ProductSearchResultBuilder</code>
     * @param name the name of the product
     * @param cardUrl the url to access the product in a web browser
     * @param price the primary price of this product.
     */
    public ProductSearchResultBuilder(String name, String cardUrl, Price price){
        name(name);
        cardUrl(cardUrl);
        price(price);
    }

    public void image(String image){
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
        v.notNullOrEmpty(name, v.REQUIRED, "Must supply a name.");
        this.name = name;
    }

    private void cardUrl(String cardUrl){
        v.notNull(cardUrl, v.REQUIRED, "Must supply a cardUrl.");
        this.cardUrl = cardUrl;
    }

    private void price(Price price){
        v.notNull(price, v.REQUIRED, "Must supply a price");
        this.price = price;
    }
}
