package com.trywildcard.pair.model.search;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.trywildcard.pair.Pair;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.model.Card;
import com.trywildcard.pair.model.CardType;
import com.trywildcard.pair.validation.ValidationTool;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Structures a Product Search Results AbstractCard, which embeds many ProductSearchResults.
 */

public final class ProductSearchCard implements Card {

    @JsonIgnore
    private ValidationTool v = new ValidationTool();

    private String pairVersion = Pair.getInstance().getVersion();

    private CardType cardType;
    private Integer totalResults;
    private List<ProductSearchResult> products;

    /**
     * Construct an instance of <code>ProductSearchResultsCard</code>.
     * @param products a list of <code>ProductSearchResult</code> objects.
     * @param totalResults the total number of results for this search response.
     */
    public ProductSearchCard(List<ProductSearchResult> products, Integer totalResults) throws CardBuilderException {
        setCardType(CardType.PRODUCT_SEARCH);
        setProducts(products);
        setTotalResults(totalResults);
    }

    public String getPairVersion(){
        return pairVersion;
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
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.PropertyNamingStrategyBase.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        return mapper.writeValueAsString(this);
    }

    public CardType getCardType() {
        return cardType;
    }


    /**
     * Private constructor to allow for Jackson deserialization.
     */
    private ProductSearchCard(){}

    private void setTotalResults(Integer totalResults) throws CardBuilderException {
        v.required(v.notNull(totalResults), "Must supply a value for totalResults.");
        v.required(v.notNegative(totalResults), "totalResults must be a positive Integer.");
        this.totalResults = totalResults;
    }

    private void setCardType(CardType cardType){
        this.cardType = CardType.PRODUCT_SEARCH;
    }

    private void setProducts(List<ProductSearchResult> products) throws CardBuilderException {
        v.required(v.notNull(products), "Must supply a list of products.");
        this.products = Collections.unmodifiableList(products);
    }

    private void setPairVersion(String pairVersion){
        this.pairVersion = pairVersion;
    }
}
