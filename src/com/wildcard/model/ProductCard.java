package com.wildcard.model;

import java.util.List;

public class ProductCard implements Card {
    private final CardType cardType;
    private final String name;
    private final String url;
    private final String brandName;
    private final List<Offer> offers;
    private final String merchant;
    private final String brand;
    private final String description;
    private final Gender gender;
    // TODO: product.colors
    private final List<String> images;
    
    public ProductCard(ProductCardBuilder builder){
        this.cardType = CardType.PRODUCT;
        this.name = builder.name;
        this.url = builder.url;
        this.brandName = builder.brandName;
        this.offers = builder.offers;
        this.merchant = builder.merchant;
        this.brand = builder.brand;
        this.description = builder.description;
        this.gender = builder.gender;
        this.images = builder.images;
    }
    
    public List<String> getImages(){
        return images;
    }
    
    public Gender getGender(){
        return gender;
    }
    
    public String getDescription(){
        return description;
    }
    
    public String getBrand(){
        return brand;
    }
    
    public String getMerchant(){
        return merchant;
    }
    
    public List<Offer> getOffers(){
        return offers;
    }

    public String getName(){
        return name;
    }

    public String getBrandName(){
        return brandName;
    }
    
    public String getUrl(){
        return url;
    }
    
    public CardType getCardType(){
        return cardType;
    }
    
    public String writeAsJson(){
        return null;
    }
    
}
