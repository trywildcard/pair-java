package com.trywildcard.pair.model.product;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Defines gender options.
 */

public enum Gender {
    MALE, FEMALE, UNISEX;

    /**
     * Get the value name as a lowercase string.
     * @return name
     */
    @JsonValue
    public String getName(){
        return name().toLowerCase();
    }
}
