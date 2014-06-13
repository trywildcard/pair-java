package com.wildcard.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MappingColor {
    Beige, Black, Blue, Bronze, Brown, Gold, Green, Gray, Metallic,
    Multicolored, OffWhite, Orange, Pink, Purple, Red, Silver,
    Transparent, Turquoise, White, Yellow;
    

    @JsonValue
    public String getName(){
        return name().toLowerCase();
    }
}
