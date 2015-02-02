package com.trywildcard.pair.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.trywildcard.pair.model.AbstractCard;
import com.trywildcard.pair.model.Card;

/**
 * Contains a configured Jackson ObjectMapper instance in order to support JSON serialization.
 * Classes implementing the <code>AbstractCard</code> interface will reference this serializer in the writeAsJsonString() method.
 */
public class CardSerializer {
    private final ObjectMapper mapper = new ObjectMapper();

    public CardSerializer(){
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.PropertyNamingStrategyBase.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    }

    public String writeCard(Card card) throws JsonProcessingException{
        return mapper.writeValueAsString(card);
    }
}
