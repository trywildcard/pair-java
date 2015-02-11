package com.trywildcard.pair.model.link;

import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.model.AbstractCard;
import com.trywildcard.pair.model.CardType;

import java.net.MalformedURLException;
import java.net.URL;

public class LinkCard extends AbstractCard {

    private Target target;

    /**
     * Construct a link card
     */
    public LinkCard(Target target) throws CardBuilderException {
        this.cardType = CardType.LINK;
        target(target);
    }

    public LinkCard(Target target, String webUrl) throws CardBuilderException {
        this.cardType = CardType.LINK;
        target(target);
        setWebUrl(webUrl);
    }

    public void setWebUrl(String webUrl) {
        boolean isValid = v.optional(v.notNullOrEmpty(webUrl), "Must specify a card webUrl.");
        if (isValid) {
            try {
                this.webUrl = new URL(webUrl);
            } catch (MalformedURLException e) {
                v.optional(v.fail(), "Could not parse URL from webUrl string.");
            }
        }
    }

    private void target(Target target) throws CardBuilderException {
        v.required(v.notNull(target), "Must specify a target.");

        this.target = target;
    }

    public Target getTarget() {
        return target;
    }

    /**
     * Private constructor to allow for Jackson deserialization.
     */
    private LinkCard(){}

}
