package com.wildcard.pair.model;

import java.io.IOException;

/**
 * Interface for cards.
 */

public interface Card {
    public String writeAsJsonString() throws IOException;
}
