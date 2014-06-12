package com.wildcard.model;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializeProductTest {
    ObjectMapper mapper = new ObjectMapper();
    
    @Before
    public void prepare(){
        // TODO: test null and empty fields
        // mapper.setSerializationInclusion(Include.NON_NULL);
        // mapper.setSerializationInclusion(Include.NON_EMPTY);
    }
    
    @Test
    public void testSerializeProductCard() throws URISyntaxException, JsonMappingException, IOException{
        File file = new File(getClass().getResource("example_product_card.json").toURI());
        
        JsonNode inputNode = mapper.readTree(file);
       
        ProductCard card = mapper.readValue(file, ProductCard.class);
        
        JsonNode outputNode = mapper.readTree(mapper.writeValueAsString(card));
        
        assertEquals(inputNode, outputNode);
    }
}
