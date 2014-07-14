package com.trywildcard.pair.translation.pinterest;

import com.trywildcard.pair.model.product.Gender;
import com.trywildcard.pair.model.product.Offer;
import com.trywildcard.pair.model.product.ProductCard;

import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * Created by michaelgarate on 6/23/14.
 */
public class PinterestBasicProductCard {
    private String title;
    private String description;
    private String brand;
    private String productId;
    private Gender gender;
    private List<URL> images;
    private Date productExpiration;
    private List<URL> relatedItems;
    private List<URL> referencedItems;
    private Number rating;
    private Number ratingScale;
    private Number ratingCount;

    public PinterestBasicProductCard(ProductCard productCard){
        Offer firstOffer = productCard.getOffers().get(0);

        this.title = productCard.getName();
        this.description = productCard.getDescription();
        this.brand = productCard.getBrand();
        this.productId = productCard.getProductId();
        this.gender = firstOffer.getGender();
        this.images = productCard.getImages();
        this.productExpiration = firstOffer.getExpirationDate();
        this.relatedItems = productCard.getRelatedItems();
        this.referencedItems = productCard.getReferencedItems();
        this.rating = productCard.getRating();
        this.ratingScale = productCard.getRatingScale();
        this.ratingCount = productCard.getRatingCount();
    }


    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public Gender getGender() {
        return gender;
    }

    public List<URL> getImages() {
        return images;
    }

    public String getBrand(){
        return brand;
    }

    public String getProductId(){
        return productId;
    }

    public Date getProductExpiration(){
        return new Date(productExpiration.getTime());
    }

    public List<URL> getRelatedItems(){
        return relatedItems;
    }

    public List<URL> getReferencedItems(){
        return referencedItems;
    }

    public Number getRating(){
        return rating;
    }

    public Number getRatingScale() {
        return ratingScale;
    }

    public Number getRatingCount() {
        return ratingCount;
    }
}
