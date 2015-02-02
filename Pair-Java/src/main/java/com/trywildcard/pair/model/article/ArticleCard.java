package com.trywildcard.pair.model.article;

import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.model.AbstractCard;
import com.trywildcard.pair.model.CardType;

public class ArticleCard extends AbstractCard {
    private Article article;

    /**
     * Construct an article card
     */
    public ArticleCard(Article article, String webUrl) throws CardBuilderException {
        this.cardType = CardType.ARTICLE;
        article(article);
        webUrl(webUrl);
    }

    /*
    * Constructs an article card by attempting to extract relevant meta tags from input web url
    */
    public ArticleCard(String webUrl) throws CardBuilderException {
        super(webUrl);
        this.cardType = CardType.ARTICLE;
        article(new ArticleBuilder(this.getMetaTagModel()).build());
    }

    private void article(Article article) throws CardBuilderException {
        v.required(v.notNull(article), "Must specify an article.");

        this.article = article;
    }

    public Article getArticle() {
        return article;
    }

    /**
     * Private constructor to allow for Jackson deserialization.
     */
    private ArticleCard(){}
}
