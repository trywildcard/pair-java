package com.trywildcard.pair;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by michaelgarate on 7/7/14.
 */
public class PairTest {
    @Test
    public void testPairVersion() {
        assertEquals(Pair.getInstance().getVersion(), "0.4.2");
    }

    @Test
    public void testPairValidation(){
        assertEquals(Pair.getInstance().getStrictValidation(), false);
        Pair.getInstance().setStrictValidation(true);
        assertEquals(Pair.getInstance().getStrictValidation(), true);
        Pair.getInstance().setStrictValidation(false);
        assertEquals(Pair.getInstance().getStrictValidation(), false);
    }
}
