package com.wildcard.pair.model.search;

import com.wildcard.pair.model.Builder;
import com.wildcard.pair.model.Price;
import com.wildcard.pair.util.ValidationTool;

import java.net.URL;

/**
 * Created by michaelgarate on 6/16/14.
 */
public class SearchProductBuilder implements Builder{

    String name;
    URL url;
    Price price;
    URL image;

    public SearchProductBuilder(String name, URL url, Price price){
        name(name);
        url(url);
        price(price);
    }

    public void image(URL image){
        this.image = image;
    }

    public void name(String name){
        ValidationTool.notNullOrEmpty(name, "Must supply a name.");
        this.name = name;
    }

    public void url(URL url){
        ValidationTool.notNull(url, "Must supply a url.");
        this.url = url;
    }

    public void price(Price price){
        ValidationTool.notNull(price, "Must supply a price");
        this.price = price;
    }

    public SearchProduct build(){
        return new SearchProduct(this);
    }

}
