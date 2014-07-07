package com.trywildcard.pair;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by michaelgarate on 7/7/14.
 */
public class PairTest {
    @Test
    public void testPairVersion() {
        Pair.init();
        assertEquals(Pair.getVersion(), "0.0.1");
    }

    @Test
    public void testPairValidation(){
        Pair.init();
        assertEquals(Pair.getStrictValidation(), false);
        Pair.setStrictValidation(true);
        assertEquals(Pair.getStrictValidation(), true);
    }
}
