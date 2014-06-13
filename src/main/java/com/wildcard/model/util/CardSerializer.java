package com.wildcard.model.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wildcard.model.Card;

public class CardSerializer {
    private static final ObjectMapper mapper = new CardMapper().getObjectMapper();
    
    public static String writeCardAsString(Card card) throws JsonProcessingException{
        return mapper.writeValueAsString(card);
    }
}
