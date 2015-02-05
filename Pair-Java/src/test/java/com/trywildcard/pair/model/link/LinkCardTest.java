package com.trywildcard.pair.model.link;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.util.DummyLink;
import com.trywildcard.pair.util.TestUtil;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by karthiksenthil on 2/3/15.
 */
public class LinkCardTest {
    ObjectMapper mapper = TestUtil.getObjectMapper();
    private static DummyLink dummyLink;

    @BeforeClass
    public static void prepare() throws ParseException, CardBuilderException {
        dummyLink = new DummyLink();
    }

    private void testMinimalCardAttributes(LinkCard card){
        assertEquals("Link Url should match", dummyLink.url, card.getTarget().getUrl().toString());
    }

    private LinkCard buildMinimalLinkCard() throws CardBuilderException {

        Target target = new Target(dummyLink.url);

        LinkCard linkCard = new LinkCard(target);

        return linkCard;
    }

    @Test
    public void testMinimalLinkCard() throws JsonProcessingException, CardBuilderException {
        LinkCard card = buildMinimalLinkCard();
        testMinimalCardAttributes(card);
    }

    @Test
    public void testMinimalLinkCardWithMinimalConstructor() throws CardBuilderException {
        Target target = new Target(dummyLink.url);
        LinkCard linkCard = new LinkCard(target);
        testMinimalCardAttributes(linkCard);
    }

    @Test
    public void testNullKeywords() throws CardBuilderException {
        Target target = new Target(dummyLink.url);
        LinkCard linkCard = new LinkCard(target);
        linkCard.setKeywords(null);
        assertNull(linkCard.getKeywords());
    }

    @Test
    public void testExtensiveWriteAsJsonMethod() throws JsonParseException, JsonMappingException, IOException, CardBuilderException {
        String inputString = TestUtil.readResourceAsString("example_link_card.json");
        LinkCard fixtureCard = mapper.readValue(inputString,  LinkCard.class);

        Target target = buildExtensiveTarget();
        LinkCard generatedCard = new LinkCard(target);
        generatedCard.setWebUrl(dummyLink.webUrl);
        generatedCard.setKeywords(dummyLink.keywords);
        generatedCard.setAppLinkAndroid(dummyLink.appLinkAndroid);
        generatedCard.setAppLinkIos(dummyLink.appLinkAndroid);

        assertEquals(mapper.writeValueAsString(fixtureCard), generatedCard.writeAsJsonString());
    }


    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullTarget() throws CardBuilderException {
        LinkCard card = new LinkCard(null);
    }


    @Test
    public void isValidWithNullWebUrl() throws CardBuilderException {
        Target target = new Target(dummyLink.url);
        LinkCard card = new LinkCard(target, null);
        assertEquals(card.getTarget(), target);
        assertNull(card.getWebUrl());
    }

    private Target buildExtensiveTarget() throws CardBuilderException {
        Target target = new Target(dummyLink.url);

        target.setTitle(dummyLink.title);
        target.setDescription(dummyLink.description);
        target.setPublicationDate(dummyLink.publicationDate);
        return target;
    }
}
