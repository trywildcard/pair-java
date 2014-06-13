package com.wildcard.model.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.PropertyNamingStrategyBase;

public class CardMapper {
    private final ObjectMapper mapper = new ObjectMapper();
    
    public CardMapper(){
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategyBase.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
    }
    
    public ObjectMapper getObjectMapper(){
        return mapper;
    }
}
