package com.wildcard.model.product;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    MALE, FEMALE, UNISEX;

    @JsonValue
    public String getName(){
        return name().toLowerCase();
    }
}
