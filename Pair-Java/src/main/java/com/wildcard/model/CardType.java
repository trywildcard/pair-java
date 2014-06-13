package com.wildcard.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CardType {
    PRODUCT;
    
    @JsonValue
    public String getName(){
        return name().toLowerCase();
    }
}
