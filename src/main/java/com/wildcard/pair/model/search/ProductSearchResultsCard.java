package com.wildcard.pair.model.search;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wildcard.pair.model.Card;
import com.wildcard.pair.model.CardType;
import com.wildcard.pair.util.CardSerializer;
import com.wildcard.pair.util.ValidationTool;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Structures a Product Search Results Card, which embeds many ProductSearchResults.
 */

public final class ProductSearchResultsCard implements Card {

    @JsonIgnore
    private ValidationTool v = new ValidationTool();

    private CardType cardType;
    private Integer totalResults;
    private List<ProductSearchResult> products;

    /**
     * Construct an instance of <code>ProductSearchResultsCard</code>.
     * @param products a list of <code>ProductSearchResult</code> objects.
     * @param totalResults the total number of results for this search response.
     */
    public ProductSearchResultsCard(List<ProductSearchResult> products, Integer totalResults){
        setCardType(CardType.PRODUCT_SEARCH);
        setProducts(products);
        setTotalResults(totalResults);
    }

    public Integer getTotalResults(){
        return totalResults;
    }

    public List<ProductSearchResult> getProducts(){
        return products;
    }


    /**
     * Serialize fields in the Wildcard product search results card format.
     * @return the string representation of this card.
     * @throws IOException
     */
    public String writeAsJsonString() throws IOException {
        return new CardSerializer().writeCard(this);
    }

    public CardType getCardType() {
        return cardType;
    }



    /**
     * Private constructor to allow for Jackson deserialization.
     */
    private ProductSearchResultsCard(){}

    private void setTotalResults(Integer totalResults){
        v.notNull(totalResults, v.REQUIRED, "Must supply a value for totalResults.");
        v.notNegative(totalResults, v.REQUIRED, "totalResults must be a positive Integer.");
        this.totalResults = totalResults;
    }

    private void setCardType(CardType cardType){
        this.cardType = CardType.PRODUCT_SEARCH;
    }

    private void setProducts(List<ProductSearchResult> products){
        v.notNull(products, v.REQUIRED, "Must supply a list of products.");
        this.products = Collections.unmodifiableList(products);
    }
}
