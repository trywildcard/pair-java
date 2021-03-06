package com.trywildcard.pair.model.product;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Defines possible colors. 
 */
public enum MappingColor {
    Beige, Black, Blue, Bronze, Brown, Gold, Green, Gray, Metallic,
    Multicolored, OffWhite, Orange, Pink, Purple, Red, Silver,
    Transparent, Turquoise, White, Yellow;
    

    @JsonValue
    public String getName(){
        return name().toLowerCase();
    }
}
