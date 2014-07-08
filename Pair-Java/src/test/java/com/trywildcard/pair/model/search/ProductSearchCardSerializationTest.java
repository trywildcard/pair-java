package com.trywildcard.pair.model.search;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trywildcard.pair.Pair;
import com.trywildcard.pair.util.TestUtil;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;

import static junit.framework.TestCase.assertEquals;

/**
 * Test serializing and deserializing a product search results card from data in a json file.
 */
public class ProductSearchCardSerializationTest {

    private static String inputString;
    private ObjectMapper mapper = new TestUtil().getObjectMapper();

    @BeforeClass
    public static void initialize() throws IOException {
        inputString = TestUtil.readResourceAsString("example_product_search_card.json");
        Pair.init();
    }

    @Test
    public void serializeProductSearchCardTest() throws IOException {
        JsonNode inputNode = mapper.readTree(inputString);

        ProductSearchCard card = mapper.readValue(inputString, ProductSearchCard.class);
        JsonNode outputNode = mapper.readTree(card.writeAsJsonString());

        TestCase.assertEquals("Expected inputNode and outputNode sizes to match", inputNode.size(), outputNode.size());

        Iterator<String> inputItr = inputNode.fieldNames();
        Iterator<String> outputItr = outputNode.fieldNames();

        while (inputItr.hasNext()){
            String inFieldName = inputItr.next();
            String outFieldName = outputItr.next();

            Assert.assertEquals("Expected " + inFieldName + " to match", inputNode.get(inFieldName), outputNode.get(outFieldName));
        }
    }
}
