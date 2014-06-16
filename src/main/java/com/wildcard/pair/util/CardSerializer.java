package com.wildcard.pair.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wildcard.pair.model.Card;

public class CardSerializer {
    private static final ObjectMapper mapper = new CardMapper().getObjectMapper();
    
    public static String writeCardAsString(Card card) throws JsonProcessingException{
        return mapper.writeValueAsString(card);
    }
}
