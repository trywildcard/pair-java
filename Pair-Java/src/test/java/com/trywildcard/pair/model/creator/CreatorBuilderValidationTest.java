package com.trywildcard.pair.model.creator;

import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.util.DummyCreator;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;

/**
 * Created by cmcewen on 2/2/15.
 */
public class CreatorBuilderValidationTest {

        DummyCreator dummyCreator;
        CreatorBuilder builder;

        @Before
        public void setUp() throws ParseException, CardBuilderException, MalformedURLException {
            dummyCreator = new DummyCreator();
            builder = new CreatorBuilder(dummyCreator.name, dummyCreator.favicon);
        }

        @Test
        public void isValidWithAttributes(){
            assertEquals(0,builder.getErrors().size());
        }

        @Test(expected = CardBuilderException.class)
        public void isInvalidWithEmptyNameString() throws CardBuilderException {
            Creator creator = new CreatorBuilder("", dummyCreator.favicon).build();
        }

        @Test(expected = CardBuilderException.class)
        public void isInvalidWithEmptyFavicon() throws CardBuilderException {
            Creator creator = new CreatorBuilder(dummyCreator.name, "").build();
        }

        @Test(expected = CardBuilderException.class)
        public void isInvalidWithEmptyNameAndFavicon() throws CardBuilderException {
            Creator creator = new CreatorBuilder("", "").build();
        }

        @Test
        public void hasErrorForNullIosAppStoreUrl (){
            assertEquals("Errors size should match", 0, builder.getErrors().size());
            builder.iosAppStoreUrl(null);
            assertEquals("Errors size should match", 1, builder.getErrors().size());
        }

        @Test
        public void hasErrorForNullAndroidAppStoreUrl (){
            assertEquals("Errors size should match", 0, builder.getErrors().size());
            builder.androidAppStoreUrl(null);
            assertEquals("Errors size should match", 1, builder.getErrors().size());
        }

        @Test
        public void hasErrorForNullUrl() throws CardBuilderException {
            assertEquals("Errors size should match", 0, builder.getErrors().size());
            builder.url(null);
            assertEquals("Errors size should match", 1, builder.getErrors().size());
        }

}
