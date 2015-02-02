package com.trywildcard.pair.util;

import com.google.common.collect.Lists;
import com.trywildcard.pair.exception.CardBuilderException;

import java.text.ParseException;
import java.util.List;

/**
 * Created by cmcewen on 2/2/15.
 */
public class DummyAbstractCard {
    public final List<String> keywords = Lists.newArrayList("keyword1", "keyword2");
    public final String appLinkIos = "ios://applink";
    public final String appLinkAndroid ="android://applink";

    public DummyAbstractCard() throws ParseException, CardBuilderException {};
}
