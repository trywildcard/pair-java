package com.trywildcard.pair.model.media;

import com.fasterxml.jackson.annotation.JsonValue;


/** Defines types of allowable media **/
public enum MediaType {
    IMAGE, VIDEO;

    /**
     * Get the value name as a lowercase string.
     * @return name
     */
    @JsonValue
    public String getName(){
        return name().toLowerCase();
    }
}
