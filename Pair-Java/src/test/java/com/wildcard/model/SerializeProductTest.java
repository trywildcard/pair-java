package com.wildcard.model;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.PropertyNamingStrategyBase;
import com.wildcard.testUtil.TestUtil;

public class SerializeProductTest {
    ObjectMapper mapper = new ObjectMapper();
    
    @Before
    public void prepare(){
        // TODO: test null and empty fields
        //mapper.setSerializationInclusion(Include.NON_NULL);
        //mapper.setSerializationInclusion(Include.NON_EMPTY);
        

        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategyBase.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
    }
    
    @Test
    public void testSerializeProductCard() throws URISyntaxException, JsonMappingException, IOException{
        
        String inputString = TestUtil.readResourceAsString("example_product_card.json");
        JsonNode inputNode = mapper.readTree(inputString);

        ProductCard card = mapper.readValue(inputString, ProductCard.class);
        JsonNode outputNode = mapper.readTree(mapper.writeValueAsString(card));
        
        assertEquals("Expected inputNode and outputNode sizes to match", inputNode.size(), outputNode.size());

        Iterator<String> inputItr = inputNode.fieldNames();
        Iterator<String> outputItr = outputNode.fieldNames();
        
        while (inputItr.hasNext()){
            String inFieldName = inputItr.next();
            String outFieldName = outputItr.next();
            
            assertEquals("Expected " + inFieldName + " to match", inputNode.get(inFieldName), outputNode.get(outFieldName));
        }
    }
}
