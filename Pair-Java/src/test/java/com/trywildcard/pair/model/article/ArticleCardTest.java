package com.trywildcard.pair.model.article;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.model.creator.Creator;
import com.trywildcard.pair.model.creator.CreatorBuilderTest;
import com.trywildcard.pair.model.media.Image;
import com.trywildcard.pair.util.DummyAbstractCard;
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
    private static DummyAbstractCard dummyAbstractCard;

    @BeforeClass
    public static void prepare() throws ParseException, CardBuilderException {
        dummyArticle = new DummyArticle();
        dummyAbstractCard = new DummyAbstractCard();
    }

    private void testMinimalCardAttributes(ArticleCard card){
        assertEquals("Article Title should match", dummyArticle.title, card.getArticle().getTitle());
        assertEquals("Article AbstractContent should match", dummyArticle.abstractContent, card.getArticle().getAbstractContent());

        assertEquals("Web url should match", dummyArticle.webUrl, card.getWebUrl().toString());
    }

    private ArticleCard buildMinimalArticleCard() throws CardBuilderException {

        Article article = new ArticleBuilder(dummyArticle.title, dummyArticle.abstractContent).build();

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
        Article article = new ArticleBuilder(dummyArticle.title, dummyArticle.abstractContent).build();
        ArticleCard articleCard = new ArticleCard(article, dummyArticle.webUrl);
        testMinimalCardAttributes(articleCard);
    }

    @Test(expected = CardBuilderException.class)
    public void testNullKeywords() throws CardBuilderException {
        Article article = new ArticleBuilder(dummyArticle.title, dummyArticle.abstractContent).build();
        ArticleCard articleCard = new ArticleCard(article, dummyArticle.webUrl);
        articleCard.setKeywords(null);
    }

    @Test
    public void testExtensiveWriteAsJsonMethod() throws JsonParseException, JsonMappingException, IOException, CardBuilderException, ParseException {
        String inputString = TestUtil.readResourceAsString("example_article_card.json");
        ArticleCard fixtureCard = mapper.readValue(inputString,  ArticleCard.class);

        Article generatedArticle = buildExtensiveArticle();
        ArticleCard generatedCard = new ArticleCard(generatedArticle, dummyArticle.webUrl);

        generatedCard.setKeywords(dummyAbstractCard.keywords);
        generatedCard.setAppLinkIos(dummyAbstractCard.appLinkIos);
        generatedCard.setAppLinkAndroid(dummyAbstractCard.appLinkAndroid);

        CreatorBuilderTest creatorTest = new CreatorBuilderTest();
        creatorTest.prepare();
        Creator generatedCreator = creatorTest.buildExtensiveCreator();
        generatedCard.setCreator(generatedCreator);

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
        Article article = new ArticleBuilder(dummyArticle.title, dummyArticle.abstractContent).build();
        ArticleCard card = new ArticleCard(article, null);
    }

    private Article buildExtensiveArticle() throws CardBuilderException {
        ArticleBuilder builder = new ArticleBuilder(dummyArticle.title, dummyArticle.abstractContent);

        builder.isBreaking(dummyArticle.isBreaking);
        builder.htmlContent(dummyArticle.htmlContent);
        builder.author(dummyArticle.author);
        builder.media(dummyArticle.image);
        builder.publicationDate(dummyArticle.publicationDate);
        builder.updatedDate(dummyArticle.updatedDate);
        builder.source(dummyArticle.source);

        return builder.build();
    }

    @Test
    public void testBuildArticleCardWithWebUrl() throws CardBuilderException {
        ArticleCard articleCard = new ArticleCard("http://grantland.com/the-triangle/j-r-in-cleveland-an-ohio-nightlife-guide-for-the-famous-nyc-party-animal/");
        assertEquals(articleCard.getArticle().getTitle(), "J.R. in Cleveland: An Ohio Nightlife Guide for the Famous NYC Party Animal");
        assertEquals(((Image) articleCard.getArticle().getMedia()).getImageUrl().toString(), "https://espngrantland.files.wordpress.com/2015/01/jr-smith.jpg?w=1200");
   }
}
