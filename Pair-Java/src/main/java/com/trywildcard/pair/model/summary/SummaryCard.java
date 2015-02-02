package com.trywildcard.pair.model.summary;

import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.model.AbstractCard;
import com.trywildcard.pair.model.CardType;

/**
 * Created by karthiksenthil on 1/28/15.
 */
public class SummaryCard extends AbstractCard {

    private Summary summary;

    /**
     * Construct a summary card
     */
    public SummaryCard(Summary summary, String webUrl) throws CardBuilderException {
        this.cardType = CardType.SUMMARY;
        summary(summary);
        webUrl(webUrl);
    }

    /*
    * Constructs an article card by attempting to extract relevant meta tags from input web url
    */
    public SummaryCard(String webUrl) throws CardBuilderException {
        super(webUrl);
        this.cardType = CardType.SUMMARY;
        summary(new Summary(this.getMetaTagModel()));
    }

    private void summary(Summary summary) throws CardBuilderException {
        v.required(v.notNull(summary), "Must specify an summary.");

        this.summary = summary;
    }

    public Summary getSummary() {
        return summary;
    }

    /**
     * Private constructor to allow for Jackson deserialization.
     */
    private SummaryCard() {
    }
}
