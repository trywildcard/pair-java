package com.trywildcard.pair.model.search;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.trywildcard.pair.model.Price;
import java.net.URL;

/**
 * Product search result. Several of these are embedded in a <code>ProductSearchResults</code> card.
 */
@JsonDeserialize(builder = ProductSearchResultBuilder.class)
public final class ProductSearchResult {
    private final String name;
    private final Price price;
    private final URL cardUrl;
    private final String image;

    /**
     * Construct a product search result using a <code>ProductSearchResultBuilder</code>.
     * @param builder the builder.
     */
    public ProductSearchResult(ProductSearchResultBuilder builder){
        this.name = builder.name;
        this.price = builder.price;
        this.cardUrl = builder.cardUrl;
        this.image = builder.image;
    }

    public String getName(){
        return name;
    }

    public Price getPrice(){
        return price;
    }

    public URL getCardUrl(){
        return cardUrl;
    }

    public String getImage() {
        return image;
    }
}
