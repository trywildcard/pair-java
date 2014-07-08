package com.trywildcard.pair.translation.pinterest;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trywildcard.pair.model.product.ProductCard;
import com.trywildcard.pair.util.TestUtil;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

/**
 * Created by michaelgarate on 6/23/14.
 */
public class PinterestProductCardSerializationTest {
    ObjectMapper mapper = new TestUtil().getObjectMapper();
    static String inputString;
    static String solutionString;

    @BeforeClass
    public static void prepare() throws IOException {
        // TODO: test null and empty fields
        //mapper.setSerializationInclusion(Include.NON_NULL);
        //mapper.setSerializationInclusion(Include.NON_EMPTY);

        inputString = TestUtil.readResourceAsString("example_product_card.json");
        solutionString = TestUtil.readResourceAsString("example_product_card_pinterest.json");
    }

    @Test
    public void testSerializeProductCard() throws URISyntaxException, JsonMappingException, IOException{

        JsonNode inputNode = mapper.readTree(inputString);
        JsonNode solutionNode = mapper.readTree(solutionString);

        ProductCard card = mapper.readValue(inputString, ProductCard.class);
        JsonNode outputNode = mapper.readTree(card.writeAsPinterestJsonString());

        assertEquals("Expected solutionNode and outputNode sizes to match", solutionNode.size(), outputNode.size());


        Iterator<String> solutionItr = solutionNode.fieldNames();
        Iterator<String> outputItr = outputNode.fieldNames();

        while (solutionItr.hasNext()){
            String solutionFieldName = solutionItr.next();
            String outFieldName = outputItr.next();

            assertEquals("Expected " + solutionFieldName + " to match", solutionNode.get(solutionFieldName), outputNode.get(outFieldName));
        }


    }
}
