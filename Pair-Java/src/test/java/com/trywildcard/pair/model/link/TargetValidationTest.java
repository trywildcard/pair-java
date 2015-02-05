package com.trywildcard.pair.model.link;

import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.util.DummyLink;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by karthiksenthil on 2/3/15.
 */
public class TargetValidationTest {

    private DummyLink dummyLink;
    private Target target;

    @Before
    public void setUp() throws ParseException, CardBuilderException {
        dummyLink = new DummyLink();
        target = new Target(dummyLink.url);
    }

    @Test
    public void isValidWithAttributes(){
        assertEquals(0,target.getErrors().size());
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithEmptyUrlString() throws CardBuilderException {
        Target target = new Target("");
    }

    @Test
    public void emptyTitle() throws CardBuilderException {
        Target target = new Target("http://url.com");
        target.setTitle("");
        assertNull(target.getTitle());
        assertEquals(target.getErrors().size(), 1);
    }

    @Test
    public void emptyDescription() throws CardBuilderException {
        Target target = new Target("http://url.com");
        target.setDescription("");
        assertNull(target.getDescription());
        assertEquals(target.getErrors().size(), 1);
    }

    @Test
    public void nullDate() throws CardBuilderException {
        Target target = new Target("http://url.com");
        target.setPublicationDate(null);
        assertNull(target.getPublicationDate());
        assertEquals(target.getErrors().size(), 1);
    }
}
