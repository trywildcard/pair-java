package com.wildcard.pair.model.search;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wildcard.pair.model.Card;
import com.wildcard.pair.model.CardType;
import com.wildcard.pair.model.product.ProductCardBuilder;
import com.wildcard.pair.util.CardSerializer;
import com.wildcard.pair.util.ValidationTool;

import java.io.IOException;
import java.util.List;

/**
 * Created by michaelgarate on 6/16/14.
 */

public class ProductSearchCard implements Card {

    private CardType cardType;
    private Integer totalResults;
    private List<SearchProduct> products;

    public ProductSearchCard(List<SearchProduct> products, Integer totalResults){
        ValidationTool.notNull(products, "Must supply a list of products.");
        ValidationTool.notNull(totalResults, "Must supply a value for totalResults.");
        ValidationTool.notNegative(totalResults, "totalResults must be a positive Integer.");

        cardType = CardType.PRODUCT_SEARCH;
        this.products = products;
        this.totalResults = totalResults;
    }

    public Integer getTotalResults(){
        return totalResults;
    }

    public List<SearchProduct> getProducts(){
        return products;
    }

    @Override
    public String writeAsJsonString() throws IOException {
        return CardSerializer.writeCardAsString(this);
    }

    public CardType getCardType() {
        return cardType;
    }


    /*
     * The following private constructor and private methods are required by Jackson.
     */
    private ProductSearchCard(){}

    private void setTotalResults(Integer totalResults){
        this.totalResults = totalResults;
    }

    private void setCardType(CardType cardType){
        this.cardType = CardType.PRODUCT_SEARCH;
    }

    private void setProducts(List<SearchProduct> products){
        this.products = products;
    }
}
