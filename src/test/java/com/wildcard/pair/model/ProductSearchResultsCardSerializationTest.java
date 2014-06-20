package com.wildcard.pair.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wildcard.pair.model.search.ProductSearchResultsCard;
import com.wildcard.pair.util.TestUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by michaelgarate on 6/16/14.
 */
public class ProductSearchResultsCardSerializationTest {

    private static String inputString;
    private ObjectMapper mapper = new TestUtil().getObjectMapper();

    @BeforeClass
    public static void initialize() throws IOException {
        inputString = TestUtil.readResourceAsString("example_product_search_card.json");

    }

    @Test
    public void serializeProductSearchCardTest() throws IOException {
        JsonNode inputNode = mapper.readTree(inputString);

        ProductSearchResultsCard card = mapper.readValue(inputString, ProductSearchResultsCard.class);
        JsonNode outputNode = mapper.readTree(card.writeAsJsonString());

        assertEquals("Expected inputNode and outputNode sizes to match", inputNode.size(), outputNode.size());

        Iterator<String> inputItr = inputNode.fieldNames();
        Iterator<String> outputItr = outputNode.fieldNames();

        while (inputItr.hasNext()){
            String inFieldName = inputItr.next();
            String outFieldName = outputItr.next();

            Assert.assertEquals("Expected " + inFieldName + " to match", inputNode.get(inFieldName), outputNode.get(outFieldName));
        }
    }
}
