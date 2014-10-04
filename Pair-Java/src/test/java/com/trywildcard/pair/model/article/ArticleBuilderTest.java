package com.trywildcard.pair.model.article;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.util.DummyArticle;
import com.trywildcard.pair.util.TestUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.text.ParseException;

public class ArticleBuilderTest {

    ObjectMapper mapper = TestUtil.getObjectMapper();
    private static DummyArticle dummyArticle;

    @BeforeClass
    public static void prepare() throws ParseException, CardBuilderException {
        dummyArticle = new DummyArticle();
    }

    private void testMinimalArticleAttributes(Article article){
        Assert.assertEquals("Name should match", dummyArticle.title, article.getTitle());
        Assert.assertEquals("HtmlContent should match", dummyArticle.htmlContent, article.getHtmlContent());
    }

    private Article buildMinimalArticle() throws CardBuilderException {

        ArticleBuilder articleBuilder = new ArticleBuilder(dummyArticle.title, dummyArticle.htmlContent);

        return articleBuilder.build();
    }

    @Test
    public void testMinimalProduct() throws JsonProcessingException, CardBuilderException {
        Article article = buildMinimalArticle();
        testMinimalArticleAttributes(article);
    }

    @Test
    public void testMinimalArticleWithMinimalConstructor() throws CardBuilderException {
        Article article = new ArticleBuilder(dummyArticle.title, dummyArticle.htmlContent).build();
        testMinimalArticleAttributes(article);
    }

    private void testExtensiveCardAttributes(Article article) throws MalformedURLException {
        testMinimalArticleAttributes(article);

        Assert.assertEquals("isBreaking should match", dummyArticle.isBreaking, article.getIsBreaking());
        Assert.assertEquals("Abstract should match", dummyArticle.abstractContent, article.getAbstractContent());
        Assert.assertEquals("Publication Date should match", dummyArticle.publicationDate, article.getPublicationDate());
        Assert.assertEquals("Updated Date should match", dummyArticle.updatedDate, article.getUpdatedDate());


        Assert.assertEquals("By-Line should match", dummyArticle.byLine, article.getByLine());
        Assert.assertEquals("Media should match", dummyArticle.image, article.getMedia());
        Assert.assertEquals("Source should match", dummyArticle.source, article.getSource());
    }


    private Article buildExtensiveArticle() throws CardBuilderException {
        ArticleBuilder builder = new ArticleBuilder(dummyArticle.title, dummyArticle.htmlContent);

        builder.isBreaking(dummyArticle.isBreaking);
        builder.abstractContent(dummyArticle.abstractContent);
        builder.byLine(dummyArticle.byLine);
        builder.media(dummyArticle.image);
        builder.publicationDate(dummyArticle.publicationDate);
        builder.updatedDate(dummyArticle.updatedDate);
        builder.source(dummyArticle.source);

        return builder.build();
    }

    @Test
    public void testExtensiveArticleTest() throws IOException, URISyntaxException, CardBuilderException {
        Article article = buildExtensiveArticle();
        testExtensiveCardAttributes(article);
    }
}
