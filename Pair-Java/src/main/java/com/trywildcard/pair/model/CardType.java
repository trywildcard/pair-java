package com.trywildcard.pair.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The type of card.
 */
public enum CardType {
    PRODUCT, PRODUCT_SEARCH, ARTICLE, REVIEW;

    /**
     * Get the value name as a lowercase string.
     * @return name
     */
    @JsonValue
    public String getName(){
        return name().toLowerCase();
    }
}
