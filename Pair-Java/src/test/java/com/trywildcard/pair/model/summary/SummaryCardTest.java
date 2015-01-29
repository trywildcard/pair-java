package com.trywildcard.pair.model.summary;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.model.article.ArticleCard;
import com.trywildcard.pair.model.media.Image;
import com.trywildcard.pair.util.DummySummary;
import com.trywildcard.pair.util.TestUtil;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;

/**
 * Created by karthiksenthil on 1/28/15.
 */
public class SummaryCardTest {
    ObjectMapper mapper = TestUtil.getObjectMapper();
    private static DummySummary dummySummary;

    @BeforeClass
    public static void prepare() throws ParseException, CardBuilderException {
        dummySummary = new DummySummary();
    }

    private void testMinimalCardAttributes(SummaryCard card){
        assertEquals("Summary Title should match", dummySummary.title, card.getSummary().getTitle());
        assertEquals("Summary HtmlContent should match", dummySummary.description, card.getSummary().getDescription());

        assertEquals("Web url should match", dummySummary.webUrl, card.getWebUrl().toString());
    }

    private SummaryCard buildMinimalSummaryCard() throws CardBuilderException {

        Summary summary = new Summary(dummySummary.title, dummySummary.description);

        SummaryCard summaryCard = new SummaryCard(summary, dummySummary.webUrl);

        return summaryCard;
    }

    @Test
    public void testMinimalSummaryCard() throws JsonProcessingException, CardBuilderException {
        SummaryCard card = buildMinimalSummaryCard();
        testMinimalCardAttributes(card);
    }

    @Test
    public void testMinimalArticleCardWithMinimalConstructor() throws CardBuilderException {
        Summary summary = new Summary(dummySummary.title, dummySummary.description);
        SummaryCard summaryCard = new SummaryCard(summary, dummySummary.webUrl);
        testMinimalCardAttributes(summaryCard);
    }

    @Test(expected = CardBuilderException.class)
    public void testNullKeywords() throws CardBuilderException {
        Summary summary = new Summary(dummySummary.title, dummySummary.description);
        SummaryCard summaryCard = new SummaryCard(summary, dummySummary.webUrl);
        summaryCard.setKeywords(null);
    }

    @Test
    public void testExtensiveWriteAsJsonMethod() throws JsonParseException, JsonMappingException, IOException, CardBuilderException {
        String inputString = TestUtil.readResourceAsString("example_summary_card.json");
        SummaryCard fixtureCard = mapper.readValue(inputString,  SummaryCard.class);

        Summary generatedSummary = buildExtensiveSummary();
        SummaryCard generatedCard = new SummaryCard(generatedSummary, dummySummary.webUrl);
        generatedCard.setKeywords(dummySummary.keywords);
        generatedCard.setAppLinkAndroid(dummySummary.appLinkAndroid);
        generatedCard.setAppLinkIos(dummySummary.appLinkAndroid);

        System.out.println(generatedCard.writeAsJsonString());

        assertEquals(mapper.writeValueAsString(fixtureCard), generatedCard.writeAsJsonString());
    }


    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullSummary() throws CardBuilderException {
        SummaryCard card = new SummaryCard(null, dummySummary.webUrl);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithBothNull() throws CardBuilderException {
        ArticleCard card = new ArticleCard(null, null);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullWebUrl() throws CardBuilderException {
        Summary summary = new Summary(dummySummary.title, dummySummary.description);
        SummaryCard card = new SummaryCard(summary, null);
    }

    private Summary buildExtensiveSummary() throws CardBuilderException {
        Summary summary = new Summary(dummySummary.title, dummySummary.description);

        summary.setMedia(dummySummary.image);
        return summary;
    }

    @Test
    public void testBuildArticleCardWithWebUrl() throws CardBuilderException {
        ArticleCard articleCard = new ArticleCard("http://www.bbc.com/news/business-29424351");
        assertEquals(articleCard.getArticle().getTitle(), "Wonga sees profits more than halve");
        assertEquals(((Image) articleCard.getArticle().getMedia()).getImageUrl().toString(), "http://news.bbcimg.co.uk/media/images/77915000/jpg/_77915774_77914640.jpg");
    }
}
