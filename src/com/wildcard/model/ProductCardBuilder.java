package com.wildcard.model;

import java.util.ArrayList;
import java.util.List;

public class ProductCardBuilder {
    // internal builder variables
    private final float UNDEFINED_PRICE = -1;
    
    // required fields
    protected final String name;
    protected final List<Offer> offers;
    
    // optional filds
    protected String url;
    protected String brandName;
    protected float price = UNDEFINED_PRICE;
    protected String merchant;
    protected String brand;
    protected String description;
    protected Gender gender;
    protected List<String> images;
    
    public ProductCardBuilder(String name){
        this.name = name;
        this.offers = new ArrayList<Offer>();
    }
    
    public ProductCardBuilder images(List<String> images){
        this.images = images;
        return this;
    }
    
    public ProductCardBuilder gender(Gender gender){
        this.gender = gender;
        return this;
    }

    public ProductCardBuilder description(String description){
        this.description = description;
        return this;
    }
    
    public ProductCardBuilder brand(String brand){
        this.brand = brand;
        return this;
    }
    
    public ProductCardBuilder url(String url){
        this.url = url;
        return this;
    }

    public ProductCardBuilder merchant(String merchant){
        this.merchant = merchant;
        return this;
    }

    public ProductCardBuilder brandName(String brandName){
        this.brandName = brandName;
        return this;
    }
    
    public ProductCard build(){
        
        if (offers.size() == 0){
            if (price == UNDEFINED_PRICE){
                // complain
            } else {
                offers.add(new Offer(price));
            }
        }
        
        return new ProductCard(this);
    }
}
