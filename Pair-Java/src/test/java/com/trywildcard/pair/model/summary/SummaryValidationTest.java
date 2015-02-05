package com.trywildcard.pair.model.summary;

import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.extraction.MetaTagModel;
import com.trywildcard.pair.model.media.Image;
import com.trywildcard.pair.util.DummySummary;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by karthiksenthil on 1/28/15.
 */
public class SummaryValidationTest {

    private DummySummary dummySummary;
    private Summary summary;

    @Before
    public void setUp() throws ParseException, CardBuilderException {
        dummySummary = new DummySummary();
        summary = new Summary(dummySummary.title, dummySummary.description);
    }

    @Test
    public void isValidWithAttributes(){
        assertEquals(0,summary.getErrors().size());
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithEmptyTitleString() throws CardBuilderException {
        Summary summary = new Summary("", dummySummary.description);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithEmptyHtmlContent() throws CardBuilderException {
        Summary summary = new Summary(dummySummary.title, "");
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithEmptyTitleAndHtmlContent() throws CardBuilderException {
        Summary summary = new Summary("", "");
    }

    @Test
    public void hasErrorForNullMedia () throws CardBuilderException {
        summary.setMedia(null);
        assertNull(summary.getMedia());
    }

    @Test(expected = CardBuilderException.class)
    public void nullMetaTagModel() throws CardBuilderException {
        Summary summary = new Summary(null);
    }

    @Test(expected = CardBuilderException.class)
    public void inCompleteMetaTagModelNull() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn(null);
        when(metaTagModel.getDescription()).thenReturn(null);

        Summary summary = new Summary(metaTagModel);
    }

    @Test(expected = CardBuilderException.class)
    public void inCompleteMetaTagModelEmptyString() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn("");
        when(metaTagModel.getDescription()).thenReturn("");

        Summary summary = new Summary(metaTagModel);
    }

    @Test(expected = CardBuilderException.class)
    public void inCompleteMetaTagModelEmptyTitleString() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn("");
        when(metaTagModel.getDescription()).thenReturn("description");

        Summary summary = new Summary(metaTagModel);
    }

    @Test(expected = CardBuilderException.class)
    public void inCompleteMetaTagModelEmptyDescriptionString() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn("title");
        when(metaTagModel.getDescription()).thenReturn("");

        Summary summary = new Summary(metaTagModel);
    }

    @Test
    public void validMetaTagModel() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn("BBC News Article");
        when(metaTagModel.getDescription()).thenReturn("description");

        Summary summary = new Summary(metaTagModel);
        assertEquals(summary.getDescription(), "description");
        assertEquals(summary.getTitle(), "BBC News Article");
    }

    @Test
    public void validMetaTagModelInvalidOptional() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn("BBC News Article");
        when(metaTagModel.getDescription()).thenReturn("description");
        when(metaTagModel.getImageUrl()).thenReturn(null);

        Summary summary = new Summary(metaTagModel);
        assertNull(summary.getMedia());
    }

    @Test
    public void validMetaTagModelValidOptional() throws CardBuilderException {

        MetaTagModel metaTagModel = mock(MetaTagModel.class);
        when(metaTagModel.getTitle()).thenReturn("BBC News Article");
        when(metaTagModel.getImageUrl()).thenReturn("https://img0.etsystatic.com/011/0/5147325/il_570xN.444675668_1tp8.jpg");
        when(metaTagModel.getDescription()).thenReturn("description");
        when(metaTagModel.getAppLinkAndroid()).thenReturn("android://etsy/1234");
        when(metaTagModel.getAppLinkIos()).thenReturn("ios://etsy/1234");

        Summary summary = new Summary(metaTagModel);
        assertEquals(((Image) summary.getMedia()).getImageUrl().toString(), "https://img0.etsystatic.com/011/0/5147325/il_570xN.444675668_1tp8.jpg");
        assertEquals(summary.getTitle(), "BBC News Article");
        assertEquals(summary.getDescription(), "description");
    }
}
