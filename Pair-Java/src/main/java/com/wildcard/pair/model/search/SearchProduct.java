package com.wildcard.pair.model.search;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wildcard.pair.model.Price;

import java.net.URL;

/**
 * Created by michaelgarate on 6/16/14.
 */
@JsonDeserialize(builder = SearchProductBuilder.class)
public final class SearchProduct {
    private final String name;
    private final Price price;
    private final URL url;
    private final URL image;

    public SearchProduct(SearchProductBuilder builder){
        this.name = builder.name;
        this.price = builder.price;
        this.url = builder.url;
        this.image = builder.image;
    }

    public String getName(){
        return name;
    }

    public Price getPrice(){
        return price;
    }

    public URL getUrl(){
        return url;
    }

    public URL getImage() {
        return image;
    }
}
