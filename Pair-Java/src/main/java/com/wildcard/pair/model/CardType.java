package com.wildcard.pair.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CardType {
    PRODUCT, PRODUCT_SEARCH;
    
    @JsonValue
    public String getName(){
        return name().toLowerCase();
    }
}
