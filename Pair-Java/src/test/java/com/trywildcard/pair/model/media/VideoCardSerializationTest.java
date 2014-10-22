package com.trywildcard.pair.model.media;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trywildcard.pair.model.article.ArticleCard;
import com.trywildcard.pair.util.TestUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;

/**
 * Created by karthiksenthil on 10/5/14.
 */
public class VideoCardSerializationTest {
    ObjectMapper mapper = new TestUtil().getObjectMapper();
    static String inputString;

    @BeforeClass
    public static void prepare() throws IOException {
        // TODO: test null and empty fields
        //mapper.setSerializationInclusion(Include.NON_NULL);
        //mapper.setSerializationInclusion(Include.NON_EMPTY);

        inputString = TestUtil.readResourceAsString("example_video_card.json");
    }

    @Test
    public void testSerializeArticleCard() throws URISyntaxException, JsonMappingException, IOException{
        JsonNode inputNode = mapper.readTree(inputString);

        VideoCard card = mapper.readValue(inputString, VideoCard.class);
        JsonNode outputNode = mapper.readTree(card.writeAsJsonString());

        Assert.assertEquals("Expected inputNode and outputNode sizes to match", inputNode.size(), outputNode.size());

        Iterator<String> inputItr = inputNode.fieldNames();
        Iterator<String> outputItr = outputNode.fieldNames();

        while (inputItr.hasNext()){
            String inFieldName = inputItr.next();
            String outFieldName = outputItr.next();

            Assert.assertEquals("Expected " + inFieldName + " to match", inputNode.get(inFieldName), outputNode.get(outFieldName));
        }
    }
}
