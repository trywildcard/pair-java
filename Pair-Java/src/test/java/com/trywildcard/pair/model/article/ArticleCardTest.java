package com.trywildcard.pair.model.article;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.model.media.Image;
import com.trywildcard.pair.util.DummyArticle;
import com.trywildcard.pair.util.TestUtil;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;

/**
 * Created by karthiksenthil on 10/4/14.
 */
public class ArticleCardTest {

    ObjectMapper mapper = TestUtil.getObjectMapper();
    private static DummyArticle dummyArticle;

    @BeforeClass
    public static void prepare() throws ParseException, CardBuilderException {
        dummyArticle = new DummyArticle();
    }

    private void testMinimalCardAttributes(ArticleCard card){
        assertEquals("Article Title should match", dummyArticle.title, card.getArticle().getTitle());
        assertEquals("Article HtmlContent should match", dummyArticle.htmlContent, card.getArticle().getHtmlContent());

        assertEquals("Web url should match", dummyArticle.webUrl, card.getWebUrl().toString());
    }

    private ArticleCard buildMinimalArticleCard() throws CardBuilderException {

        Article article = new ArticleBuilder(dummyArticle.title, dummyArticle.htmlContent).build();

        ArticleCard articleCard = new ArticleCard(article, dummyArticle.webUrl);

        return articleCard;
    }

    @Test
    public void testMinimalArticleCard() throws JsonProcessingException, CardBuilderException {
        ArticleCard card = buildMinimalArticleCard();
        testMinimalCardAttributes(card);
    }


    @Test
    public void testMinimalArticleCardWithMinimalConstructor() throws CardBuilderException {
        Article article = new ArticleBuilder(dummyArticle.title, dummyArticle.htmlContent).build();
        ArticleCard articleCard = new ArticleCard(article, dummyArticle.webUrl);
        testMinimalCardAttributes(articleCard);
    }

    @Test
    public void testExtensiveWriteAsJsonMethod() throws JsonParseException, JsonMappingException, IOException, CardBuilderException {
        String inputString = TestUtil.readResourceAsString("example_article_card.json");
        ArticleCard fixtureCard = mapper.readValue(inputString,  ArticleCard.class);

        Article generatedArticle = buildExtensiveArticle();
        ArticleCard generatedCard = new ArticleCard(generatedArticle, dummyArticle.webUrl);

        assertEquals(mapper.writeValueAsString(fixtureCard), generatedCard.writeAsJsonString());
    }

    @Test
    public void testMinimalWriteAsJsonMethod() throws JsonParseException, JsonMappingException, IOException, CardBuilderException {
        String inputString = TestUtil.readResourceAsString("example_minimal_article_card.json");
        ArticleCard fixtureCard = mapper.readValue(inputString,  ArticleCard.class);
        ArticleCard generatedCard = buildMinimalArticleCard();

        assertEquals(mapper.writeValueAsString(fixtureCard), generatedCard.writeAsJsonString());
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullArticle() throws CardBuilderException {
        ArticleCard card = new ArticleCard(null, dummyArticle.webUrl);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithBothNull() throws CardBuilderException {
        ArticleCard card = new ArticleCard(null, null);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullWebUrl() throws CardBuilderException {
        Article article = new ArticleBuilder(dummyArticle.title, dummyArticle.htmlContent).build();
        ArticleCard card = new ArticleCard(article, null);
    }

    private Article buildExtensiveArticle() throws CardBuilderException {
        ArticleBuilder builder = new ArticleBuilder(dummyArticle.title, dummyArticle.htmlContent);

        builder.isBreaking(dummyArticle.isBreaking);
        builder.abstractContent(dummyArticle.abstractContent);
        builder.author(dummyArticle.author);
        builder.media(dummyArticle.image);
        builder.publicationDate(dummyArticle.publicationDate);
        builder.updatedDate(dummyArticle.updatedDate);
        builder.source(dummyArticle.source);
        builder.appLinkAndroid(dummyArticle.appLinkAndroid);
        builder.appLinkIos(dummyArticle.appLinkIos);
        builder.keywords(dummyArticle.keywords);

        return builder.build();
    }

    @Test
    public void testBuildArticleCardWithWebUrl() throws CardBuilderException {
        ArticleCard articleCard = new ArticleCard("http://www.bbc.com/news/business-29424351");
        assertEquals(articleCard.getArticle().getTitle(), "Wonga sees profits more than halve");
        assertEquals(((Image) articleCard.getArticle().getMedia()).getImageUrl().toString(), "http://news.bbcimg.co.uk/media/images/77915000/jpg/_77915774_77914640.jpg");
   }
}
