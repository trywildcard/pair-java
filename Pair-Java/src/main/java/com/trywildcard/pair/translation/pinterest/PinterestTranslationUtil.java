package com.trywildcard.pair.translation.pinterest;

import com.trywildcard.pair.model.product.Availability;

/**
 * Created by michaelgarate on 6/23/14.
 */
public class PinterestTranslationUtil {
    public static String mapAvailability(Availability availability) {
        if (availability == null) {
            return null;
        }

        switch (availability) {
            case Discontinued:
                return "discontinued";
            case InStock:
                return "in stock";
            case InStoreOnly:
                return "preorder"; // not a great match
            case LimitedAvailability:
                return "in stock";
            case OnlineOnly:
                return "in stock";
            case OutOfStock:
                return "out of stock";
            case PreOrder:
                return "preorder";
            case SoldOut:
                return "out of stock";
        }
        return "in stock";
    }
}
