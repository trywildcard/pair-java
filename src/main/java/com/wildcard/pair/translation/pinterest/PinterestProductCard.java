package com.wildcard.pair.translation.pinterest;

import com.wildcard.pair.model.Card;
import com.wildcard.pair.model.product.Offer;
import com.wildcard.pair.model.product.ProductCard;
import com.wildcard.pair.util.CardSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Given a <code>ProductCard</code> object, structure data for a Pinterest product pin.
 */
public class PinterestProductCard extends PinterestBasicProductCard implements Card {
    private String url;
    private Number price;
    private String currencyCode;
    private String providerName;
    private String availability;
    private Number quantity;
    private Number standardPrice;
    private Date saleStartDate;
    private Date saleEndDate;
    private List<String> geographicAvailability;

    public PinterestProductCard(ProductCard productCard){
        super(productCard);

        Offer firstOffer = productCard.getOffers().get(0);

        this.url = productCard.getWebUrl().toString();
        this.price = firstOffer.getPrice().getPrice();
        this.currencyCode = firstOffer.getPrice().getCurrency().toString();
        this.providerName = productCard.getMerchant();
        this.availability = PinterestTranslationUtil.mapAvailability(firstOffer.getAvailability());
        this.quantity = firstOffer.getQuantity();
        this.standardPrice = firstOffer.getOriginalPrice().getPrice();
        this.saleStartDate = firstOffer.getSaleStartDate();
        this.saleEndDate = firstOffer.getSaleEndDate();
        this.geographicAvailability = buildGeographicAvailability(firstOffer.getGeographicAvailability());
    }

    private List<String> buildGeographicAvailability(List<Locale> locales){
        List<String> countryStrings = new ArrayList<String>();

        for (Locale locale : locales){
            countryStrings.add(locale.getISO3Country());
        }

        return countryStrings;
    }

    public String getUrl(){
        return url;
    }

    public Number getPrice(){
        return price;
    }

    public String getCurrencyCode(){
        return currencyCode;
    }

    public String getProviderName(){
        return providerName;
    }

    public String getAvailability() {
        return availability;
    }

    public Number getQuantity() {
        return quantity;
    }

    public Number getStandardPrice() {
        return standardPrice;
    }

    public Date getSaleStartDate() {
        return saleStartDate;
    }

    public Date getSaleEndDate() {
        return saleEndDate;
    }

    public List<String> getGeographicAvailability() {
        return geographicAvailability;
    }

    public String writeAsJsonString() throws IOException {
        return new CardSerializer().writeCard(this);
    }
}
