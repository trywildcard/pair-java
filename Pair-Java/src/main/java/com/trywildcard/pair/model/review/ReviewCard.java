package com.trywildcard.pair.model.review;

import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.model.AbstractCard;
import com.trywildcard.pair.model.CardType;

public class ReviewCard extends AbstractCard {

    private Review review;

    /**
     * Construct a product card
     */
    public ReviewCard(Review review, String webUrl) throws CardBuilderException {
        this.cardType = CardType.REVIEW;
        review(review);
        webUrl(webUrl);
    }

    /*
    * Constructs an review card by attempting to extract relevant meta tags from input web url
    */
    public ReviewCard(String webUrl) throws CardBuilderException {
        super(webUrl);
        this.cardType = CardType.REVIEW;
        review(new ReviewBuilder(this.getMetaTagModel()).build());
    }

    private void review(Review review) throws CardBuilderException {
        v.required(v.notNull(review), "Must specify a review.");

        this.review = review;
    }

    public Review getReview() {
        return review;
    }

    /**
     * Private constructor to allow for Jackson deserialization.
     */
    private ReviewCard(){}
}
